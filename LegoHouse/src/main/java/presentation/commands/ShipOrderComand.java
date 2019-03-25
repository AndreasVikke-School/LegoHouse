package presentation.commands;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.exceptions.CommandException;
import logic.OrderController;
import logic.exceptions.OrderException;

/**
 *
 * @author Andreas Vikke
 */
public class ShipOrderComand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String orderId = request.getParameter("orderId");
            OrderController.shipOrder(Integer.parseInt(orderId));
            
            request.getRequestDispatcher("/account").forward(request, response);
        } catch (OrderException | SQLException | IOException | ServletException ex) {
            ex.printStackTrace();
            throw new CommandException(ex.getMessage());
        }
    }
}
