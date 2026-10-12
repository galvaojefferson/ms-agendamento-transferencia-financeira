# ms-agendamento-transferencia-financeira

Microsserviço REST para agendamento de transferências financeiras com cálculo de taxa conforme a quantidade de dias até a transferência, e consulta de extrato.

## Stack

| Item | Versão / Ferramenta |
|---|---|
| Linguagem | **Java 11** |
| Framework | Spring Boot **2.7.18** (Web, Data JPA) |
| Build | Maven (via Maven Wrapper `./mvnw`) |
| Banco de dados | H2 em memória |
| Testes | JUnit 5 / spring-boot-starter-test |

## Como subir o projeto

Pré-requisito: JDK 11 (ou superior compatível) instalado.

```bash
./mvnw spring-boot:run
```

Ou gerando o jar:

```bash
./mvnw clean package
java -jar target/ms-agendamento-transferencia-financeira-0.0.1-SNAPSHOT.jar
```

Testes: `./mvnw test`

A aplicação sobe em `http://localhost:8080`. O console do H2 fica em `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:bancario`, usuário `sa`, senha vazia).

## Endpoints

### Agendar transferência
`POST /api/v1/transferencia`

```json
{
  "contaOrigem": "1234567890",
  "contaDestino": "0987654321",
  "valorTransferencia": 1000.00,
  "dataTransferencia": 15,
  "dataAgendamento": "2026-10-12"
}
```

`dataTransferencia` é a quantidade de dias (0 a 50) até a efetivação. Retorna `200 OK`, ou `400` para dia inválido ou sem taxa aplicável.

### Consultar extrato
`GET /api/v1/transferencia/extrato` — lista as transferências ordenadas por `dataAgendamento` decrescente, com `valorTransferencia` e `valorCalculado` (valor + taxa).

```bash
curl http://localhost:8080/api/v1/transferencia/extrato
```

## Regra de taxas

| Dias (`dataTransferencia`) | Taxa |
|---|---|
| 0 | 2,5% |
| 1 a 10 | 0% (não permitido, retorna 400) |
| 11 a 20 | 8,2% |
| 21 a 30 | 6,9% |
| 31 a 40 | 4,7% |
| 41 a 50 | 1,7% |
| Outros | 400 – inválido |

`valorCalculado = valorTransferencia + valorTransferencia × taxa`

## Decisões arquiteturais

- **Arquitetura em camadas** (`controller` → `service` → `repository` → `entity`): separa responsabilidades de API, regra de negócio e persistência.
- **Strategy Pattern para as taxas** (`strategy/`): cada faixa de dias é uma strategy (`@Component`) que estende `FaixaDiasTaxaStrategy`. O `TaxaStrategyResolver` recebe todas via injeção (`List<TaxaStrategy>`) e escolhe a aplicável. Nova faixa = nova classe, sem alterar o serviço (Open/Closed), eliminando cadeias de `if/else`.
- **Enum `TaxaTransferencia`**: centraliza os percentuais como `BigDecimal`, evitando valores mágicos espalhados.
- **`BigDecimal`** para valores monetários, evitando erros de ponto flutuante.
- **DTOs** (`RealizarTransferenciaDTO` com Builder, `ExtratoTransferenciaDTO` imutável) desacoplam o contrato da API da entidade JPA.
- **Tratamento centralizado de erros** com `@RestControllerAdvice` (`GlobalExceptionHandler`), retornando corpo JSON padronizado com `400`.
- **Injeção por construtor**, facilitando testes e imutabilidade.
- **H2 em memória** com `ddl-auto: update`: simplifica execução e avaliação local, sem dependências externas. Os dados são perdidos ao reiniciar; para produção, trocar por um banco persistente (ex.: PostgreSQL) e usar migrations (Flyway/Liquibase).
- **Java 11 / Spring Boot 2.7**: versão LTS estável e amplamente suportada.

## Estrutura

```
src/main/java/com/financeiro/ms_bancario
├── controller   # endpoints REST
├── service      # regras de negócio
├── strategy     # estratégias de cálculo de taxa
├── enums        # percentuais de taxa
├── dto          # contratos de entrada/saída
├── entity       # entidade JPA
├── repository   # acesso a dados (Spring Data)
└── exception    # tratamento global de erros
```
