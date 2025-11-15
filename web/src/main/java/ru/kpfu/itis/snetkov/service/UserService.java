package ru.kpfu.itis.snetkov.service;

import ru.kpfu.itis.snetkov.dto.UserDto;
import ru.kpfu.itis.snetkov.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    boolean save(User user);
    UserDto getByLogin(String login);
    String getPasswordByLogin(String login);
    public boolean authenticate(String login, String password);
}
