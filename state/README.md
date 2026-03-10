# State API
This API handles all user, item and wallet based interactions.

# System Design

![System Design.png](images/System%20Design.png)

# Endpoints

| REST Endpoint                               | Meaning / Action                                            |
|---------------------------------------------|-------------------------------------------------------------|
| **GET /users**                | Returns user's username, email and created time             |
| **GET /users/{userId}/wallet**              | Returns user's balance in all currencies                    |
| **GET /users/{userId}/inventory**           | Returns user's full inventory                               |
| **POST /users**                             | Create a new user                                           |
| **POST /users/{userId}/wallet/deposit**     | Add specified amount of currency to user wallet             |
| **POST /users/{userId}/wallet/withdraw**    | Remove specified amount of currency from user wallet        |
| **POST /wallet/transfer**                   | Move specified amount of currency to specified user         |
| **POST /users/{userId}/inventory/add**      | Add specified amount of item to users inventory             |
| **POST /users/{userId}/inventory/remove**   | Remove specified amount of item from users inventory        |
| **POST /users/{userId}/inventory/reserve**  | Move specified amount of item from quantity to reserved     |
| **POST /users/{userId}/inventory/unreserve** | Move specified amount of item from reserved to quantity     |
| **POST /inventory/transfer** | Move specified amount of item from reserved to another user |
| **GET /items**                | Returns info about item with given ID                       |
| **POST /items**                             | Create a new item                                           |
| **DELETE /users/{userId}**                  | Delete a user                                               |
| **DELETE /items/{itemId}**                  | Delete an item                                              |