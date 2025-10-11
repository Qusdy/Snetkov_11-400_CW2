package ru.kpfu.itis.snetkov.service.impl;

import ru.kpfu.itis.snetkov.dao.UserDao;
import ru.kpfu.itis.snetkov.dao.impl.UserDaoImpl;
import ru.kpfu.itis.snetkov.dto.UserDto;
import ru.kpfu.itis.snetkov.entity.User;
import ru.kpfu.itis.snetkov.service.UserService;
import ru.kpfu.itis.snetkov.util.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(u -> new UserDto(u.getName(), u.getLogin())).toList();
    }

    @Override
    public boolean save(User user) {
        return userDao.save(user);
    }

    @Override
    public UserDto getByLogin(String login) {
        User user = userDao.getByLogin(login);
        return user == null ? null : new UserDto(user.getName(), login);
    }

    @Override
    public String getPasswordByLogin(String login) {
        return userDao.getPasswordByLogin(login);
    }

    @Override
    public boolean authenticate(String login, String password) {
        String storedHash = userDao.getPasswordByLogin(login);
        if (storedHash == null) {
//            throw new RuntimeException("egadfdf");
            return false;
        }
        String inputHash = PasswordUtil.encrypt(password);
        return storedHash.equals(inputHash);
    }
}
