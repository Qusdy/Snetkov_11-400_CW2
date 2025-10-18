package ru.kpfu.itis.snetkov.dto;

public class UserDto {

    private String name;
    private String login;
    private String image;

    public UserDto(String name, String login, String image) {
        this.name = name;
        this.login = login;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}