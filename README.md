# Desafio PicPay Simplificado - Documentação do Projeto

## PicPay Simplificado

O PicPay Simplificado é um serviço que permite transferências de dinheiro entre dois tipos de usuários: comuns e lojistas. O sistema gerencia o cadastro de usuários, valida operações de transferência, verifica saldos antes da transferência e realiza notificações de pagamento.

## Endpoints

### 1. Cadastro de Usuários

#### `POST /users`

Cadastra um novo usuário no sistema.

**Payload:**
```json
{
    "firstName": "Nome",
    "lastName": "Sobrenome",
    "document": "12345678901",
    "email": "usuario@email.com",
    "password": "senha",
    "balance": 100.0,
    "userType": "COMMON"
}
```
### 2. Transferência de Dinheiro

#### POST /transactions

Efetua uma transferência de dinheiro entre usuários.

Payload:

```json

{
    "value": 100.0,
    "payer": 1,
    "payee": 2
}
```

### 3. Consulta de Saldo

#### GET /users/{id}/balance

Consulta o saldo de um usuário específico.

Exemplo de Resposta:

```json
{
    "userId": 1,
    "balance": 150.0
}
```
### Requisitos e Regras:
Nome Completo.
CPF
E-mail
Senha (obrigatórios)

CPF/CNPJ e e-mails devem ser únicos no sistema;
Apenas um cadastro permitido com o mesmo CPF ou endereço de e-mail;

#### Transferência de Dinheiro:
Usuários podem enviar dinheiro para lojistas e entre usuários.
Lojistas só recebem transferências, não enviam dinheiro.
Antes de finalizar a transferência, valida se o usuário tem saldo.
Consulta um serviço autorizador externo antes de finalizar a transferência.
Consulta de Saldo:
Permite consultar o saldo de um usuário específico.

### Mocks Externos
#### Serviço de Autorização:
```
URL: https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc
```


Serviço de Notificação:
```
URL: https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6
```
