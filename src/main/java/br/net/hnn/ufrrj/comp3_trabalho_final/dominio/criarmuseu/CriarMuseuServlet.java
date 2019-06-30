package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu;

import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception.SolicitacaoInvalidaException;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception.SolicitacaoNaoExisteException;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CriarMuseuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UsuarioDTO maybeGestor = (UsuarioDTO) req.getSession().getAttribute("gestor");

        if (maybeGestor != null) {
            req.getRequestDispatcher("/WEB-INF/CriarMuseu.jsp").forward(req, res);
        } else {
            List<SolicitacaoMuseuDTO> solicitacoes;
            try {
                solicitacoes = new ListarSolicitacoesMuseuCommand().execute();
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }

            req.setAttribute("solicitacoes", solicitacoes);
            req.getRequestDispatcher("/WEB-INF/ListarSolicitacoes.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String cmd = req.getParameter("cmd");

        switch (cmd) {
            case "Verifica": {
                int idSolicitacao = Integer.parseInt(req.getParameter("id"));
                Command<SolicitacaoMuseuDTO, Exception> verificarSolicitacaoCommand = new VerificarSolicitacaoMuseuCommand(idSolicitacao);

                try {
                    SolicitacaoMuseuDTO solicitacaoValida = verificarSolicitacaoCommand.execute();

                    req.getSession().setAttribute("solicitacao", solicitacaoValida);
                    res.sendRedirect("/criar-gestor");
                } catch (SolicitacaoNaoExisteException ex) {
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Solicitação não existe");
                } catch (SolicitacaoInvalidaException sie) {
                    req.setAttribute("excecao", sie);
                    req.getRequestDispatcher("/WEB-INF/SolicitacaoInvalida.jsp").forward(req, res);
                } catch (Exception ex) {
                    throw new ServletException(ex);
                }
                break;
            }
            case "Insere": {
                SolicitacaoMuseuDTO solicitacao = (SolicitacaoMuseuDTO) req.getSession().getAttribute("solicitacao");
                UsuarioDTO gestor = (UsuarioDTO) req.getSession().getAttribute("gestor");

                try {
                    new CriarMuseuCommand(solicitacao, gestor).execute();
                } catch (DateTimeParseException ignored) {
                    // nunca deveria acontecer, visto que verificamos a solicitação anteriormente
                } catch (Exception ex) {
                    throw new ServletException(ex);
                }
                break;
            }
            default:
                throw new ServletException("Comando desconhecido");
        }
    }
}
