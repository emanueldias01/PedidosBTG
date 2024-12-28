# PedidosBTG

Bem-vindo ao **PedidosBTG**, um microsserviço desenvolvido como parte de um desafio técnico para uma vaga de backend no Banco BTG. Este projeto foi criado para demonstrar habilidades em construção de APIs RESTful, integração com sistemas de mensageria e manipulação de dados em banco NoSQL. 🚀

## 🛠️ Descrição do Projeto

O **PedidosBTG** é responsável por integrar-se a um sistema de mensageria (RabbitMQ), consumir mensagens de uma fila de pedidos e registrar essas informações em um banco de dados MongoDB. O microsserviço oferece endpoints para realizar as seguintes operações:

- **Salvar pedidos**: Consome mensagens da fila de pedidos e persiste os dados.
- **Buscar o valor total de um pedido.**
- **Listar todos os pedidos de um cliente específico.**
- **Consultar a quantidade total de pedidos de um cliente.**

## 📦 Estrutura do JSON da Mensagem na Fila

A mensagem consumida do RabbitMQ possui o seguinte formato:

```json
{
  "codigoPedido": 12345,
  "codigoCliente": 67890,
  "itens": [
    {
      "produto": "Produto A",
      "quantidade": 2,
      "preco": 19.99
    },
    {
      "produto": "Produto B",
      "quantidade": 1,
      "preco": 45.50
    }
  ]
}
```

## 🌐 Endpoints Disponíveis

### 1. Buscar o Valor Total de um Pedido

- **Endpoint**: `/pedidos/totalPedido/{id}`
- **Descrição**: Retorna o valor total de um pedido específico.

**Exemplo de Resposta**:

```json
{
  "codigoPedido": 12345,
  "valorTotal": 85.48
}
```

### 2. Buscar a Quantidade Total de Pedidos de um Cliente

- **Endpoint**: `/pedidos/quantidadePedidos/{codigoCliente}`
- **Descrição**: Retorna o total de pedidos realizados por um cliente.

**Exemplo de Resposta**:

```json
{
  "codigoCliente": 67890,
  "totalPedidos": 5
}
```

### 3. Buscar Todos os Pedidos de um Cliente

- **Endpoint**: `/pedidos/{codigoCliente}`
- **Descrição**: Lista todos os pedidos realizados por um cliente.

**Exemplo de Resposta**:

```json
[
  {
    "codigoPedido": 12345,
    "itens": [
      { "produto": "Produto A", "quantidade": 2, "preco": 19.99 },
      { "produto": "Produto B", "quantidade": 1, "preco": 45.50 }
    ],
    "valorTotal": 85.48
  }
]
```

## 💻 Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Framework**: Para construção de APIs RESTful e injeção de dependências.
- **MongoDB**: Banco de dados NoSQL para persistência de pedidos.
- **RabbitMQ**: Sistema de mensageria para consumo de filas de pedidos.
