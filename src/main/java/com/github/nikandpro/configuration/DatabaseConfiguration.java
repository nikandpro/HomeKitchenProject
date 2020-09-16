package com.github.nikandpro.configuration;


import com.github.nikandpro.modelDB.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseConfiguration {
    public static ConnectionSource connectionSource;
    public static Dao<User, Integer> userDao;
    public static Dao<Food, Integer> foodDao;
    public static Dao<Order, Integer> orderDao;
    public static Dao<Reviews, Integer> reviewsDao;
    public static Dao<Tag, Integer> tagDao;


    static {
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:C:\\Users\\Lenovo\\Desktop\\Project\\ProjectGraNei\\DB\\granei.db");
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, Food.class);
            TableUtils.createTableIfNotExists(connectionSource, Order.class);
            TableUtils.createTableIfNotExists(connectionSource, Reviews.class);
            TableUtils.createTableIfNotExists(connectionSource, Tag.class);

            userDao = DaoManager.createDao(connectionSource, User.class);
            foodDao = DaoManager.createDao(connectionSource, Food.class);
            orderDao = DaoManager.createDao(connectionSource, Order.class);
            reviewsDao = DaoManager.createDao(connectionSource, Reviews.class);
            tagDao = DaoManager.createDao(connectionSource, Tag.class);

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
