# Projeto de Microserviços com Java e Spring Boot

## 📌 Conceito
Este projeto é uma aplicação baseada em **arquitetura de microserviços** utilizando **Java 21** e **Spring Boot**.  
O objetivo é demonstrar a comunicação entre serviços independentes, orquestrados por um **API Gateway** e registrados em um **Service Discovery** (**Eureka Server**).  

A aplicação possui dois microserviços principais:
- **Products Service**: Responsável por gerenciar produtos (com persistência em banco de dados H2).
- **Orders Service**: Permite a criação de pedidos a partir dos produtos cadastrados (não possui persistência própria).

Toda comunicação entre os clientes e os serviços é feita através do **API Gateway**.

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
   - Comunicação via **REST Template** ou **Feign Client** com o Products Service.
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

### 1️⃣ Pré-requisitos
- **Java 21** instalado.
- **Maven** instalado.
- (Opcional) **Docker** instalado.
- **Postman** para testes.

### 2️⃣ Clonar o repositório
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
