# **WISH LIST API**


## **Projeto da Disciplna de Desenvolvimento de Sistemas para WEB - SENAI**

1. O sistema deve permitir cadastrar, visualizar, alterar e excluir um usuário.
2. O sistema deve permitir que cada usuário crie diversas listas de desejo (Ex: Mercado, Famácia, etc.)
3. O sistema deve permitir a edição destas listas
4. O sistema deve permitir a exclusão destas listas
5. O sistema deve controlar o status das listas (Concluída ou Pendente)

## **Routes**
`http://localhost:8080/api`

### **Users**

* **Create User - POST** `/users`
```
{
  "name": "Nome Usuário",
  "email": "usuario@email.com",
  "birthdate": "1986-03-07",
  "password": "senha"
}
```
* **Update User - PUT** `/users/{id}`
```
{
	"name": "Nome Usuário Alterado",
	"email": "usuario@email.com",
	"birthdate": "1985-03-07"
}
```
* **Change Password - PATCH** `/users/change-password/{id}`
```
{
	"oldPassword": "senha",
	"newPassword": "novasenha",
	"passwordConfirmation": "novasenha"
}
```
* **Find User By Id - GET** `/users/{id}`
* **Delete User - DEL** `/users/{id}`

### **Lists**

* **Create List - POST** `/lists`
```
{
	"title": "Título Lista",
	"items": ["item 1", "item 2", "item 3"],
	"user_id": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"
}
```
* **Update List - PUT** `/lists/{id}`
```
{
	"title": "Título Lista Atualizado",
	"items": ["item 1", "item 2"],
	"user_id": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"
}
```
* **Change Status - PATCH** `/lists/change-status/{id}`
* **Get List By Id - GET** `/lists/{id}`
* **Get Lists By User - GET** `/lists?user_id=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx`
* **Delete List - DEL** `/lists/{id}`

## Preparação do ambiente
* JDK 17
* Maven
* PostgresSQL

Após clonar o repositório, rodar o comando `mvn clean install` para instalar todas as dependências necessárias.