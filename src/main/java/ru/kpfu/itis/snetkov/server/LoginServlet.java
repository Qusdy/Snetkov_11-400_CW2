package ru.kpfu.itis.snetkov.server;

import ru.kpfu.itis.snetkov.dto.UserDto;
import ru.kpfu.itis.snetkov.service.UserService;
import ru.kpfu.itis.snetkov.service.impl.UserServiceImpl;
import ru.kpfu.itis.snetkov.util.PasswordUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
//        UserDto user = userService.getByLogin(login);
        if (userService.authenticate(login, password)) {
            // logic to authenticate user

            // session
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", login);

            httpSession.setAttribute("image", userService.getByLogin(login).getImage());
            httpSession.setMaxInactiveInterval(60 * 60);

            // cookie
            Cookie cookie = new Cookie("user", login);
            cookie.setMaxAge(24 * 60 * 60);

            resp.addCookie(cookie);

            resp.sendRedirect("main");
        } else {
            resp.sendRedirect("login");
        }
    }

}
