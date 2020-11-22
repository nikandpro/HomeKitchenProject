package com.github.nikandpro.tools;

import com.github.nikandpro.configuration.DatabaseConfiguration;
import com.github.nikandpro.modelDB.Food;
import com.github.nikandpro.modelDB.Tag;
import com.github.nikandpro.modelDB.User;
import com.github.nikandpro.modelDB.statuses.TagFoodStatus;
import com.github.nikandpro.modelDB.statuses.UserStatus;
import com.github.nikandpro.requestWeb.controller.TagController;
import com.github.nikandpro.requestWeb.controller.TagFoodController;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SecurityService {

    public static String encryption(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
        return hashed;
    }

    public static boolean authentication(Context ctx) throws SQLException {
        boolean check = false;
        String userName = ctx.basicAuthCredentials().getUsername();
        String userPas = ctx.basicAuthCredentials().getPassword();
        System.out.println(userName+" "+userPas);
        for (User us: DatabaseConfiguration.userDao.queryForAll()) {
            System.out.println(us.getFname());
            if (us.getFname().equals(userName) && BCrypt.checkpw(userPas, us.getPassword())) {
                check = true;
            }
        }

        return check;
    }

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

    public static void chooseTagFood(Context ctx, Food food, TagFoodStatus TFstatus) throws IOException, SQLException {

        if (TagFoodStatus.get == TFstatus) {
            System.out.println("noGetFoodStatus");
            Set<String> keys = ctx.queryParamMap().keySet();
            for (String key : keys) {
                String value = ctx.queryParam(key);
                queryTransTag(value);
            }
        } else if (TagFoodStatus.record == TFstatus) {
            Set<String> keys = ctx.queryParamMap().keySet();
            for (String key : keys) {
                String value = ctx.queryParam(key);
                TagFoodController.createTF(ctx, food, queryTransTag(value));
            }
        } else {

        }

    }

    public static Tag queryTransTag(String value) {
        Tag tag = null;
        for (Tag tg: DatabaseConfiguration.tagDao) {
            if (tg.getTitle().equals(value)) {
                tag = tg;
            }
        }
        return tag;
    }

}
