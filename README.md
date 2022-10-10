# BACKEND HomeKitchen

![Рисунок1](https://user-images.githubusercontent.com/49100874/107142618-8a1c0f80-695a-11eb-925b-4e93afa1177f.png)


---

Серверная часть, которая принимает и отвечает на запросы с клиентской части Frontend
  
   + Данная часть написана на языке программирование Java 
   
   + Для хранения данных используется база данных SQLite
  
   + Также для увеличения возможностей взаимодействия Серверной и клиентской частями были использованны фреймворк Javalin и библиотека JDBC
   
   ![Рисунок12](https://user-images.githubusercontent.com/49100874/107142962-75407b80-695c-11eb-96cc-3caa4b970a00.png)

  
**Здесь реализованы такие группы запросов как:**
  
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
  
   Помимо запросов серверная часть занимается всей логикой которая происходит на сайте. 
   Например: авторизацией, регистрацией, сортировкой, добавлением блюд и т.д.
  
   ```
   Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.enableDevLogging();
            javalinConfig.enableCorsForAllOrigins();
            javalinConfig.defaultContentType = "application/json";
   }).start(7123);

        
 app.post("user/post" , ctx -> UserController.createUser(ctx));
 app.get("user/check", ctx -> UserController.checkUser(ctx));
 app.get("user/get" , ctx -> UserController.getAllUser(ctx));
 app.get("user/get/:id" , ctx -> UserController.getUser(ctx));
 app.patch("user/patch" , ctx -> UserController.updateUser(ctx));
 app.delete("user/delete" , ctx -> UserController.deleteUser(ctx));
    
 ``` 
  > **В данном фрагменте показан часть кода, реализации принятие запросов с клиентской части**
  
  ```
    public static void createFood(Context ctx) throws IOException, SQLException {
        if (SecurityService.authentication(ctx)) {
            UserStatus userStatus = Service.findUser(ctx).getUserStatus();
            if (userStatus == UserStatus.admin || userStatus == UserStatus.seller) {
                String json = ctx.body();
                Food food;
                ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(Food.class);
                food = obMap.readValue(json, Food.class);
                food.setUser(Service.findUser(ctx));
                System.out.println(food.toString());
                DatabaseConfiguration.foodDao.create(food);
                //recording Tag_Food in BD
                Service.handleFoodTag(ctx.queryParam("subtag"), food);
                ctx.status(201);
            } else {
                ctx.status(403);
            }
        } else {
            ctx.status(401);
        }
    } 
  ```

  > **В этом фрагменте показана логика создания объекта Food  и записи его в базу данных**
  
  **Ознакомиться с интерфейсом сайта вы можете по ссылке:** [HomeKitchen](http://159.65.199.127/) (На данный момент сайт не функционнирует)
  
  На данном аккаунте Github размещен код клиентская части **(Frontend приложения)**, фронт размещен на этом репозитории: https://github.com/nikandpro/HomeKitchen/tree/master

