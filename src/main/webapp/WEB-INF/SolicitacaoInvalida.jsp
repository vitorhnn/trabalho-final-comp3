<%@ page import="br.net.hnn.ufrrj.comp3_trabalho_final.dominio.criarmuseu.exception.SolicitacaoInvalidaException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SolicitacaoInvalidaException excecao = (SolicitacaoInvalidaException) request.getAttribute("excecao");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gerenciamento de Museus do Duarte - Solicitação Inválida</title>
</head>
<body>
<h1>A solicitação é inválida!</h1>
<p>A solicitação era inválida: <%= excecao.getMotivo() %></p>
<a href="/criar-museu">Clique aqui para voltar a listagem de solicitações</a>
</body>
</html>
