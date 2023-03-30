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