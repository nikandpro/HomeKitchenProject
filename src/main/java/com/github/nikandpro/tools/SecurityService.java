package com.github.nikandpro.tools;

import com.github.nikandpro.configuration.DatabaseConfiguration;
import com.github.nikandpro.modelDB.Food;
import com.github.nikandpro.modelDB.User;
import com.github.nikandpro.modelDB.statuses.UserStatus;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        User user = null;
        String userLogin = ctx.basicAuthCredentials().getUsername();
        String userPas = ctx.basicAuthCredentials().getPassword();
        for (User us: DatabaseConfiguration.userDao.queryForAll()) {
            if (us.getFname().equals(userLogin)  && BCrypt.checkpw(userPas, us.getPassword())) {
                user = us;
            }
        }
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
}
