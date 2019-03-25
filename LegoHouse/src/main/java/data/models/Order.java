package data.models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
    private Date shipped;
    private boolean door;
    private boolean window;
    private boolean bound;

    public Order(int userId, int width, int length, int height, Date date, Date shipped, boolean door, boolean window, boolean bound) {
        this.userId = userId;
        this.width = width;
        this.length = length;
        this.height = height;
        this.date = date;
        this.shipped = shipped;
        this.door = door;
        this.window = window;
        this.bound = bound;
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

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public Date getDate() {
        return date;
    }

    public boolean isShipped() throws ParseException {
        Date nullDate = new Date(19000101);
        return shipped.after(nullDate);
    }

    public boolean isDoor() {
        return door;
    }

    public boolean isWindow() {
        return window;
    }

    public boolean isBound() {
        return bound;
    }
}
