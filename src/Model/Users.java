package Model;

public class Users {
    private int id;
    private String username;
    private String password;
    private String userType;

    public Users(int id, String username, String password, String userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getUserType() {
        return userType;
    }
}
