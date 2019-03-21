package presentation.commands;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.exceptions.CommandException;

/**
 *
 * @author Andreas Vikke
 */
public class LogoutCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("user");

            response.sendRedirect(request.getContextPath());
        } catch (IOException ex) {
            throw new CommandException(ex.getMessage());
        }
    }

}
