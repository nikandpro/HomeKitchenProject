package com.github.nikandpro.modelDB;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "tags")
public class Tag {
    @DatabaseField(generatedId = true, columnName = "tagID")
    private int id;

    @DatabaseField(columnName = "title")
    private String title;

    public Tag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id == tag.id &&
                Objects.equals(title, tag.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}

