package presentation.commands;

import data.models.RoleEnum;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.UserController;
import logic.exceptions.CommandException;
import logic.exceptions.UserException;
import data.models.User;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author Andreas Vikke
 */
public class LoginCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            boolean valid = UserController.validateUser(email, password);

            if (valid) {
                User user = UserController.getUser(email);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                if (user.getRole() == RoleEnum.EMPLOYEE) {
                    response.addHeader("redirect", request.getContextPath() + "/employee");
                    request.getRequestDispatcher("/employee").forward(request, response);
                } else {
                    response.addHeader("redirect", request.getContextPath() + "/customer");
                    request.getRequestDispatcher("/customer").forward(request, response);
                }
            } else {
                throw new CommandException("Incorrect username and/or password");
//                response.addHeader("errormessage", "Incorrect username and/or password");
//                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch (UserException | SQLException | ServletException | IOException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
