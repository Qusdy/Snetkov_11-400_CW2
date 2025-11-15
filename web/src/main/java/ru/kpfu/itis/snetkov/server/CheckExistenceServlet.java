package ru.kpfu.itis.snetkov.server;

import ru.kpfu.itis.snetkov.service.UserService;
import ru.kpfu.itis.snetkov.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/check_existence")
public class CheckExistenceServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        resp.setContentType("text/plain");
        if (userService.getByLogin(login) != null) {
            resp.getWriter().write("already registered");
        } else resp.getWriter().write("ok");
    }
}
