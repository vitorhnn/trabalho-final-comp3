package br.net.hnn.ufrrj.comp3_trabalho_final.dominio;

import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.roteirossolicitacaomuseu.CriarSolicitacaoMuseuCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SolicitacaoMuseu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/CriarSolicitacaoMuseu.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        LocalDate dataCriacao = LocalDate.parse(req.getParameter("dataCriacao"), DateTimeFormatter.ISO_LOCAL_DATE);
        Command cmd = new CriarSolicitacaoMuseuCommand(
                req.getParameter("nomeMuseu"),
                dataCriacao,
                req.getParameter("cidade"),
                req.getParameter("estado"),
                req.getParameter("cpfGestor"),
                req.getParameter("nomeGestor"),
                req.getParameter("senhaGestor")
        );

        try {
            cmd.execute();
        } catch (Exception ignored) {
        }
    }
}
