package com.github.nikandpro.requestWeb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nikandpro.configuration.DatabaseConfiguration;
import com.github.nikandpro.modelDB.Food;
import com.github.nikandpro.modelDB.Tag;
import com.github.nikandpro.modelDB.Tag_Food;
import com.github.nikandpro.modelDB.User;
import com.github.nikandpro.modelDB.statuses.UserStatus;
import com.github.nikandpro.tools.ObjectMapperFactory;
import com.github.nikandpro.tools.SecurityService;
import io.javalin.http.Context;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TagFoodController {

    public static void createTF(Context ctx, Food food, Tag tag) throws IOException, SQLException {
        String json = ctx.body();
        Tag_Food tagFood = null;
        tagFood.setId(0);
        tagFood.setFood(food);
        tagFood.setTag(tag);
        DatabaseConfiguration.foodDao.create(food);

    }

    public static ArrayList<Food> getTF(Context ctx, Tag tag) throws SQLException, JsonProcessingException {
        //сортирует food по тэгу и отдает лист
        ArrayList<Food> foods = null;
        for (Tag_Food tf: DatabaseConfiguration.tagFoodDao) {
            if (tf.getTag().getId() == tag.getId()) {
                foods.add(tf.getFood());
            }
        }
        return foods;
    }


    public static void deleteTF(Context ctx) throws SQLException {

    }

}
