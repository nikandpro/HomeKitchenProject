package com.github.nikandpro.requestWeb.controller;

import io.javalin.http.Context;

import java.sql.SQLException;

public class TagFoodController {

//    public static void createTF(Context ctx, Food food, Tag tag) throws IOException, SQLException {
//        String json = ctx.body();
//        Tag_Food tagFood = new Tag_Food();
//        tagFood.setId(0);
//        tagFood.setFood(food);
//        tagFood.setTag(tag);
//        DatabaseConfiguration.foodDao.create(food);
//
//    }
//
//    public static ArrayList<Food> getTF(Context ctx, Tag tag) throws SQLException, JsonProcessingException {
//        //сортирует food по тэгу и отдает лист
//        ArrayList<Food> foods = null;
//        for (Tag_Food tf: DatabaseConfiguration.tagFoodDao) {
//            if (tf.getTag().getId() == tag.getId()) {
//                foods.add(tf.getFood());
//            }
//        }
//        return foods;
//    }


    public static void deleteTF(Context ctx) throws SQLException {

    }

}
