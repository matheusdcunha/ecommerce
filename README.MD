# Projeto Ecommerce

Esse é um projeto educacional, para entender melhor sobre Spring Data JPA.

O foco aqui é entender melhor sobre os tipos de relacionamentos no JPA, também fazer uma modelagem mais complexa, usando um sistema básico de Ecommerce.

## Tecnologias Utilizadas

- **Java** `21`
- **Spring Boot** `3.5.8`
- **Spring Data JPA**
- **PostgreSQL**
- **Docker**
- **Maven**

## Pré-requisitos

Antes de iniciar o projeto, certifique-se de ter instalado:

- **Java 21** ou superior
- **Docker** e **Docker Compose**
- **Maven** (opcional, pois o projeto inclui o Maven Wrapper)

## Como iniciar o projeto

### 1. Iniciar o banco de dados

Para iniciar o banco de dados PostgreSQL, execute o seguinte comando:

```sh
docker-compose up -d
```

Este comando irá:
- Criar um container PostgreSQL chamado `db_ecommerce`
- Expor a porta `5432`
- Criar o banco de dados `db_ecommerce`
- Configurar as credenciais padrão (usuário: `matheus`, senha: `secret`)

### 2. Executar a aplicação

Você pode executar a aplicação de duas formas:

**Usando Maven Wrapper (recomendado):**

```sh
./mvnw spring-boot:run
```

**Ou usando Maven instalado:**

```sh
mvn spring-boot:run
```

A aplicação será iniciada na porta padrão `8080`.

### 3. Verificar se está funcionando

Após iniciar a aplicação, você pode verificar se está tudo funcionando acessando:

```
http://localhost:8080
```

## Configurações do Banco de Dados

As configurações do banco de dados estão localizadas em `src/main/resources/application.yaml`:

- **URL:** `jdbc:postgresql://localhost:5432/db_ecommerce`
- **Usuário:** `matheus`
- **Senha:** `secret`
- **Porta:** `5432`

