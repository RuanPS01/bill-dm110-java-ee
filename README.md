# API de Contas

Esta é uma API para gerenciamento de contas.

## Endpoints

### Criar Conta

Cria uma nova conta.

- Método: POST
- URL: `/bill/create`
- Tipo de Conteúdo: application/json
- Parâmetros de URL: Nenhum
- Corpo da Requisição:

  - Exemplo:
    - 
    ```json
    {
      "bill_code": "12345",
      "description": "Conta de água",
      "value": 100.0,
      "expiration_date": "2023-06-30",
      "paid_date": null
    }
    ```

- Possíveis respostas:
  - Código de status: 200 (OK)
    - Exemplo:
      - 
      ```json
      {
        "message": "Conta criada com sucesso"
      }
      ```
  - Código de status: 400 (Erro de Requisição)
    - Exemplo:
      - 
      ```json
      {
        "message": "Falha ao criar a conta"
      }
      ```

### Atualizar Conta

Atualiza uma conta existente.

- Método: PUT
- URL: `/bill/update/{id}`
- Tipo de Conteúdo: application/json
- Parâmetros de URL:
  - `id` (obrigatório): ID da conta a ser atualizada
- Corpo da Requisição:

  - Exemplo:
    - 
    ```json
    {
      "bill_code": "54321",
      "description": "Conta de luz",
      "value": 150.0,
      "expiration_date": "2023-07-31",
      "paid_date": null
    }
    ```

- Possíveis respostas:
  - Código de status: 200 (OK)
    - Exemplo:
      - 
      ```json
      {
        "message": "Conta atualizada com sucesso"
      }
      ```
  - Código de status: 400 (Erro de Requisição)
    - Exemplo:
      - 
      ```json
      {
        "message": "Falha ao atualizar a conta"
      }
      ```

### Listar Contas

Lista todas as contas.

- Método: GET
- URL: `/bill/list`
- Tipo de Conteúdo: application/json
- Parâmetros de URL:
  - `searchText` (opcional): Texto para filtrar contas por código da conta, descrição ou valor
  - `minExpirationDate` (opcional): Data mínima de vencimento para filtrar contas
  - `maxExpirationDate` (opcional): Data máxima de vencimento para filtrar contas
  - `minPaidDate` (opcional): Data mínima de pagamento para filtrar contas
  - `maxPaidDate` (opcional): Data máxima de pagamento para filtrar contas

- Possíveis respostas:
  - Código de status: 200 (OK)
    - Exemplo:
      - 
      ```json
      {
        "bills": [
          {
            "id": "1",
            "bill_code": "12345",
            "description": "Conta de água",
            "value": 100.0,
            "expiration_date": "2023-06-30",
            "paid_date": null
          },
          {
            "id": "2",
            "bill_code": "54321",
            "description": "Conta de luz",
            "value": 150.0,
            "expiration_date": "2023-07-31",
            "paid_date": null
          }
        ]
      }
      ```

### Excluir Conta

Exclui uma conta existente.

- Método: PUT
- URL: `/bill/delete/{id}`
- Tipo de Conteúdo: application/json
- Parâmetros de URL:
  - `id` (obrigatório): ID da conta a ser excluída

- Possíveis respostas:
  - Código de status: 200 (OK)
    - Exemplo:
      - 
      ```json
      {
        "message": "Conta excluída com sucesso"
      }
      ```
  - Código de status: 400 (Erro de Requisição)
    - Exemplo:
      - 
      ```json
      {
        "message": "Falha ao excluir a conta"
      }
      ```


