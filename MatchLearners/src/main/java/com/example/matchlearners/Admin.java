package com.example.matchlearners;

public class Admin extends User {
    private int privileges;

    public Admin(int privileges, int idUser, String username, String email, String password) {
        super(idUser, username, email, password);
        this.privileges = privileges;
    }

    public Admin(int privileges, String username, String email, String password) {
        super(username, email, password);
        this.privileges = privileges;
    }
}
