package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception.GestorInvalidoException;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception.UsuarioJaExisteException;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CriarGestorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        SolicitacaoMuseuDTO solicitacao = (SolicitacaoMuseuDTO) req.getSession().getAttribute("solicitacao");

        if (solicitacao != null) {
            req.setAttribute("solicitacao", solicitacao);
            req.setAttribute("comando", "Verifica");
            req.getRequestDispatcher("/WEB-INF/CriarGestor.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        SolicitacaoMuseuDTO solicitacao = (SolicitacaoMuseuDTO) req.getSession().getAttribute("solicitacao");
        String cmd = req.getParameter("cmd");

        try {
            switch (cmd) {
                case "Verifica": {
                    try {
                        new VerificaCriarGestorCommand(solicitacao).execute();
                    } catch (GestorInvalidoException gie) {
                        req.setAttribute("excecao", gie);
                        req.getRequestDispatcher("/WEB-INF/GestorInvalido.jsp").forward(req, res);
                        return;
                    } catch (UsuarioJaExisteException ujee) {
                        req.getSession().setAttribute("usuario", ujee.getUsuario());
                        req.getRequestDispatcher("/WEB-INF/UsuarioJaExiste.jsp").forward(req, res);
                        return;
                    }

                    req.getSession().setAttribute("usuarioVerificado", true);
                    req.setAttribute("comando", "Insere");
                    req.getRequestDispatcher("/WEB-INF/CriarGestor.jsp").forward(req, res);
                    break;
                }
                case "Insere": {
                    boolean verificado = (Boolean) req.getSession().getAttribute("usuarioVerificado");

                    if (!verificado) {
                        throw new ServletException("Insere com usuário não verificado?");
                    }

                    UsuarioDTO gestorInserido = new CriarGestorCommand(solicitacao).execute();
                    req.getSession().setAttribute("gestor", gestorInserido);
                    res.sendRedirect("/criar-museu");
                    break;
                }
                case "Transforma": {
                    UsuarioDTO usuario = (UsuarioDTO) req.getSession().getAttribute("usuario");
                    new TransformaUsuarioGestorCommand(usuario).execute();
                    req.getSession().setAttribute("gestor", usuario);
                    res.sendRedirect("/criar-museu");
                    break;
                }
                default:
                    throw new ServletException("Comando desconhecido");
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
