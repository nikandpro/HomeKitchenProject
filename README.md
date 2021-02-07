# BACKEND HomeKitchen

---

Серверная часть, которая принемает и отвечает на запросы с клиентской части Frontend
  
   + Данная часть написана на языке программирование Java 
   
   + Для хранения данных используется база данных SQLite
  
   + Также для увеличения возможностей взаимодействия Серверной и клиентской частями были использованны фреймворк Javalin и библиотека Jackson
  
**Сдесь реализованы такие группы запросов как:**
  
  1. UserRequest
    
  2. FoodRequest 
  
  3. ReviewsRequest
  
  4. TagRequest
  
  5. SubTagRequest
  
  6. OrderRequest
  
  _В каждой из групп есть запросы CRUD (Create Read Update Delete) и другие дополнительные запросы_
  
 **Пример JSON запросов CRUD класса User:**
  
  * createUser
  
  ` http://159.65.199.127:7123/user/post `
  
  ```
   {
    "mail":" doe@mail.ru",
    "password":"password",
    "status":"customer"
   }
   ```
   
   * readUser
  
  ` http://159.65.199.127:7123/user/get `
  
   ```
   [
    {
        "id": 1,
        "fname": "Елена",
        "lname": "Иванова",
        "patron": "Петровна",
        "mail": "elena1@mail.ru",
        "adress": "Doe street",
        "status": "seller"
    },
    {
        "id": 2,
        "fname": "Светлана",
        "lname": "Калинина",
        "patron": "Юрьевна",
        "mail": " sveta@mail.ru",
        "adress": "Doe street",
        "status": "seller"
    },
    ...
    ]
   ```
   
   * updateUser
  
  ` http://159.65.199.127:7123/user/patch `
  
   ```
   {
    "mail":" doe1@mail.ru",
    "password":"password1",
    "status":"seller"
   }
   ```
   
   * deleteUser
  
  ` http://159.65.199.127:7123/user/delete/[user_id] `
  
  _Это был пример JSON одной из группы запросов_
  
  ### Что происходит на Backend часть помимо запросов?
  
    Помимо запросов серверная часть занимается всей логикой которая происходит на сайте. Например: авторизацией, регистрацией, сортировкой, добавлением блюд и т.д.
  
   
  

