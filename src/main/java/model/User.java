package model;

public class User{
    public String username = null;
    public String password = null;
    public int role; // 0 for customers, 1 for customer representatives

    public User(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
