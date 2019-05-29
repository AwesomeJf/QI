package dao;

import models.User;
import utils.db.Conn;
import utils.db.BaseDao;

import java.sql.*;

public class UserDao extends BaseDao {
    private String table;

    public UserDao() {
        table = "user";
    }

    private User makeUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setAdmin(resultSet.getBoolean("isAdmin"));
            user.setCreateTime(resultSet.getTime("createTime"));
            user.setLastLoginTime(resultSet.getTime("lastLoginTime"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findOne(int id) {
        User user = null;
        Conn conn = new Conn();
        try {
            this.connection = conn.getConnection();
            this.statement = this.connection.createStatement();
            String rowSql = "SELECT * FROM " + this.table + " WHERE id=" + id;
            ResultSet resultSet = this.statement.executeQuery(rowSql);
            if (resultSet.next()) {
                user = makeUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    public User findOne(String username, String password) {
        User user = null;
        try {
            String rowSql = "SELECT * FROM " + this.table + "WHERE username=" + "'" + username + "'" + " and password='" + password + "'";
            ResultSet resultSet = this.executeQuery(rowSql);
            if (resultSet.next()) {
                user = makeUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    public User addOne(User user) {
        try {
            Conn con = new Conn();
            this.connection = con.getConnection();
            String rowSql = "INSERT INTO " + this.table + "VALUES ("
                    + user.getUsername() + ","
                    + "'" + user.getPassword() + "',"
                    + "'" + user.getAdmin() + "',"
                    + "'" + user.getCreateTime() + "',"
                    + user.getLastLoginTime() + ")";
            this.statement = this.connection.createStatement();
            statement.execute(rowSql);
            this.connection.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteOne(User user) {
        try {
            Conn con = new Conn();
            this.connection = con.getConnection();
            String rowSql = "DELETE FROM " + this.table +" WHERE id=" + user.getId();
            this.statement = this.connection.createStatement();
            statement.execute(rowSql);
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
