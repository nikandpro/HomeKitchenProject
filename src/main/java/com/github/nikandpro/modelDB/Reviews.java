package com.github.nikandpro.modelDB;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "reviewses")
public class Reviews {
    @DatabaseField(generatedId = true, columnName = "reviewsID")
    private long id;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "user")
    private User user;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "food")
    private Food food;
    @DatabaseField(columnName = "text")
    private String text;

    public Reviews() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
