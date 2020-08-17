package com.github.nikandpro.modelDB;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "orders")
public class Order {
    @DatabaseField(generatedId = true, columnName = "orderID")
    private long id;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "buyer")
    private User buyer;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "food")
    private Food food;
    @DatabaseField(columnName = "portion")
    private int portion;
    @DatabaseField(columnName = "status")
    private String status;

    public Order() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
