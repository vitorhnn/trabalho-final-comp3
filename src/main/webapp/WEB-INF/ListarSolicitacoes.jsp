<%@ page import="br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto.SolicitacaoMuseuDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gerenciamento de Museus do Duarte - Listar solicitações de museu</title>
    <style>
        #tabela-solicitacoes tr > *:nth-child(1)
        {
            display: none;
        }
    </style>
</head>
<body>
    <table id="tabela-solicitacoes">
        <tr>
            <th>ID solicitação</th>
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
            <td>
                <form id="solicitacao-<%= solicitacao.getId() %>-form" action="criar-museu" method="post">
                    <input type="hidden" name="id" value="<%= solicitacao.getId() %>"/>
                </form>
            </td>
            <td><%= solicitacao.getNome() %></td>
            <td><%= solicitacao.getDataCriacao() %></td>
            <td><%= solicitacao.getCidade() %></td>
            <td><%= solicitacao.getCpfGestor() %></td>
            <td><input type="submit" value="Criar" form="solicitacao-<%= solicitacao.getId() %>-form"/></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
