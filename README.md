# 🧭 Sistema de Doações — API REST

Este projeto consiste em um **sistema simples de gerenciamento de doações**, desenvolvido com foco em **aprendizado prático**, **modelagem de entidades**, **relacionamentos entre dados** e **regras de negócio**.

O sistema simula um cenário envolvendo **doadores**, **instituições** e **doações**, garantindo validações, integridade relacional e consistência transacional.

---

## 🎯 Objetivos do Projeto

- Praticar fundamentos de desenvolvimento back-end  
- Trabalhar relacionamento entre entidades  
- Aplicar regras de negócio e validações  
- Garantir integridade e consistência dos dados  
- Consolidar conceitos de API REST com Spring Boot  

---

## 🧩 Entidades do Sistema

### 🧍‍♂️ Doador
- Nome obrigatório  
- E-mail, CPF e telefone únicos  
- Pode realizar múltiplas doações  
- Não pode ser excluído se houver doações associadas

```json
{
  "nome": "João da Silva",
  "email": "joao.silva@email.com",
  "cpf": "529.982.247-25",
  "telefone": "11987654321"
}
```

### 🏢 Instituição
- Nome e CNPJ válidos  
- CNPJ, e-mail e telefone únicos  
- Pode receber múltiplas doações  
- Não pode ser removida se possuir doações registradas  

```json
{
  "nome": "Empresa Exemplo LTDA",
  "cnpj": "12.345.678/0001-95",
  "email": "contato@empresaexemplo.com",
  "telefone": "11987654321",
  "endereco": "Rua das Flores, 123 - Centro, São Paulo - SP",
  "cep": "01001-000"
}
```
### 💸 Doação
- Vinculada a um doador e a uma instituição existentes  
- Valor maior que zero  
- Data não pode ser futura  
- Armazena valor, descrição, data e método de pagamento  

---

## ⚙️ Funcionalidades Implementadas

- Cadastro, atualização, consulta e exclusão controlada  
- Registro de doações com validações  
- Consultas por doador, instituição e período  
- Cálculo do total de doações por instituição  

---

## 🔗 Rotas da API

### 🏢 Instituição (`/instituicao`)

| Método | Rota | Descrição |
|------|------|-----------|
| POST | `/salvar` | Cadastra uma nova instituição |
| PUT | `/atualizar-cnpj/{cnpj}` | Atualiza instituição pelo CNPJ |
| GET | `/buscar-cnpj/{cnpj}` | Busca instituição pelo CNPJ |
| GET | `/buscar-nome/{nome}` | Busca instituição pelo nome |
| GET | `/listar-todos` | Lista todas as instituições |
| DELETE | `/deletar-cnpj/{cnpj}` | Remove instituição pelo CNPJ |

---

### 🧍‍♂️ Doador (`/doador`)

| Método | Rota | Descrição |
|------|------|-----------|
| POST | `/salvar` | Cadastra um novo doador |
| PUT | `/atualizar-cpf/{cpf}` | Atualiza doador pelo CPF |
| GET | `/buscar/{id}` | Busca doador pelo ID |
| GET | `/buscar-cpf/{cpf}` | Busca doador pelo CPF |
| GET | `/listar-todos` | Lista todos os doadores |
| DELETE | `/deletar-cpf/{cpf}` | Remove doador pelo CPF |

---

### 💸 Doação (`/doacao`)

| Método | Rota | Descrição |
|------|------|-----------|
| POST | `/nova-doacao/{cpf}/{cnpj}` | Registra uma nova doação |
| PUT | `/atualizacao-doacao/{id}/{cpf}/{cnpj}` | Atualiza uma doação |
| GET | `/buscar/{id}` | Busca doação pelo ID |
| GET | `/buscar-cpf/{cpf}` | Lista doações por doador |
| GET | `/buscar-cnpj/{cnpj}` | Lista doações por instituição |
| GET | `/listar-todos` | Lista todas as doações |
| GET | `/listar-por-periodo/{cpf}/{dataInicio}/{dataFinal}` | Lista doações por período |
| GET | `/soma-total-instituicao-doacao/{cnpj}` | Soma total de doações por instituição |
| DELETE | `/excluir-id/{id}` | Remove uma doação pelo ID |

---

## 🔒 Validações e Regras de Negócio

- Campos obrigatórios não podem ser nulos ou vazios  
- Validações com mensagens claras de erro  
- Integridade referencial entre doações, doadores e instituições  
- Exclusões condicionadas à inexistência de vínculos  
- Operações transacionais para evitar gravações parciais  

---

## 🛠️ Tecnologias Utilizadas

- Java  
- Spring Boot  
- Spring Data JPA  
- Hibernate  
- Bean Validation  
- Swagger / OpenAPI  
- MySql  

---

## 📌 Observações Finais

Projeto desenvolvido com foco em **organização**, **boas práticas**, **clareza de regras de negócio** e **aprendizado contínuo**, estava passando por um momento na minha vida que eu precisei focar mais nas questões pessoais deixando um pouco a programação de lado, fiz esse projeto com foco em manter uma constância.
