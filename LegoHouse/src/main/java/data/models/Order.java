package data.models;

import java.sql.Date;

/**
 *
 * @author Andreas Vikke
 */
public class Order {
    private int id;
    private int userId;
    private int width;
    private int length;
    private int height;
    private Date date;
    private boolean shipped;

    public Order(int userId, int width, int length, int height, Date date, boolean shipped) {
        this.userId = userId;
        this.width = width;
        this.length = length;
        this.height = height;
        this.date = date;
        this.shipped = shipped;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }    
}
