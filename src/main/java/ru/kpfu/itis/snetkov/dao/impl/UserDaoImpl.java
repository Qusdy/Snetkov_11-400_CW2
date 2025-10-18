package ru.kpfu.itis.snetkov.dao.impl;

import ru.kpfu.itis.snetkov.dao.UserDao;
import ru.kpfu.itis.snetkov.entity.User;
import ru.kpfu.itis.snetkov.util.DatabaseConnectionUtil;
import ru.kpfu.itis.snetkov.util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public List<User> getAll() {
        String sql = "select * from users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    users.add(
                            new User(resultSet.getString("name"),
                                     resultSet.getString("lastname"),
                                     resultSet.getString("login")
                            )
                    );
                }
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(User user) {
        String sql = "insert into users (name, lastname, login, password, image) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());

            preparedStatement.setString(4, PasswordUtil.encrypt(user.getPassword()));
            preparedStatement.setString(5, user.getImage());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
//            return false;
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getByLogin(String login) {
        String sql = "select * from users WHERE login = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("login"),
                        resultSet.getString("image")
                );
            }
            return null;
        } catch (SQLException e) {
            return null;
//            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPasswordByLogin(String login) {
        String sql = "select password from users WHERE login = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getString("password");
            }
            return null;
        } catch (SQLException e) {
            return null;
//            throw new RuntimeException(e);
        }
    }
}
