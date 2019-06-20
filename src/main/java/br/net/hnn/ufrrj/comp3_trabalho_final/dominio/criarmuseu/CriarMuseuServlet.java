package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu;

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
}
