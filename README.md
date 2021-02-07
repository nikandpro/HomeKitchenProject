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
  
 **Пример CRUD класса User:**
  
  * createUser
  
  < {
    "mail":" doe@mail.ru",
    "password":"password",
    "status":"customer"
  > }
  

