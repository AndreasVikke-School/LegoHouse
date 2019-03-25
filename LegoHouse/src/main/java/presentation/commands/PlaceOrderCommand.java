package presentation.commands;

import data.models.User;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.exceptions.CommandException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import logic.OrderController;
import logic.exceptions.OrderException;

/**
 *
 * @author Andreas Vikke
 */
public class PlaceOrderCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String length = request.getParameter("length");
            String width = request.getParameter("width");
            String height = request.getParameter("height");
            Boolean door = "on".equals(request.getParameter("door"));
            Boolean window = "on".equals(request.getParameter("window"));
            Boolean bound = "on".equals(request.getParameter("bound"));
            
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            
            if(length != null && !length.isEmpty() 
                    && width != null && !width.isEmpty()
                    && height != null && !width.isEmpty()
                    && user != null) {
                int lengthInt = Integer.parseInt(length);
                int widthInt = Integer.parseInt(width);
                int heightInt = Integer.parseInt(height);
                
                if(lengthInt >= 12 && widthInt >= 6 && heightInt >= 1) {
                    int id = OrderController.createOrder(user.getId(), lengthInt, widthInt, heightInt, door, window, bound);

                    request.getRequestDispatcher("/order?orderId=" + id).forward(request, response);
                } else {
                    throw new CommandException("Fields must be above minimum");
                }
            } else {
                throw new CommandException("All fields must to be filled");
            }
        } catch (OrderException | SQLException | ServletException | IOException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
