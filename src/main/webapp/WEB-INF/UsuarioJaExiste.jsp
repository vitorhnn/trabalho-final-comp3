<%@ page import="br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gerenciamento de Museus do Duarte - Usuário já existe</title>
</head>
<body>
<h1>Esse usuário já existe!</h1>
<h2>Deseja converter ele para gestor?</h2>
<form action="criar-gestor" method="post">
    Nome do usuario: <%= usuario.getNome() %><br/>
    CPF do usuario: <%= usuario.getCpf() %><br/>
    <input type="hidden" value="Transforma" name="cmd"/>
    <input type="submit" value="Confirmar"/>
</form>
</body>
</html>
