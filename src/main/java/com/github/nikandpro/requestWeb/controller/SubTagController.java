package com.github.nikandpro.requestWeb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nikandpro.configuration.DatabaseConfiguration;
import com.github.nikandpro.modelDB.SubTag;
import com.github.nikandpro.modelDB.statuses.UserStatus;
import com.github.nikandpro.tools.ObjectMapperFactory;
import com.github.nikandpro.tools.SecurityService;
import com.github.nikandpro.tools.Service;
import io.javalin.http.Context;

import java.io.IOException;
import java.sql.SQLException;

public class SubTagController {
    public static void createTag(Context ctx) throws SQLException, IOException {
        if (SecurityService.authentication(ctx)) {
            UserStatus userStatus = Service.findUser(ctx).getUserStatus();
            if (userStatus == UserStatus.admin) {
                String json = ctx.body();
                SubTag subTag;
                ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(SubTag.class);
                System.out.println("record object subtag");
                subTag = obMap.readValue(json, SubTag.class);
                DatabaseConfiguration.subTagDao.create(subTag);
                ctx.status(201);
            } else {
                ctx.status(403);
            }
        } else {
            ctx.status(401);
        }
    }

    public static void getTagName(Context ctx) throws SQLException, JsonProcessingException {
        System.out.println("Get_SubTag");
        ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(SubTag.class);
        ctx.result(obMap.writeValueAsString(DatabaseConfiguration.subTagDao.queryForAll()));
        ctx.status(200);
    }

    public static void getTagID(Context ctx) throws SQLException, JsonProcessingException {
        System.out.println("Get_SubTag_ID");
        int id = Integer.parseInt(ctx.pathParam("id"));
        ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(SubTag.class);
        ctx.result(obMap.writeValueAsString(DatabaseConfiguration.subTagDao.queryForId(id)));
        ctx.status(200);
    }

    public static void deleteTag(Context ctx) throws SQLException, IOException {
        if (SecurityService.authentication(ctx)) {
            UserStatus userStatus = Service.findUser(ctx).getUserStatus();
            if (userStatus == UserStatus.admin) {
                int idSubTag = Integer.parseInt(ctx.pathParam("id"));
                String json = ctx.body();
                DatabaseConfiguration.subTagDao.deleteById(idSubTag);
                ctx.status(204);
            } else {
                ctx.status(403);
            }
        } else {
            ctx.status(401);
        }
    }
}
