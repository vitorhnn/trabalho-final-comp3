package br.net.hnn.ufrrj.comp3_trabalho_final.dominio;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloWorld extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String[] mensagens = { "Olá, mundo", "Adeus, mundo" };
        req.getSession().setAttribute("mensagens", mensagens);
        req.getRequestDispatcher("/WEB-INF/Hello.jsp").forward(req, res);
    }
}
