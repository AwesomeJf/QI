package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import models.User;
import net.sf.json.JSONObject;

import java.io.PrintWriter;
import java.sql.Timestamp;

public class RegisterServelet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/register.html").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.find(username);
        if (user != null) {
            // 用户已经存在
            String err = "{\"errCode\": \"601\"}";
            JSONObject err_response = JSONObject.fromObject(err);
            out.print(err_response.toString());
        } else {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAdmin(false);
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            userDao.add(user);
            String err = "{\"errCode\": \"0\"}";
            JSONObject err_response = JSONObject.fromObject(err);
            out.print(err_response.toString());
        }
    }
}
