package com.github.nikandpro.modelDB;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "food")
public class Food {
    @DatabaseField(generatedId = true, columnName = "foodID")
    private long id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "user")
    private User user;
    @DatabaseField(columnName = "rating")
    private int rating;

    public Food() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
