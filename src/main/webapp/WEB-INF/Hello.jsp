<%@ page import="java.util.List" %>
<html>
<body>
<h2>Mensagens</h2>
<%
    String[] mensagens = (String[]) session.getAttribute("mensagens");
    for (String mensagem : mensagens) {
%>
<%= mensagem %><br/>
<%
    }
%>
</body>
</html>
