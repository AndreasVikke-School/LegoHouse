package data.mappers;

import data.DatabaseConnector;
import data.models.RoleEnum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import logic.exceptions.UserException;
import data.models.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public class UserMapper implements DataMapperInterface<User, String> {

    private static final DatabaseConnector connector = new DatabaseConnector();

    public UserMapper(DataSource ds) {
        connector.setDataSource(ds);
    }
    
    @Override
    public int add(User user) throws UserException, SQLException {
        try {
            connector.open();
            String SQL = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = connector.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().toString());
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
            throw new UserException(ex.getMessage());
        } finally {
            connector.close();
        }
        return -1;
    }
    
    @Override
    public User get(String email) throws UserException, SQLException  {
        try {
            connector.open();
            
            String SQL = "SELECT id, email, role FROM users WHERE email = ?";
            PreparedStatement ps = connector.prepareStatement(SQL);
            ps.setString(1, email);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                User dbUser = new User(rs.getString("email"), null, RoleEnum.valueOf(rs.getString("role")));
                dbUser.setId(rs.getInt("id"));
                return dbUser;
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        } finally {
            connector.close();
        }
        return null;
    }

    @Override
    public List<User> getAll() throws UserException, SQLException  {
        try {
            connector.open();
            List<User> users = new ArrayList();
            
            String SQL = "SELECT id, email, role FROM users";
            PreparedStatement ps = connector.prepareStatement(SQL);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                User user = new User(rs.getString("email"), null, RoleEnum.valueOf(rs.getString("role")));
                user.setId(rs.getInt("id"));
                users.add(user);
            }
            
            return users;
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        } finally {
            connector.close();
        }
    }

    public static boolean validateUser(String email, String password) throws UserException, SQLException {
        try {
            connector.open();
            String SQL = "SELECT email FROM users WHERE email=? AND password=?";
            PreparedStatement ps = connector.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if(email.equals(rs.getString("email")))
                    return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        } finally {
            connector.close();
        }
        return false;
    }
}
