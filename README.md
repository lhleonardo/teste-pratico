# Desafio Prático - WeFin

## Sobre o desafio

Este projeto trata-se de uma implementação de back-end utilizando Java+Spring e comunicação
com banco de dados PostgreSQL.

O objetivo do sistema é ser utilizado como plataforma de gerenciamento de pessoas.

---

## Funcionalidades/End-points

Os seguintes end-points são oferecidos para manipulação do sistema:

| Método |        URL       |                           Ação                          |
|:------:|:----------------:|:-------------------------------------------------------:|
|  POST  | /person          | Cadastrar nova pessoa                                   |
|   PUT  | /person/{id}     | Editar informação de pessoa cadastrada                  |
| DELETE | /person/{id}     | Apagar registro de uma pessoa                           |
|   GET  | /person          | Listar todas as pessoas cadastradas                     |
|   GET  | /person/id       | Obter informações de uma pessoa específica              |
|   GET  | /v2/api-docs     | Arquivo do Swagger com o JSON da documentação das rotas |
|   GET  | /swagger-ui.html | Visualização da documentação da API pelo Swagger        |


Os métodos de inserção e atualização dos registros de pessoa utilizam o mesmo formato no corpo da requisição, tal como:

```json
{
   "name": "some-name",
   "documentNumber": "some-document"
}
```

---
## Documentação do projeto

* Swagger: [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)

---
## Banco de dados

Para executar o banco de dados do projeto há um arquivo de esqueleto do [Docker Compose](https://docs.docker.com/compose/). 

Basta executar o seguinte comando no terminal para disponibilizar o banco de dados:

```bash
> docker-compose up -d
```

--- 

## Autor

 | [<img src="https://github.com/lhleonardo.png" width=115><br><sub>Leonardo Braz</sub>](https://github.com/lhleonardo) <br><sub>Desenvolvedor</sub> |
| :---: |