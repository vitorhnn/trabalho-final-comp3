<%@ page import="br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO" %>
<%@ page import="br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.UsuarioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SolicitacaoMuseuDTO solicitacao = (SolicitacaoMuseuDTO) request.getSession().getAttribute("solicitacao");
    UsuarioDTO gestor = (UsuarioDTO) request.getSession().getAttribute("gestor");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gerenciamento de Museus do Duarte - Criar Museu</title>
</head>
<body>
<h1>Confirma esse museu?</h1>
<form action="criar-museu" method="post">
    Nome do museu: <%= solicitacao.getNome() %><br/>
    Nome do gestor: <%= gestor.getNome() %><br/>
    <input type="hidden" value="Insere" name="cmd"/>
    <input type="submit" value="Confirmar"/>
</form>
</body>
</html>
