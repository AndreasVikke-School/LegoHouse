package logic;

import data.DataSourceMySql;
import data.mappers.UserMapper;
import data.models.RoleEnum;
import java.sql.SQLException;
import logic.exceptions.UserException;
import data.models.User;
import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public class UserController {
    private static final UserMapper mapper = new UserMapper(new DataSourceMySql().getDataSource());

    public static int createUser(String email, String password) throws UserException, SQLException {
        return mapper.add(new User(email, password, RoleEnum.CUSTOMER));
    }
    
    public static User getUser(String email) throws UserException, SQLException  {
        return mapper.get(new User(email, null, null));
    }
    
    public static List<User> getAllUsers(String email) throws UserException, SQLException  {
        return mapper.getAll();
    }
    
    public static boolean validateUser(String email, String password) throws UserException, SQLException {
        return mapper.validateUser(email, password);
    }
}
