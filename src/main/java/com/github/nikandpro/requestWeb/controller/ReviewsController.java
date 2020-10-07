package com.github.nikandpro.requestWeb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nikandpro.configuration.DatabaseConfiguration;
import com.github.nikandpro.modelDB.Reviews;
import com.github.nikandpro.modelDB.User;
import com.github.nikandpro.tools.ObjectMapperFactory;
import com.github.nikandpro.tools.SecurityService;
import io.javalin.http.Context;

import java.io.IOException;
import java.sql.SQLException;

public class ReviewsController {
    public static void postReviews(Context ctx) throws SQLException, IOException {
        if (SecurityService.authentication(ctx)) {
            String json = ctx.body();
            int idFood = Integer.parseInt(ctx.pathParam("id"));
            Reviews reviews;
            ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(Reviews.class);
            reviews = obMap.readValue(json, Reviews.class);
            reviews.setUser(SecurityService.findUser(ctx));
            reviews.setFood(DatabaseConfiguration.foodDao.queryForId(idFood));
            DatabaseConfiguration.reviewsDao.create(reviews);
            ctx.status(201);
        } else
            ctx.status(401);
    }

    public static void getReviewsAll(Context ctx) throws SQLException, JsonProcessingException {
        ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(Reviews.class);
        ctx.result(obMap.writeValueAsString(DatabaseConfiguration.reviewsDao.queryForAll()));
        ctx.status(200);
    }

    public static void getReviewsId(Context ctx) throws SQLException, JsonProcessingException {
        int idReviews = Integer.parseInt(ctx.pathParam("id"));
        ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(Reviews.class);
        ctx.result(obMap.writeValueAsString(DatabaseConfiguration.reviewsDao.queryForId(idReviews)));
        ctx.status(200);
    }

    public static void updateReviews(Context ctx) throws IOException, SQLException {
        if (SecurityService.authentication(ctx)) {
            String json = ctx.body();
            int idReviews = Integer.parseInt(ctx.pathParam("id"));
            int idFood = Integer.parseInt(ctx.pathParam("id"));
            Reviews reviews;
            ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(Reviews.class);
            reviews = obMap.readValue(json, Reviews.class);
            User user = SecurityService.findUser(ctx);
            reviews.setUser(user);
            reviews.setFood(DatabaseConfiguration.foodDao.queryForId(idFood));
            if (DatabaseConfiguration.reviewsDao.queryForId(idReviews).getUser().getId() == user.getId()) {
                reviews.setId(idReviews);
                DatabaseConfiguration.reviewsDao.update(reviews);
            } else {
                ctx.status(403);
            }
            ctx.status(201);
        } else
            ctx.status(401);
    }

    public static void deleteReviews(Context ctx) throws SQLException {
        if (SecurityService.authentication(ctx)) {
            String json = ctx.body();
            int idReviews = Integer.parseInt(ctx.pathParam("id"));
            Reviews reviews = DatabaseConfiguration.reviewsDao.queryForId(idReviews);
            if (SecurityService.findUser(ctx).getId() == reviews.getUser().getId()) {
                DatabaseConfiguration.reviewsDao.deleteById(idReviews);
            } else {
                ctx.status(403);
            }
            ctx.status(201);
        } else
            ctx.status(401);
    }
}
