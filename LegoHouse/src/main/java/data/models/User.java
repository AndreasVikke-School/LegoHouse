package data.models;

/**
 *
 * @author Andreas Vikke
 */
public class User {

    private int id;
    private String email;
    private String password;
    private RoleEnum role;

    public User(String email, String password, RoleEnum role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
