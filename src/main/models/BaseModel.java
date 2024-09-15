package main.models;

import java.util.Date;

public class BaseModel {
    private int id;
    private Date insertAt;
    private Date updatedAt;

    public BaseModel(int id, Date insertAt, Date updatedAt) {
        this.id = id;
        this.insertAt = insertAt;
        this.updatedAt = updatedAt;
    }

    public BaseModel(int id) {
        this.id = id;
        this.insertAt = new Date();
        this.updatedAt = new Date();
    }

    public Date getInsertAt() {
        return insertAt;
    }

    public int getId() {
        return id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
