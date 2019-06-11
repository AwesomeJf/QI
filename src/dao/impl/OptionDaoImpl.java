package dao.impl;

import java.sql.*;
import models.Option;
import utils.db.BaseDao;
import utils.db.Conn;
import dao.OptionDao;

public class OptionDaoImpl extends BaseDao implements OptionDao {

    public OptionDaoImpl() {
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

    public Option find(int pk) {
        Option option = null;
        Conn conn = new Conn();
        ResultSet resultSet;
        try {
            this.connection = conn.getConnection();
            String rowSql = "SELECT * FROM option WHERE id=?";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setInt(1, pk);
            resultSet = this.statement.executeQuery();
            if (resultSet.next()) {
                option = makeOption(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return option;
    }

    public boolean add(Option option) {
        return true;
    }

    public boolean update(Option option) {
        return true;
    }

    public boolean delete(int pk) {
        return super.delete(pk);
    }
}
