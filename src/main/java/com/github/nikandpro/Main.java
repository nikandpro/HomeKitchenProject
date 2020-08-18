package com.github.nikandpro;

import com.github.nikandpro.requestWeb.controller.UserController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7123);

        app.get("user/get" , ctx -> UserController.userGet());
        app.post("user/post" , ctx -> UserController.userGet());
        app.patch("user/patch" , ctx -> UserController.userGet());
        app.delete("user/delete" , ctx -> UserController.userGet());

    }
}
