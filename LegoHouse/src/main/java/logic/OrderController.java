package logic;

import data.DataSourceMySql;
import data.mappers.OrderMapper;
import data.models.Order;
import data.models.User;
import java.sql.SQLException;
import java.util.List;
import logic.exceptions.OrderException;

/**
 *
 * @author Andreas Vikke
 */
public class OrderController {
    private static final OrderMapper mapper = new OrderMapper(new DataSourceMySql().getDataSource());

    public static int createOrder(int userId, int length, int width, int height, boolean door, boolean window, boolean bound) throws OrderException, SQLException {
        return mapper.add(new Order(userId, width, length, height, null, null, door, window, bound));
    }
    
    public static Order getOrder(int id) throws OrderException, SQLException  {
        Order o = new Order(0,0,0,0,null, null,false,false,false);
        o.setId(id);
        return mapper.get(o);
    }
    
    public static List<Order> getAllOrders() throws OrderException, SQLException  {
        return mapper.getAll();
    }
    
    public static List<Order> getAllOrdersByUser(User user) throws OrderException, SQLException  {
        return mapper.getAllByUser(user);
    }
    
    public static void shipOrder(int orderId) throws OrderException, SQLException  {
        mapper.shipOrder(orderId);
    }
}
