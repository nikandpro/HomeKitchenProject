package com.github.nikandpro.tools;

import com.github.nikandpro.configuration.DatabaseConfiguration;
import com.github.nikandpro.modelDB.User;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

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

}
