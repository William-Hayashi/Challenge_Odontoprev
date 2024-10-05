# CHALLENGE_JAVA_ADVANCED - Aplicação para Cadastro de Clientes

## Integrantes:
- **Murilo Ferreira Ramos** - RM553315
- **Pedro Luiz Prado** - RM553874
- **William Kenzo Hayashi** - RM552659

## Distribuição de Atividades
A divisão das atividades foi realizada conforme as disciplinas da grade curricular:

- **Murilo**: DevOps Tools, Cloud Computing, Compliance, Quality Assurance, e Tests.
- **Pedro**: Disruptive Architectures IoT, IOB, Generative AI, Mobile Application Development, e Advanced Business Development With .NET.
- **William**: Java Advanced, Mastering Relational, e Non-Relational Database.

## Cronograma de Tarefas
- [Link do Trello](https://trello.com/invite/b/6701a3ed3d57ce4ab46300fd/ATTI2c7cf38c5ce5cc04465175370f7a3aedF74A7B2D/backlog-odontoprev)

##
![Diagrama Lógico](Documentos/Documentação/DOCUMENTAÇÃO%20JAVA.pdf)

## Rodagem da Aplicação
Para executar a API, siga os passos abaixo:

1. Execute a classe `TesteSpringApplication`.
2. Aguarde a inicialização completa da aplicação.
3. Após a inicialização, você poderá realizar chamadas GET, POST, PUT, e DELETE.

A aplicação está conectada a um banco de dados hospedado na conta do William, utilizando as credenciais presentes no arquivo de configuração. A porta configurada para a aplicação é a **8080**.

## Diagramas
### Diagrama Relacional
![Diagrama Relacional](Documentos/Relacional.png)

### Diagrama de Lógico
![Diagrama Lógico](Documentos/Logical.png)

## Link do Vídeo de Apresentação
- [Link do Vídeo](https://youtu.be/zDa0eHVw5kU)

## Listagem de Endpoints

#### Criar Cliente
- **Método**: POST
- **URL**: `http://localhost:8080/cadastro`
- **Parâmetros**: 
  - **Exemplo de Body**:
  {
	"name": "William",
    "CPF": "38-34386839",
    "CEP": "04321070",
    "primeiroTratamento": "Sim"
}
- **Respostas**:
  - **200 OK**: Cliente criado com sucesso
  - **400 Bad Request**: Dados inválidos
![Teste Postman POST](Documentos/POST_JAVA.png)


#### Obter Todos os Clientes
- **Método**: GET
- **URL**: `http://localhost:8080/cadastro`
- **Respostas**:
  - **200 OK**: Retorna a lista de clientes
  - **404 Not Found**: Nenhum cliente encontrado
![Teste Postman GET](Documentos/GET_JAVA.png)

#### Obter Cliente por ID
- **Método**: GET
- **URL**: `http://localhost:8080/cadastro/{id}`
- **Parâmetros**: 
  - **Path Variable**: `id` do cliente
- **Respostas**:
  - **200 OK**: Retorna os dados do cliente
  - **404 Not Found**: Cliente não encontrado
![Teste Postman GET BY ID](Documentos/get_by_id.png)


#### Atualizar Cliente
- **Método**: PUT
- **URL**: `http://localhost:8080/cadastro/{id}`
- **Parâmetros**: 
  - **Path Variable**: `id` do cliente
  - **Body**: JSON com os dados atualizados do cliente
- **Respostas**:
  - **200 OK**: Cliente atualizado com sucesso
  - **404 Not Found**: Cliente não encontrado
  ![Teste Postman PUT](Documentos/UPDATE.png)

#### Deletar Cliente
- **Método**: DELETE
- **URL**: `http://localhost:8080/cadastro/{id}`
- **Parâmetros**: 
  - **Path Variable**: `id` do cliente
- **Respostas**:
  - **200 OK**: Cliente deletado com sucesso
  - **404 Not Found**: Cliente não encontrado
![Teste Postman DELETE](Documentos/delete.png)