# State API
This API handles all user, item and wallet based interactions.

# System Design

![System Design.png](images/System%20Design.png)

# Endpoints

| REST Endpoint                           | Meaning / Action                                   |
|-----------------------------------------|-----------------------------------------------------|
| **POST /users**                         | Create a new user                                   |
| **POST /items**                         | Create a new item                                   |
| **POST /users/{userId}/wallet**         | Add currency to user’s wallet                       |
| **POST /users/{userId}/inventory**      | Add an item (or stack) to user’s inventory          |
| **DELETE /users/{userId}/wallet**       | Remove currency from user’s wallet                  |
| **DELETE /users/{userId}**              | Delete a user                                       |
| **DELETE /items/{itemId}**              | Delete an item                                      |