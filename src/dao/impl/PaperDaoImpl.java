package dao.impl;

import dao.PaperDao;
import models.Paper;
import utils.db.BaseDao;
import utils.db.Conn;

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
        Paper paper = null;
        Conn conn = new Conn();
        ResultSet resultSet;
        try {
            this.connection = conn.getConnection();
            String rowSql = "SELECT * FROM paper WHERE id=?";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setInt(1, pk);
            resultSet = this.statement.executeQuery();
            if (resultSet.next()) {
                paper = makePaper(resultSet);
            }
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paper;
    }

    public boolean add(Paper paper) {
        try {
            Conn con = new Conn();
            int index;
            this.connection = con.getConnection();
            String maxIDSql = "SELECT MAX(id) as id from paper";
            this.statement = this.connection.prepareStatement(maxIDSql);
            ResultSet sqlRst = this.statement.executeQuery(maxIDSql);
            if (sqlRst.next()) {
                index = sqlRst.getInt("id") + 1;
            } else {
                index = 1;
            }
            String rowSql = "INSERT INTO paper VALUES (?, ?, ?, ?, ?, ?)";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setInt(1, index);
            this.statement.setString(2, paper.getTitle());
            this.statement.setBoolean(3, paper.getIsPublish());
            this.statement.setTimestamp(4, paper.getPublishTime());
            this.statement.setTimestamp(5, paper.getEndTime());
            this.statement.setInt(6, paper.getUserId());
            this.statement.execute();
            this.connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Paper paper) {
        return true;
    }

    public boolean delete(int pk) {
        return true;
    }
}
