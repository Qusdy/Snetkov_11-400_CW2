package ru.kpfu.itis.snetkov.dao;

import ru.kpfu.itis.snetkov.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    boolean save(User user);

    User getByLogin(String login);

    String getPasswordByLogin(String login);
}
