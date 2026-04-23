# 🐶 Petshop API

API REST desenvolvida com Java e Spring Boot para gerenciamento completo de um sistema de petshop, incluindo clientes, pets e serviços.

---

## 📌 Sobre o projeto

Este projeto foi desenvolvido com o objetivo de simular um sistema real de gerenciamento de petshop, aplicando conceitos de arquitetura em camadas, boas práticas de desenvolvimento backend e construção de APIs REST.

A aplicação permite o controle de clientes, seus respectivos pets e os serviços realizados, garantindo organização e rastreabilidade das informações.

---

## 🚀 Funcionalidades

- ✅ Cadastro, listagem, atualização e remoção de clientes
- ✅ Cadastro de pets vinculados a clientes
- ✅ Controle de serviços (ex: banho, tosa, consulta)
- ✅ Relacionamento entre entidades (Cliente ↔ Pet)
- ✅ Estrutura organizada em camadas (Controller, Service, Repository)
- ✅ Validação básica de dados

---

## 🛠️ Tecnologias utilizadas

### Backend
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA

### Banco de Dados
- H2 (ambiente de desenvolvimento)
- Compatível com SQL (MySQL, PostgreSQL)

### Ferramentas
- Postman (testes de API)
- Git e GitHub (versionamento)

---

## 🧱 Arquitetura do projeto

O projeto segue o padrão de arquitetura em camadas:
Controller → Recebe requisições HTTP
Service → Contém regras de negócio
Repository → Comunicação com banco de dados
Model → Entidades do sistema

Estrutura de pastas
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 └── dto/ (se aplicável)

 Como executar o projeto
Pré-requisitos
Java 17+
Maven
Passos
git clone https://github.com/SEU-USUARIO/petshop-api.git
cd petshop-api
./mvnw spring-boot:run

A aplicação estará disponível em:

http://localhost:8080

Endpoints da API
👤 Clientes
Método	Endpoint	Descrição
GET	/clientes	Listar clientes
POST	/clientes	Criar cliente
PUT	/clientes/{id}	Atualizar cliente
DELETE	/clientes/{id}	Remover cliente
🐾 Pets
Método	Endpoint	Descrição
GET	/pets	Listar pets
POST	/pets	Criar pet
✂️ Serviços
Método	Endpoint	Descrição
GET	/servicos	Listar serviços
POST	/servicos	Criar serviço
🧪 Testes da API

Os testes foram realizados utilizando o Postman, simulando requisições HTTP para todos os endpoints.

📷 Exemplos de requisição
Criar cliente
POST /clientes
{
  "nome": "João Silva",
  "email": "joao@email.com"
}
⚠️ Tratamento de erros

Atualmente o projeto possui tratamento básico de erros.

Exemplo:

Recurso não encontrado
Dados inválidos
📌 Melhorias futuras
🔐 Autenticação e autorização com JWT
📄 Documentação automática com Swagger
🐳 Dockerização do projeto
☁️ Deploy em nuvem (Render, Railway ou AWS)
🧪 Testes automatizados (JUnit)
📊 Status do projeto

🚧 Em desenvolvimento contínuo

👨‍💻 Autor

Desenvolvido por Arthur Faleiro
🔗 GitHub: https://github.com/ArthurFaleiro
🌐 Portfólio: https://arthur-faleiro-portfolio.vercel.app/
