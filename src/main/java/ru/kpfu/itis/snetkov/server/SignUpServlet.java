package ru.kpfu.itis.snetkov.server;

import ru.kpfu.itis.snetkov.dao.UserDao;
import ru.kpfu.itis.snetkov.dao.impl.UserDaoImpl;
import ru.kpfu.itis.snetkov.entity.User;
import ru.kpfu.itis.snetkov.service.UserService;
import ru.kpfu.itis.snetkov.service.impl.UserServiceImpl;
import ru.kpfu.itis.snetkov.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SignUp", urlPatterns = "/sign_up")
public class SignUpServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("sign_up.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // registration
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");

        if (!login.isEmpty() && !password.isEmpty() && userService.save(new User(name, lastname, login, password))) {
            resp.sendRedirect("login");
        } else {
            resp.sendRedirect("sign_up");
        }
        // TODO: persist in memory (Map) login + password and after that use it in LoginServlet instead of "login" and "password"
    }
}
