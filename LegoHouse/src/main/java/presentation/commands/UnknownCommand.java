package presentation.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.exceptions.CommandException;

/**
 *
 * @author Andreas Vikke
 */
public class UnknownCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String msg = "Unknown command. Contact IT";
        throw new CommandException(msg);
    }

}
