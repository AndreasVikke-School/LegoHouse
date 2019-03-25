package logic;

import data.DataSourceMySql;
import data.mappers.UserMapper;
import data.models.RoleEnum;
import java.sql.SQLException;
import logic.exceptions.UserException;
import data.models.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Andreas Vikke
 */
public class UserController {

    private static final UserMapper mapper = new UserMapper(new DataSourceMySql().getDataSource());

    public static int createUser(String email, String password) throws UserException, SQLException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return mapper.add(new User(email, passwordHash, RoleEnum.CUSTOMER));
    }

    public static User getUser(String email) throws UserException, SQLException {
        return mapper.get(email);
    }

    public static List<User> getAllUsers(String email) throws UserException, SQLException {
        return mapper.getAll();
    }

    public static boolean validateUser(String email, String password) throws UserException, SQLException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return mapper.validateUser(email, passwordHash);
    }
}
