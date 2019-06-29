package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor;

import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO;

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
        super.doPost(req, res);
    }
}
