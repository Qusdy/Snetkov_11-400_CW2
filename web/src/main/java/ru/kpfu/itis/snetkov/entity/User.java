package ru.kpfu.itis.snetkov.entity;

public class User {
    private String name;
    private String lastName;
    private String login;
    private String image;
    private String password;



    public User(String name, String lastName, String login, String image) {
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.image = image;
    }

    public User(String name, String lastName, String login) {
        this.name = name;
        this.lastName = lastName;
        this.login = login;
    }

    public User(String name, String lastName, String login, String image, String password) {
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.image = image;
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
