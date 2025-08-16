## Project Management System 

Aplicação **RESTful** desenvolvida em **Java 21** com **Spring Boot 3.3.4**, projetada para gerenciar projetos, membros e tarefas de forma organizada, segura e escalável. O sistema segue boas práticas de arquitetura, validação e design, facilitando manutenção e futuras expansões.

| Modelagem do Sistema | Modelagem do Banco de Dados |
|---------------------|----------------------------|
| ![Sistema](https://github.com/user-attachments/assets/a1bab672-4dbb-4ef5-bc39-4d33fc7fcb14) | ![Banco](https://github.com/user-attachments/assets/7b5cc652-de95-482c-8fb9-64ea9deea5c4) |


### ⚙️ Funcionalidades

- **Projetos:** cadastro, descrição, datas de início e fim, status (PENDING, IN\_PROGRESS, FINISHED)
- **Membros:** cadastro, exclusão lógica e associação a projetos
- **Tarefas:** criação, atribuição de responsáveis, prazo (dias) e status
- **Segurança:** autenticação via **Spring Security** e API Key
- **Consultas personalizadas:** com paginação para otimizar desempenho
- **CRUD completo:** para todas as entidades do sistema

---

### 🛠️ Tecnologias e Conceitos

**Backend:** Java 21, Spring Boot 3.3.4, Spring Data JPA, Spring Security\
**Banco de dados:** H2 (desenvolvimento), MySQL, MongoDB\
**Validações:** Bean Validation API\
**Utilitários:** Lombok, Logback\
**Arquitetura:** DDD (Domain-Driven Design), DTO (Data Transfer Object)\
**Outros:** API REST, Web Service, Maven, Postman, IntelliJ IDEA, Protocolo HTTP

---

### 📦 Instalação e Configuração

### Pré-requisitos

- Java 21 JDK
- Maven
- Docker (opcional, para bancos de dados)
- MySQL ou MongoDB (dependendo do ambiente)

### Passos para rodar localmente

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/project-management-system.git
cd project-management-system
```


2. Execute o projeto usando Maven:

```bash
mvn spring-boot:run
```

3. Acesse a API em:

```
http://localhost:8080/
```

### Rodando com Docker (opcional)

Se preferir usar bancos de dados via Docker, você pode criar contêineres para MySQL e MongoDB:

```bash
# MySQL
docker run --name pms-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=pmanagerdb -p 3306:3306 -d mysql:8

# MongoDB
docker run --name pms-mongo -p 27017:27017 -d mongo:6

## 🔑 Segurança

O sistema utiliza **Spring Security** e autenticação via **API Key**. Recomenda-se gerar suas próprias chaves e armazená-las de forma segura no ambiente.
```
---

### 📝 Observações

- Segue boas práticas de desenvolvimento e arquitetura, sendo facilmente adaptável para cenários corporativos.
- Permite integração com front-ends ou outras APIs via REST.
- Consultas e endpoints estão preparados para lidar com grandes volumes de dados através de paginação e filtros.

---

### 📚 Referência

Curso que inspirou o desenvolvimento: **"Java Backend 360°: Web Services API com Spring Boot e JPA"** - Udemy, instrutor Carlos Eduardo Tosin.
