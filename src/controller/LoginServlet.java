package controller;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import models.User;

import java.io.IOException;
import net.sf.json.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.PrintWriter;

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
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=utf-8");
        if (user != null) {
            JSONObject userInfo = JSONObject.fromObject(user);
            out.print(userInfo.toString());
        } else{
            String err = "{\"errCode\": \"611\"}";
            JSONObject err_response = JSONObject.fromObject(err);
            out.print(err_response.toString());
        }
    }
}
