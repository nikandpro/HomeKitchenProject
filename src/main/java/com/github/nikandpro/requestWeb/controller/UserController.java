package com.github.nikandpro.requestWeb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nikandpro.configuration.DatabaseConfiguration;
import com.github.nikandpro.modelDB.User;
import com.github.nikandpro.modelDB.statuses.UserStatus;
import com.github.nikandpro.tools.ObjectMapperFactory;
import com.github.nikandpro.tools.SecurityService;
import io.javalin.http.Context;

import java.io.IOException;
import java.sql.SQLException;

public class UserController {

    public static void createUser(Context ctx) throws IOException, SQLException {
        String json = ctx.body();
        User user;
        ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(User.class);
        user = obMap.readValue(json, User.class);
        DatabaseConfiguration.userDao.create(user);
        ctx.status(201);

    }

    public static void getUser(Context ctx) throws SQLException, JsonProcessingException {
        if (SecurityService.authentication(ctx)) {
            ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(User.class);
            ctx.result(obMap.writeValueAsString(SecurityService.showUser(DatabaseConfiguration.userDao.queryForAll())));
            ctx.status(200);
        } else {
            ctx.status(401);
        }
    }

    public static void updateUser(Context ctx) throws IOException, SQLException {
        if (SecurityService.authentication(ctx)) {
            String json = ctx.body();
            User user;
            ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(User.class);
            user = obMap.readValue(json, User.class);
            DatabaseConfiguration.userDao.updateId(user, SecurityService.findUser(ctx).getId());
            ctx.status(201);
        }
    }

    public static void deleteUser(Context ctx) throws SQLException {
        UserStatus userStatus;
        if (SecurityService.authentication(ctx)) {
            userStatus = SecurityService.findUser(ctx).getUserStatus();
            if (userStatus == UserStatus.admin) {
                int id = Integer.parseInt(ctx.pathParam("id"));
                DatabaseConfiguration.userDao.deleteById(id);
                ctx.status(204);
            } else {
                DatabaseConfiguration.userDao.delete(SecurityService.findUser(ctx));
                ctx.status(204);
            }
        } else {
            ctx.status(401);
        }
    }
}
