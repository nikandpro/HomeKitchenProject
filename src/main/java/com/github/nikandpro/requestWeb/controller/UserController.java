package com.github.nikandpro.requestWeb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nikandpro.configuration.DatabaseConfiguration;
import com.github.nikandpro.modelDB.User;
import com.github.nikandpro.modelDB.statuses.UserStatus;
import com.github.nikandpro.tools.ObjectMapperFactory;
import com.github.nikandpro.tools.SecurityService;
import io.javalin.http.Context;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    public static void createUser(Context ctx) throws IOException, SQLException {
        String json = ctx.body();
        User user;
        System.out.println("post");
        ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(User.class);
        user = obMap.readValue(json, User.class);
        System.out.println("createUser");
        System.out.println(user.toString());
        DatabaseConfiguration.userDao.create(user);
        ctx.status(201);

    }

    public static void getAllUser(Context ctx) throws SQLException, JsonProcessingException {
        UserStatus userStatus;
            userStatus = SecurityService.findUser(ctx).getUserStatus();
            if (userStatus == UserStatus.admin) {
//                ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(User.class);
//                ctx.result(obMap.writeValueAsString(DatabaseConfiguration.userDao.queryForAll()));
//                ctx.status(200);
                arrayUser(ctx, DatabaseConfiguration.userDao.queryForAll());
//                System.out.println(userStatus+" 1");
            } else {
                System.out.println(userStatus+" 2");
                arrayUser(ctx, SecurityService.showUser(DatabaseConfiguration.userDao.queryForAll()));
            }

    }

    public static void getUser(Context ctx) throws SQLException, JsonProcessingException {
        UserStatus userStatus;

            userStatus = SecurityService.findUser(ctx).getUserStatus();
            int idUser = Integer.parseInt(ctx.pathParam("id"));
            if (userStatus == UserStatus.admin) {
                User user = DatabaseConfiguration.userDao.queryForId(idUser);
                ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(User.class);
                ctx.result(obMap.writeValueAsString(user));
                ctx.status(200);
            } else {
                User user = DatabaseConfiguration.userDao.queryForId(idUser);
                if (user.getUserStatus() == UserStatus.seller) {
                    ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(User.class);
                    ctx.result(obMap.writeValueAsString(user));
                    ctx.status(200);
                } else {
                    ctx.status(403);
                }
            }

    }

    public static void updateUser(Context ctx) throws IOException, SQLException {
        if (SecurityService.authentication(ctx)) {
            String json = ctx.body();
            User user;
            ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(User.class);
            user = obMap.readValue(json, User.class);
//            int id = SecurityService.findUser(ctx).getId();
//            System.out.println(id);
//            System.out.println(user.toString());
            user.setId(SecurityService.findUser(ctx).getId());
            DatabaseConfiguration.userDao.update(user);
            ctx.status(201);
        }
    }

    public static void deleteUser(Context ctx) throws SQLException {
        UserStatus userStatus;
        if (SecurityService.authentication(ctx)) {
            userStatus = SecurityService.findUser(ctx).getUserStatus();
            if (userStatus == UserStatus.admin) {
                try {
                    User user = DatabaseConfiguration.userDao.queryForId(Integer.parseInt(ctx.queryParam("id")));
                    System.out.println(user.getId());
                    if (user.getUserStatus() == UserStatus.admin) {
                        ctx.status(403);
                    } else {
                        DatabaseConfiguration.userDao.delete(user);
                        ctx.status(204);
                    }
                } catch (RuntimeException e) {
                    ctx.status(404);
                }
            } else {
                DatabaseConfiguration.userDao.delete(SecurityService.findUser(ctx));
                ctx.status(204);
            }
        } else {
            ctx.status(401);
        }
    }

    public static void arrayUser(Context ctx, List<User> list) throws SQLException, JsonProcessingException {
        ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(User.class);
        ctx.result(obMap.writeValueAsString(list));
        ctx.status(200);
    }
}
