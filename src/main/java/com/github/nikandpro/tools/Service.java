package com.github.nikandpro.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nikandpro.configuration.DatabaseConfiguration;
import com.github.nikandpro.modelDB.*;
import com.github.nikandpro.modelDB.statuses.UserStatus;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service {


    public static UserStatus authorization(Context ctx) throws SQLException {
        return findUser(ctx).getUserStatus();
    }

    public static User findUser(Context ctx) throws SQLException {
        System.out.println("check findUser");
        User user = null;
        String userLogin = ctx.basicAuthCredentials().getUsername();
        String userPas = ctx.basicAuthCredentials().getPassword();
        System.out.println("user: "+userLogin + " " + userPas);
        for (User us: DatabaseConfiguration.userDao.queryForAll()) {
            if (us.getFname().equals(userLogin)  && BCrypt.checkpw(userPas, us.getPassword())) {
                user = us;
            }
        }
        System.out.println("finish findUser "+user);
        return user;
    }


    public static List<User> showUser(List<User> userList) {
        ArrayList<User> userArrayList = new ArrayList<User>();
        for (User user : userList) {
            if (user.getUserStatus() == UserStatus.seller) {
                userArrayList.add(user);
            }
        }
        return userArrayList;
    }

    public static Tag findTag(int idTag) throws SQLException {
        Tag tag = null;
        for (Tag tg : DatabaseConfiguration.tagDao.queryForAll()) {
            if (tg.getId() == idTag) {
                tag = tg;
            }
        }
        System.out.println(tag.getId()+" "+tag.getTitle());
        return tag;
    }



//    public static void chooseTagFood(Context ctx, Food food, TagFoodStatus TFstatus) throws IOException, SQLException {
//
//        if (TagFoodStatus.get == TFstatus) {
//            System.out.println("noGetFoodStatus");
//            Set<String> keys = ctx.queryParamMap().keySet();
//            for (String key : keys) {
//                String value = ctx.queryParam(key);
//                queryTransTag(value);
//            }
//        } else if (TagFoodStatus.record == TFstatus) {
//            Set<String> keys = ctx.queryParamMap().keySet();
//            for (String key : keys) {
//                String value = ctx.queryParam(key);
//                TagFoodController.createTF(ctx, food, queryTransTag(value));
//            }
//        } else {
//
//        }
//
//    }

//    public static Tag queryTransTag(String value) {
//        Tag tag = null;
//        for (Tag tg: DatabaseConfiguration.tagDao) {
//            if (tg.getTitle().equals(value)) {
//                tag = tg;
//            }
//        }
//        return tag;
//    }


    public static List<SubTag> deserialSubTag(String stags) throws SQLException {
        System.out.println("deserialSubTag");
        String subtug;
        stags= stags+",";
        System.out.println(stags);
        List<SubTag> subTagList = new ArrayList<SubTag>();
        while (!stags.isEmpty()) {
            subtug=stags.substring(0,stags.indexOf(","));
            stags=stags.substring(stags.indexOf(",")+1);
            subTagList.add(DatabaseConfiguration.subTagDao.queryForId(Integer.parseInt(subtug)));
        }
        return subTagList;
    }

    public static void handleFoodTag(String stags, Food food) throws SQLException {
        System.out.println("handleFoodTag");
        System.out.println(stags);
        String subtug;
        Tag tag = new Tag();
        Tag_Food tag_food = new Tag_Food();
        tag_food.setId(0);
        tag_food.setFood(food);
//        while (!stags.isEmpty()) {
//            subtug=stags.substring(0,stags.indexOf(":"));
//            stags=stags.substring(stags.indexOf(":")+1);
//            tag_food.setSubTag(DatabaseConfiguration.subTagDao.queryForId(Integer.parseInt(subtug)));
//            DatabaseConfiguration.tagFoodDao.create(tag_food);
//            System.out.println(subtug);
//        }
        for (SubTag subTag: deserialSubTag(stags)) {
            tag_food.setSubTag(subTag);
            DatabaseConfiguration.tagFoodDao.create(tag_food);
        }
    }

    public static List<SubTag> listSubtag(Tag tag) throws SQLException {
        List<SubTag> listSubTag = new ArrayList<>();
        for (SubTag subTag : DatabaseConfiguration.subTagDao.queryForAll()) {
            if (subTag.getTag().equals(tag)) {
                System.out.println("ok");
                listSubTag.add(subTag);
            }
        }
        System.out.println("show ListSUbTag");
        System.out.println(listSubTag.isEmpty());
        for (SubTag subTag: listSubTag) {
            System.out.println(subTag.getId());
            System.out.println(subTag.getName());
            System.out.println(subTag.getTag());
        }
        System.out.println();
        return listSubTag;
    }

    public static void findFoodSubtag(Context ctx) throws SQLException, JsonProcessingException {
        System.out.println("findFoodSubtag");
        List<Food> foods = new ArrayList<Food>();
        System.out.println(ctx.queryParam("subtag"));
        String stringSubtag = ctx.queryParam("subtag");
        System.out.println("stringSubtag"+ stringSubtag);
        Food copy = new Food();
        //System.out.println(body);
//        foods.add(DatabaseConfiguration.foodDao.queryForId(3));
//        foods.add(DatabaseConfiguration.foodDao.queryForId(5));
//        foods.add(DatabaseConfiguration.foodDao.queryForId(8));

        for (Tag_Food foodTag: DatabaseConfiguration.tagFoodDao.queryForAll()) {
            for (SubTag subTag : deserialSubTag(stringSubtag)) {
                if (foodTag.getSubTag().getId()==subTag.getId() && foodTag.getFood().getId()!=copy.getId()) {
                    foods.add(foodTag.getFood());
                    copy=foodTag.getFood();
                }
            }
        }
        ObjectMapper obMap = ObjectMapperFactory.createObjectMapper(Food.class);
        ctx.result(obMap.writeValueAsString(foods));
        ctx.status(200);
    }

    public static boolean checkPostUser(User user) {
        if (user.getMail()!="" && user.getPassword()!="") {
            System.out.println("true check");
            return true;
        } else {
            System.out.println("false check");
            return false;
        }
    }


}
