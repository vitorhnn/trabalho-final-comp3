package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception.GestorInvalidoException;
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
                    new VerificaCriarGestorCommand(solicitacao).execute();
                    req.getRequestDispatcher("/WEB-INF/ConfirmaGestor.jsp");
                }
                case "Insere": {
                    UsuarioDTO gestorInserido = new CriarGestorCommand(solicitacao).execute();
                    req.getSession().setAttribute("gestorInserido", gestorInserido);
                    res.sendRedirect("/criar-museu");
                }
                default:
                    throw new ServletException("Comando desconhecido");
            }
        } catch (GestorInvalidoException gie) {
            req.setAttribute("excecao", gie);
            req.getRequestDispatcher("/WEB-INF/GestorInvalido.jsp").forward(req, res);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
