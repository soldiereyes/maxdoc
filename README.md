# MaxDoc - Backend

## Descrição do Projeto

MaxDoc é um sistema de gerenciamento de documentos que permite criar, editar, excluir e gerenciar versões de documentos. Este backend foi desenvolvido com **Spring Boot** e utiliza **PostgreSQL** como banco de dados.

## Tecnologias Utilizadas

- Java 11+
- Spring Boot
    - Spring Data JPA
    - Spring Web
- PostgreSQL
- Maven

## Funcionalidades

1. Criar, editar e excluir documentos.
2. Gerar novas versões de documentos existentes.
3. Alterar a fase dos documentos entre **Minuta**, **Revisão**, e **Finalizado**.
4. Garantir unicidade da combinação **Sigla + Versão**.
5. Aplicar regras de negócio específicas para cada fase.

## Configuração do Banco de Dados

No arquivo `application.yml`, configure as credenciais e URL do banco de dados:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/maxdocdb
    username: admin
    password: senhaforte
  jpa:
    hibernate:
      ddl-auto: update
```

## Clone o repositório utilizando os comandos

`git clone https://github.com/usuario/maxdoc-backend.git`
`cd maxdoc-backend`

## Compile e execute o projeto com Maven:

`mvn clean install`
`nmv spring-boot:run`

## Endpoints Disponíveis

Os endpoints disponíveis para interagir com o sistema são os seguintes:

- POST /api/documents: Cria um novo documento.
- GET /api/documents: Retorna todos os documentos.
- PATCH /api/documents/{id}/phase: Altera a fase de um documento.
- POST /api/documents/{id}/new-version: Cria uma nova versão de um documento.
- DELETE /api/documents/{id}: Exclui um documento.

## Exemplo de Requisição e Resposta

```
{
  "title": "Documento de Exemplo",
  "description": "Descrição do documento.",
  "sigla": "DOC001",
  "version": 1
}
```
```
{
  "id": "UUID",
  "title": "Documento de Exemplo",
  "description": "Descrição do documento.",
  "sigla": "DOC001",
  "version": 1,
  "phase": "MINUTA"
}
```


