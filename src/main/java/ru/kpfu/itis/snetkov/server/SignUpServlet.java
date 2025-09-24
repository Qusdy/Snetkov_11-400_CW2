package ru.kpfu.itis.snetkov.server;

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
    private static Map<String, String> data = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("sign_up.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // registration
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && password != null && !data.containsKey(login)) {
            data.put(login, password);
            resp.sendRedirect("login.html");
        }
        // TODO: persist in memory (Map) login + password and after that use it in LoginServlet instead of "login" and "password"
    }

    public static boolean authenticate(String login, String password) {
        String password2 = data.get(login);
        return password2 != null && password2.equals(password);
    }
}
