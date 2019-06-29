package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu;

import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception.SolicitacaoInvalidaException;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception.SolicitacaoNaoExisteException;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CriarMuseuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<SolicitacaoMuseuDTO> solicitacoes;
        try {
            solicitacoes = new ListarSolicitacoesMuseuCommand().execute();
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

        req.setAttribute("solicitacoes", solicitacoes);
        req.getRequestDispatcher("/WEB-INF/ListarSolicitacoes.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
    }
}
