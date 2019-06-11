package controller;

import dao.PaperDao;
import dao.UserDao;
import dao.impl.PaperDaoImpl;
import dao.impl.UserDaoImpl;
import models.Paper;
import models.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class QIList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=utf-8");
        String paperIdString = request.getParameter("paperId");
        String userIdString = request.getParameter("userId");
        int paperId, userId;
        try {
            paperId = Integer.parseInt(paperIdString);
            userId = Integer.parseInt(userIdString);
        } catch (NumberFormatException e) {
            String err = "{\"errCode\": \"666\"}";
            JSONObject err_response = JSONObject.fromObject(err);
            out.print(err_response.toString());
            return;
        }
        PaperDao paperDao = new PaperDaoImpl();
        Paper paper = paperDao.find(paperId);
        String json_res;
        if (paper == null) {
            json_res = "{}";
        } else {
            if (paper.getUserId() == userId) {
                JSONObject userInfo = JSONObject.fromObject(paper);
                json_res = userInfo.toString();
            } else {
                json_res = "{\"errCode\": \"667\"}";
            }
        }
        out.print(json_res);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=utf-8");
        String title = request.getParameter("title");
        String isPublishString = request.getParameter("isPublish");
        String publishTimeString = request.getParameter("publishTime");
        String endTimeString = request.getParameter("endTime");
        String userIdString = request.getParameter("userId");
        Boolean isPublish;
        Timestamp publishTime, endTime;
        int userId;
        try {
            isPublish = Boolean.valueOf(isPublishString);
            if (publishTimeString.equals("")) {
                publishTime = null;
            } else {
                publishTime = Timestamp.valueOf(publishTimeString);
            }
            if (endTimeString.equals("")) {
                endTime = null;
            } else {
                endTime = Timestamp.valueOf(endTimeString);
            }
            userId = Integer.parseInt(userIdString);
            UserDao userDao = new UserDaoImpl();
            User user = userDao.find(userId);
            if (user == null) {
                String err = "{\"errCode\": \"602\"}";
                JSONObject err_response = JSONObject.fromObject(err);
                out.print(err_response.toString());
                return;
            }
            Paper paper = new Paper();
            paper.setTitle(title);
            paper.setIsPublish(isPublish);
            paper.setPublishTime(publishTime);
            paper.setEndTime(endTime);
            paper.setUserId(userId);
            PaperDao paperDao = new PaperDaoImpl();
            paperDao.add(paper);
            String res = "{\"errCode\": \"0\"}";
            JSONObject json_res = JSONObject.fromObject(res);
            out.print(json_res.toString());
        } catch (Exception e) {
            String err = "{\"errCode\": \"666\"}";
            JSONObject err_response = JSONObject.fromObject(err);
            out.print(err_response.toString());
        }
    }
}
