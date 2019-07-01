<%@ page import="br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criargestor.exception.GestorInvalidoException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    GestorInvalidoException excecao = (GestorInvalidoException) request.getAttribute("excecao");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gerenciamento de Museus do Duarte - Solicitação Inválida</title>
</head>
<body>
<h1>O gestor é invalido!</h1>
<p>O gestor é inválido: <%= excecao.getMotivo() %></p>
<a href="/criar-museu">Clique aqui para voltar a listagem de solicitações</a>
</body>
</html>
