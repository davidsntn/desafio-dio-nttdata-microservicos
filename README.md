# Projeto de Microserviços com Java e Spring Boot

## 📌 Conceito
Este projeto é uma aplicação baseada em **arquitetura de microserviços** utilizando **Java 21** e **Spring Boot**.  
O objetivo é demonstrar a comunicação entre serviços independentes, orquestrados por um **API Gateway** e registrados em um **Service Discovery** (**Eureka Server**).  

A aplicação possui dois microserviços principais:
- **Products Service**: Responsável por gerenciar produtos (com persistência em banco de dados H2).
- **Orders Service**: Permite a criação de pedidos a partir dos produtos cadastrados (não possui persistência própria).

Toda comunicação entre os clientes e os serviços é feita através do **API Gateway**, que também gerencia a **autenticação via Bearer Token**.


---

## 🛠 Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3.x**
- **Spring Cloud Netflix Eureka**
- **Spring Cloud Gateway**
- **Spring Web**
- **Spring Data JPA**
- **Banco de dados H2** (apenas para o Products Service)
- **Maven**
- **Docker** (opcional, para conteinerização)
- **Postman** (para testes)

---

## 📂 Estrutura do Projeto
O projeto está organizado em quatro aplicações independentes:

1. **API Gateway**
   - Porta: `8700`
   - Função: Roteamento de requisições para os microserviços.
   - Configura as rotas no `application.yml` para direcionar o tráfego.

2. **Eureka Server**
   - Porta: `8761`
   - Função: Registro e descoberta de serviços.
   - Todos os microserviços e o API Gateway se registram nele.

3. **Products Service**
   - Porta: `8100`
   - Função: Gerenciar produtos (CRUD básico).
   - Banco de dados: H2 (em memória).
   - Endpoints principais:
     - `GET /api/products` → Lista todos os produtos.
     - `POST /api/products` → Cria um novo produto.

4. **Orders Service**
   - Porta: `8200`
   - Função: Criar pedidos a partir dos produtos disponíveis no Products Service.
   - Comunicação via **Feign Client** com o Products Service.
   - Endpoints principais:
     - `POST /api/orders` → Cria um pedido com base em produtos existentes.
     - `GET /api/orders/{id}` → Consulta um pedido.
    
---

## 🔄 Fluxo de Comunicação
1. O cliente faz uma requisição para o **API Gateway**.
2. O Gateway consulta o **Eureka Server** para localizar o serviço de destino.
3. A requisição é roteada para o microserviço correto (Products ou Orders).
4. Se necessário, um microserviço pode chamar outro (por exemplo, Orders chamando Products).

---

## ▶ Como Executar o Projeto

### Pré-requisitos
- **Java 21** instalado.
- **Maven** instalado.
- (Opcional) **Docker** instalado.
- **Postman** para testes.

## 📬 Testando no Postman

Teste dos principais endpoints da aplicação utilizando o **Postman**.  
Certifique-se de que todos os serviços estão em execução e registrados no **Eureka Server**, e que o **API Gateway** está ativo.

---

## 🔒 Autenticação via API Gateway
O **API Gateway** controla o acesso aos microserviços (**Products** e **Orders**) através de **Bearer Token**.

**Como usar:**
1. Inclua o token no cabeçalho da requisição enviada ao Gateway:

**Exemplo no Postman:**  
- URL: `http://localhost:8700/api/products` ou `http://localhost:8700/api/orders`  
- Authorization: `Auth Type: Bearer Token <meutoken>`

O Gateway valida o token e encaminha a requisição para o microserviço correto.

---

### 1️⃣ Listar Produtos
**Descrição:** Retorna a lista de todos os produtos cadastrados no sistema.  
- **Método:** `GET`  
- **URL:** `http://localhost:8700/api/products`

### 2️⃣ Criar Produto
**Descrição:** Cadastra um novo produto no sistema.
- **Método:** `POST`
- **URL:** `http://localhost:8700/api/products`

### 3️⃣ Criar Pedido
**Descrição:** Cria um pedido a partir de um produto já existente no sistema.
- **Método:** `POST`
- **URL:** `http://localhost:8700/api/orders`

---

### Observação
**Devido a algumas dificuldades relacionadas à infraestrutura, a conteneirização das aplicações com Docker não foi realizada, impossibilitando o uso do Docker Compose para acesso aos serviços. Este projeto será aprimorado em breve, e a conteneirização será implementada.**
