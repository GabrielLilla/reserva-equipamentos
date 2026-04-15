# Reserva de Equipamentos

API REST para gerenciamento e reserva de equipamentos institucionais, desenvolvida com Spring Boot.

## Tecnologias

- Java 21
- Spring Boot 4.0
- Spring Data JPA
- Spring Security (HTTP Basic)
- H2 (banco em memória — perfil dev)
- Oracle (perfil prod)
- Lombok
- Swagger / OpenAPI 3.0

## Como executar

### Pré-requisitos

- Java 21+
- Maven (ou use o `mvnw` incluso)

### Rodando em modo desenvolvimento (H2)

```bash
./mvnw spring-boot:run
```

A aplicação sobe na porta `8080` com o perfil `dev` ativo e banco H2 em memória.

## Acessos

| Recurso | URL |
|---|---|
| API | http://localhost:8080/api |
| Swagger UI | http://localhost:8080/swagger-ui |
| H2 Console | http://localhost:8080/h2-console |

### H2 Console

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:reserva` |
| Username | `sa` |
| Password | *(em branco)* |

## Autenticação

A API usa HTTP Basic Authentication.

| Usuário | Senha | Role |
|---|---|---|
| `professor` | `123456` | PROF |
| `admin` | `admin123` | ADMIN |

## Endpoints — Equipamentos

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/api/equipamentos` | Lista equipamentos ativos (ou busca por `?q=termo`) | 200 |
| `GET` | `/api/equipamentos/ativos` | Lista apenas equipamentos ativos | 200 |
| `GET` | `/api/equipamentos/{id}` | Busca equipamento por ID | 200 / 404 |
| `POST` | `/api/equipamentos` | Cria novo equipamento | 201 |
| `PUT` | `/api/equipamentos/{id}` | Atualiza equipamento existente | 200 / 404 |
| `DELETE` | `/api/equipamentos/{id}` | Remove equipamento | 204 / 404 |

### Exemplos de requisição

**Criar equipamento**
```http
POST /api/equipamentos
Authorization: Basic professor:123456
Content-Type: application/json

{
  "descricao": "Projetor Sony VPL",
  "ativo": true
}
```

**Atualizar equipamento**
```http
PUT /api/equipamentos/1
Authorization: Basic professor:123456
Content-Type: application/json

{
  "descricao": "Projetor Sony VPL HDMI",
  "ativo": true
}
```

**Deletar equipamento**
```http
DELETE /api/equipamentos/1
Authorization: Basic professor:123456
```

## Estrutura do projeto

```
src/
└── main/
    ├── java/com/fiap/reserva_equipamentos/
    │   └── api/
    │       ├── config/         # Configuração de segurança
    │       ├── controller/     # Endpoints REST
    │       ├── domain/         # Entidades JPA
    │       ├── dto/            # Objetos de transferência de dados
    │       ├── repository/     # Acesso ao banco de dados
    │       ├── service/        # Regras de negócio
    │       └── validation/     # Validações customizadas
    └── resources/
        ├── application.properties        # Configuração base
        ├── application-dev.properties    # Perfil dev (H2)
        ├── application-prod.properties   # Perfil prod (Oracle)
        └── data.sql                      # Dados iniciais (dev)
```

## Perfis

| Perfil | Banco | Ativação |
|---|---|---|
| `dev` | H2 em memória | padrão |
| `prod` | Oracle | `--spring.profiles.active=prod` |
