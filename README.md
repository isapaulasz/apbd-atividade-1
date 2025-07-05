# Projeto Conta Bancária Spring Boot

Este projeto demonstra operações bancárias com controle de concorrência usando Spring Boot, JPA e H2.

## Integrantes
- Isa Paula da Silva Costa - 202307908151
- Abraão Ribeiro Rodrigues - 202307907274

## Funcionalidades
- CRUD para Conta Bancária
- Operações de depósito e retirada
- Testes de concorrência com e sem versionamento

## Tecnologias
- Spring Boot 3.1.0
- Spring Data JPA
- H2 Database
- JUnit 5

## Como executar
1. Clone o repositório
2. Execute `mvn spring-boot:run`
3. Acesse o H2 Console em `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User: `sa`
   - Password: (vazio)

## Endpoints
- POST `/contas` - Cria nova conta
- POST `/contas/{id}/depositar?valor=X` - Deposita valor X
- POST `/contas/{id}/retirar?valor=X` - Retira valor X
- GET `/contas/{id}` - Obtém conta por ID

Para contas versionadas, use os mesmos endpoints com `/versionada` no caminho.

## Testes de Concorrência
Os testes demonstram o comportamento com e sem versionamento:
- `ConcurrencyTest` - Testa operações concorrentes sem versionamento
- `VersionedConcurrencyTest` - Testa operações concorrentes com versionamento
