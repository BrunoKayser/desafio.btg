# 📦 Gerenciador de Pedidos  

Este projeto consiste em uma aplicação desenvolvida em **Java (Spring Boot)** que integra **RabbitMQ** e **PostgreSQL** para processamento e persistência de pedidos.  

A aplicação é responsável por:  
- **Consumir mensagens** de uma fila no RabbitMQ contendo informações de pedidos.  
- **Persistir os dados** no banco PostgreSQL.  
- **Disponibilizar uma API REST** para consulta das informações processadas.  

---

## 🚀 Funcionalidades  
- **Consumo de pedidos** enviados para a fila RabbitMQ.
- **Cálculo do valor total do pedido.**  
- **Consulta da quantidade de pedidos por cliente.**  
- **Listagem dos pedidos realizados por cliente.**  

---

## 📂 Estrutura da Mensagem Consumida (JSON)  
```json
{
  "codigoPedido": 1001,
  "codigoCliente": 1,
  "itens": [
    {
      "produto": "lápis",
      "quantidade": 100,
      "preco": 1.10
    },
    {
      "produto": "caderno",
      "quantidade": 10,
      "preco": 1.00
    }
  ]
}
```
---

🛠️ Tecnologias e Frameworks Utilizados
- **Spring Boot**
  - **spring-boot-starter-web** → Desenvolvimento da API REST.
  - **spring-boot-starter-amqp** → Integração com RabbitMQ.
  - **spring-boot-starter-data-jpa** → Persistência de dados via JPA/Hibernate.
- **PostgreSQL** → Banco de dados relacional.
- **Lombok** → Redução de boilerplate com anotações.
- **JUnit & Mockito** → Testes unitários e criação de mocks.
- **MockMvc** → Testes funcionais da API REST.
- **Generic Fixture** → Geração de objetos randômicos para testes.
---

## Como Rodar a aplicação
Dentro da pasta do projeto `springboot.processar.pedidos` abrir o terminal de sua preferência e executar o comando: `docker-compose up -d`

‼️ Após finalizar o pull e o start dos três containers (btg-postgres, rabbitmq e Nome da aplicação) aguardar em torno de 30 a 60 segundos para iniciar os testes.

## 🐇 Como postar uma mensagem no tópico
### 1. Acessar o rabbitMQ 

Link da interface gráfica do rabbitMq: http://localhost:15672/

Username: admin

Password: 123456

### 2. Postar mensagem na fila PEDIDO

Link: http://localhost:15672/#/queues/%2F/PEDIDO

Mensagem de exemplo:
```json
{
  "codigoPedido": 1001,
  "codigoCliente": 1,
  "itens": [
    {
      "produto": "lápis",
      "quantidade": 100,
      "preco": 1.10
    },
    {
      "produto": "caderno",
      "quantidade": 10,
      "preco": 1.00
    }
  ]
}
```

## 🌐 API de consulta 

Curl da API.
```curl
curl --location 'http://localhost:8080/cliente/1/pedidos'
```

Descrição dos campos:
de response: 

```
{
    "pedidos": [ ------------------------> Apresentação dos pedidos consultados
        {
            "codigoCliente": 1, ---------> código que identifica o cliente
            "codigoPedido": 1001, -------> Código que identifica o pedido
            "valor": 120.00 -------------> Valor total do pedido (quantidade x preco)
        }
    ],
    "page": { ---------------------------> Objeto de paginação
        "pagina": 0, --------------------> Página atual do filtro
        "tamanhoPagina": 10, ------------> Quantidade de registros que pode apareser na consulta
        "totalPaginas": 1, --------------> Total de páginas 
        "totalPedidos": 1 ---------------> total de pedidos do cliente
    }
}
```

## 🐘 Como acessar o banco de dados

Host: 0.0.0.0

Porta: 5432

Banco de dados: db_gerenciador_pedidos

Usuário: postgres

Senha: 123456

