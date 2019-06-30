<%@ page import="br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SolicitacaoMuseuDTO solicitacao = (SolicitacaoMuseuDTO) request.getSession().getAttribute("solicitacao");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gerenciamento de Museus do Duarte - Criar Gestor</title>
</head>
<body>
<h1>Confirma esse usuario?</h1>
<form action="criar-gestor" method="post">
    Nome do usuario: <%= solicitacao.getNomeGestor() %><br/>
    CPF do usuario: <%= solicitacao.getCpfGestor() %><br/>
    <input type="hidden" value="Verifica" name="cmd"/>
    <input type="submit" value="Confirmar"/>
</form>
</body>
</html>
