package br.net.hnn.ufrrj.comp3_trabalho_final.dominio;

import br.net.hnn.ufrrj.comp3_trabalho_final.ServiceLocator;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.exception.SenhaIncorretaException;
import br.net.hnn.ufrrj.comp3_trabalho_final.dominio.exception.UsuarioNaoExisteException;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.UsuarioTableGateway;
import br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getSession().getAttribute("idUsuario") == null) {
            req.getRequestDispatcher("/WEB-INF/Login.jsp").forward(req, res);
            return;
        }

        res.sendRedirect("hello-world");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String cpf = req.getParameter("cpf");
        String senha = req.getParameter("senha");

        UsuarioTableGateway gateway = ServiceLocator.getInstance().getUsuarioTableGateway();

        try {
            UsuarioDTO usuarioDTO = gateway.findUsuarioByCpf(cpf).orElseThrow(UsuarioNaoExisteException::new);

            if (!usuarioDTO.getSenha().equals(senha)) {
                throw new SenhaIncorretaException();
            }

            req.getSession().setAttribute("idUsuario", usuarioDTO.getId());
            res.sendRedirect("hello-world");
        } catch (UsuarioNaoExisteException | SenhaIncorretaException ex) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            req.getRequestDispatcher("/WEB-INF/Login.jsp").forward(req, res);
        }
    }
}
