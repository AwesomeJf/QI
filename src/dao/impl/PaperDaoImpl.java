package dao.impl;

import dao.PaperDao;
import models.Paper;
import utils.db.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaperDaoImpl extends BaseDao implements PaperDao {
    public PaperDaoImpl() {
        table = "paper";
    }

    private Paper makePaper(ResultSet resultSet) {
        Paper paper = new Paper();
        try {
            paper.setId(resultSet.getInt("id"));
            paper.setTitle(resultSet.getString("title"));
            paper.setIsPublish(resultSet.getBoolean("isPublish"));
            paper.setPublishTime(resultSet.getTimestamp("publishTime"));
            paper.setEndTime(resultSet.getTimestamp("endTime"));
            paper.setUserId(resultSet.getInt("userId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paper;
    }


    public Paper find(int pk) {
        return new Paper();
    }

    public boolean add(Paper paper) {
        return true;
    }

    public boolean update(Paper paper) {
        return true;
    }

    public boolean delete(int pk) {
        return true;
    }
}
