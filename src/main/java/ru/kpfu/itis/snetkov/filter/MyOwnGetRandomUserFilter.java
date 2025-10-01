package ru.kpfu.itis.snetkov.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebFilter(urlPatterns = "/main", filterName = "MyFilter")
public class MyOwnGetRandomUserFilter implements Filter {
    private List<String> users;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        users = new ArrayList<>(List.of("admin"));
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Random rand = new Random();
        String nameOfRandomUser = users.get(rand.nextInt(users.size()));
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session != null) {
            users.add((String) session.getAttribute("user"));
            request.setAttribute("rec_user", nameOfRandomUser);
        } else {
            request.setAttribute("rec_user", "а неавторизованным нет рекомендованных друзей");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
