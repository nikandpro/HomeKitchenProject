package com.github.nikandpro;

import com.github.nikandpro.requestWeb.controller.UserController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7123);

        app.post("user/post" , ctx -> UserController.createUser(ctx));
        app.get("user/get" , ctx -> UserController.getAllUser(ctx));
        app.get("user/get/:id" , ctx -> UserController.getUser(ctx));
        app.patch("user/patch" , ctx -> UserController.updateUser(ctx));
        app.delete("user/delete" , ctx -> UserController.deleteUser(ctx));

        app.post("food/post" , ctx -> UserController.createUser(ctx));
        app.get("food/get" , ctx -> UserController.getUser(ctx));
        app.get("food/get/:id" , ctx -> UserController.getUser(ctx));
        app.patch("food/patch/:id" , ctx -> UserController.updateUser(ctx));
        app.delete("food/delete" , ctx -> UserController.deleteUser(ctx));

    }
}
