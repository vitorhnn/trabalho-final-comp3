<%@ page import="br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gerenciamento de Museus do Duarte - Listar solicitações de museu</title>
</head>
<body>
    <table>
        <tr>
            <th>Nome</th>
            <th>Data de criação</th>
            <th>Cidade</th>
            <th>CPF do gestor</th>
        </tr>

        <%
            List<SolicitacaoMuseuDTO> solicitacoes = (List<SolicitacaoMuseuDTO>) request.getAttribute("solicitacoes");
            for (SolicitacaoMuseuDTO solicitacao : solicitacoes) {
        %>
        <tr>
            <td><%= solicitacao.getNome() %></td>
            <td><%= solicitacao.getDataCriacao() %></td>
            <td><%= solicitacao.getCidade() %></td>
            <td><%= solicitacao.getCpfGestor() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
