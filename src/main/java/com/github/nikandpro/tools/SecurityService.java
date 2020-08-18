package com.github.nikandpro.tools;

import com.github.nikandpro.configuration.DatabaseConfiguration;
import com.github.nikandpro.modelDB.User;
import com.github.nikandpro.modelDB.statuses.UserStatus;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class SecurityService {

    public static String encryption(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
        return hashed;
    }

    public static boolean authentication(Context ctx) throws SQLException {
        boolean check=false;
        String userLogin = ctx.basicAuthCredentials().getUsername();
        String userPas = ctx.basicAuthCredentials().getPassword();
        for (User us: DatabaseConfiguration.userDao.queryForAll()) {
            if (us.getFname().equals(userLogin)  && BCrypt.checkpw(userPas, us.getPassword())) {
                check=true;
            }
        }
        return check;
    }

    public static UserStatus authorization(Context ctx) {
        
    }
}
