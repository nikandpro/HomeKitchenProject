package com.github.nikandpro.requestWeb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nikandpro.configuration.DatabaseConfiguration;
import com.github.nikandpro.modelDB.User;
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

    public static void getUser(Context ctx) throws SQLException {
        if (SecurityService.authentication(ctx)) {
            ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(User.class);
            ctx.result(obMap.writeValueAsString(DatabaseConfiguration.userDao.queryForAll()));
        } else
            ctx.status(401);
    }
}
