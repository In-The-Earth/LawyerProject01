package Model;

public class Lawyer {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String tel;
    private String birth_date;

    public Lawyer(int id, String username, String password, String name, String email, String tel, String birth_date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.birth_date = birth_date;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getBirth_date() {
        return birth_date;
    }
}
