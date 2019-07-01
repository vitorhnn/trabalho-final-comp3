<%@ page import="br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SolicitacaoMuseuDTO solicitacao = (SolicitacaoMuseuDTO) request.getSession().getAttribute("solicitacao");
    String comando = (String) request.getAttribute("comando");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gerenciamento de Museus do Duarte - Criar Gestor</title>
</head>
<body>
<h1>Confirma esse gestor?</h1>
<form action="criar-gestor" method="post">
    Nome do gestor: <%= solicitacao.getNomeGestor() %><br/>
    CPF do gestor: <%= solicitacao.getCpfGestor() %><br/>
    <input type="hidden" value="<%= comando %>" name="cmd"/>
    <input type="submit" value="<%= comando %>"/>
</form>
</body>
</html>
