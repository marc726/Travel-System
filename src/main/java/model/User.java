package model;

public class User {
    public String username;
    public String password;
    public String name;
    public int role; // 0 for customers, 1 for customer representatives

    public User(String username, String password, String name, int role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }
}