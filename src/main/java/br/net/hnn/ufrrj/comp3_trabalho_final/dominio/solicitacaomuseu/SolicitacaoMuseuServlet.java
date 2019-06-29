package br.net.hnn.ufrrj.comp3_trabalho_final.dominio.solicitacaomuseu;

import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SolicitacaoMuseuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/CriarSolicitacaoMuseu.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Command cmd = new CriarSolicitacaoMuseuCommand(
                req.getParameter("nomeMuseu"),
                req.getParameter("dataCriacao"),
                req.getParameter("cidade"),
                req.getParameter("estado"),
                req.getParameter("cpfGestor"),
                req.getParameter("nomeGestor"),
                req.getParameter("senhaGestor")
        );

        try {
            cmd.execute();
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
