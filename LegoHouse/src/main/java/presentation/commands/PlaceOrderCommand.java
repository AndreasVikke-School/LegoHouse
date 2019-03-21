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
            
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            
            if(length != null && !length.isEmpty() 
                    && width != null && !width.isEmpty()
                    && height != null && !width.isEmpty()
                    && user != null) {
                
                int id = OrderController.createOrder(user.getId(), Integer.parseInt(length), Integer.parseInt(width), Integer.parseInt(height));
                
                request.getRequestDispatcher("/order?orderId=" + id).forward(request, response);
            } else {
//                response.addHeader("errormessage", "All fields needs to be filled");
//                request.getRequestDispatcher("/error.jsp").forward(request, response);
                throw new CommandException("All fields needs to be filled");
            }
        } catch (OrderException | SQLException | ServletException | IOException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
