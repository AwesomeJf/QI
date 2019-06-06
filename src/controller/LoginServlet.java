package controller;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import models.User;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/login.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.find(username, password);
        if (user != null) {
            userDao.delete(user.getId());
            user.setId(user.getId() + 1);
            userDao.add(user);
            user.setId(user.getId() + 1);
            userDao.add(user);
            request.getRequestDispatcher("/html/404.html")
                    .forward(request, response);

        } else
            response.sendRedirect("/html/index.html");
    }
}
