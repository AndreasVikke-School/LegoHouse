package data.mappers;

import data.DatabaseConnector;
import data.models.Order;
import data.models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import logic.exceptions.OrderException;

/**
 *
 * @author Andreas Vikke
 */
public class OrderMapper implements DataMapperInterface<Order> {

    private static final DatabaseConnector connector = new DatabaseConnector();

    public OrderMapper(DataSource ds) {
        connector.setDataSource(ds);
    }
    
    @Override
    public int add(Order order) throws OrderException, SQLException {
        try {
            connector.open();
            String SQL = "INSERT INTO orders (userId, width, length, height, doorC, windowC, boundC) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connector.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getWidth());
            ps.setInt(3, order.getLength());
            ps.setInt(4, order.getHeight());
            ps.setBoolean(5, order.isDoor());
            ps.setBoolean(6, order.isWindow());
            ps.setBoolean(7, order.isBound());
            connector.setAutoCommit(false);

            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);

            if (id != -1 && id != 0) {
                connector.commit();
                return id;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (connector != null) {
                connector.rollback();
            }
            throw new OrderException(ex.getMessage());
        } finally {
            connector.close();
        }
        return -1;
    }

    @Override
    public Order get(Order order) throws OrderException, SQLException  {
        try {
            connector.open();
            
            String SQL = "SELECT id, userId, width, length, height, date, shipped, doorC, windowC, boundC FROM orders WHERE id = ?";
            PreparedStatement ps = connector.prepareStatement(SQL);
            ps.setInt(1, order.getId());
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Order dbOrder = new Order(rs.getInt("userId"), rs.getInt("width"), rs.getInt("length"), rs.getInt("height"), rs.getDate("date"), rs.getBoolean("shipped"), rs.getBoolean("doorC"), rs.getBoolean("windowC"), rs.getBoolean("boundC"));
                dbOrder.setId(rs.getInt("id"));
                return dbOrder;
            }
        } catch (SQLException ex) {
            throw new OrderException(ex.getMessage());
        } finally {
            connector.close();
        }
        return null;
    }

    @Override
    public List<Order> getAll() throws OrderException, SQLException  {
        try {
            connector.open();
            List<Order> orders = new ArrayList();
            
            String SQL = "SELECT id, userId, width, length, height, date, shipped, doorC, windowC, boundC FROM orders";
            PreparedStatement ps = connector.prepareStatement(SQL);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Order order = new Order(rs.getInt("userId"), rs.getInt("width"), rs.getInt("length"), rs.getInt("height"), rs.getDate("date"), rs.getBoolean("shipped"), rs.getBoolean("doorC"), rs.getBoolean("windowC"), rs.getBoolean("boundC"));
                order.setId(rs.getInt("id"));
                orders.add(order);
            }
            
            return orders;
        } catch (SQLException ex) {
            throw new OrderException(ex.getMessage());
        } finally {
            connector.close();
        }
    }
    
    public List<Order> getAllByUser(User user) throws OrderException, SQLException  {
        try {
            connector.open();
            List<Order> orders = new ArrayList();
            
            String SQL = "SELECT id, userId, width, length, height, date, shipped, doorC, windowC, boundC FROM orders where userId = ?";
            PreparedStatement ps = connector.prepareStatement(SQL);
            ps.setInt(1, user.getId());
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Order order = new Order(rs.getInt("userId"), rs.getInt("width"), rs.getInt("length"), rs.getInt("height"), rs.getDate("date"), rs.getBoolean("shipped"), rs.getBoolean("doorC"), rs.getBoolean("windowC"), rs.getBoolean("boundC"));
                order.setId(rs.getInt("id"));
                orders.add(order);
            }
            
            return orders;
        } catch (SQLException ex) {
            throw new OrderException(ex.getMessage());
        } finally {
            connector.close();
        }
    }
}
