package Model;

public class Client {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String id_card;
    private String tel;
    private String birth_date;

    public Client(int id, String username, String password, String name, String email, String id_card, String tel, String birth_date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.id_card = id_card;
        this.tel = tel;
        this.birth_date = birth_date;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getId_card() {
        return id_card;
    }

    public String getTel() {
        return tel;
    }

    public String getBirth_date() {
        return birth_date;
    }
}
