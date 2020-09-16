package com.github.nikandpro.modelDB;

import com.github.nikandpro.modelDB.statuses.UserStatus;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User {
    @DatabaseField(generatedId = true, columnName = "UserID")
    private int id;
    @DatabaseField(columnName = "firstName")
    private String fname;
    @DatabaseField(columnName = "lastName")
    private String lname;
    @DatabaseField(columnName = "patronymic")
    private String patronymic;
    @DatabaseField(columnName = "mail")
    private String mail;
    @DatabaseField(columnName = "asdress")
    private String adress;
    @DatabaseField(columnName = "password")
    private String password;
    @DatabaseField(columnName = "userStatus")
    private UserStatus userStatus;

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", mail='" + mail + '\'' +
                ", adress='" + adress + '\'' +
                ", password='" + password + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}
