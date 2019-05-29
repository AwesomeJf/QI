package dao;

import models.Option;

import java.sql.*;

import utils.db.BaseDao;

public class OptionDao extends BaseDao {

    public OptionDao() {
        table = "option";
    }

    private Option makeOption(ResultSet resultSet) {
        Option option = new Option();
        try {
            option.setId(resultSet.getInt("id"));
            option.setContent(resultSet.getString("content"));
            option.setSubjectId(resultSet.getInt("subjectId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return option;
    }

    public Option findOne(int id) {
        Option option = null;
        try {
            String rowSql = "SELECT * FROM " + this.table + "WHERE id=" + id;
            ResultSet resultSet = this.executeQuery(rowSql);
            if (resultSet.next()) {
                option = makeOption(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return option;
    }

    public void deleteOne(int id) {
        super.deleteOne(id);
    }
}
