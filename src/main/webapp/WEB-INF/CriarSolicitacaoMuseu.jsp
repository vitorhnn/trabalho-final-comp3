<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gerenciamento de Museus do Duarte - Criar Solicitação de Museu</title>
</head>
<body>
<form action="solicitacao-museu" method="post">
    <label for="nomeMuseu">Nome do museu:</label><input type="text" id="nomeMuseu" name="nomeMuseu"/><br/>
    <label for="dataCriacao">Data de criação:</label><input type="date" id="dataCriacao" name="dataCriacao"/><br/>
    <label for="cidade">Cidade:</label><input type="text" id="cidade" name="cidade"/><br/>
    <label for="estado">Estado:</label><input type="text" id="estado" name="estado"/><br/>
    <label for="nomeGestor">Nome do usuario:</label><input type="text" id="nomeGestor" name="nomeGestor"/><br/>
    <label for="cpfGestor">CPF do usuario:</label><input type="text" id="cpfGestor" name="cpfGestor"/><br/>
    <label for="senhaGestor">Senha do usuario:</label><input type="password" id="senhaGestor" name="senhaGestor"/><br/>
    <input type="submit" value="Enviar"/>
</form>
</body>
</html>
