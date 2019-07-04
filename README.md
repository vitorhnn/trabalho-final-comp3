# Trabalho final de Computação 3
[![Build Status](https://travis-ci.org/vitorhnn/trabalho-final-comp3.svg?branch=master)](https://travis-ci.org/vitorhnn/trabalho-final-comp3)

Trabalho final da disciplina de Computação 3 da UFRRJ, contemplando desenvolvimento de software utilizando múltiplas camadas.

O nosso grupo ficou encarregado de usar o padrão de roteiro de roteiro de transações para camada de domínio e o padrão de gateway de tabelas para a camada de dados, como definidos por Martin Fowler no livro Patterns of Enterprise Application Architecture

## Deploy no Tomcat
Para rodar a aplicação no Tomcat, use o Maven para gerar o WAR:
```
mvn package
```

Após isso, copie o WAR gerado para a pasta webapps do Tomcat e execute o Tomcat fornecendo a variável de ambiente ``DB_STRING`` com uma string válida do JDBC para instanciar o banco de dados, por exemplo ``jdbc:derby:memory:teste;create=true``
