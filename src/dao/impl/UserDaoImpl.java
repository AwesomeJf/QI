package dao.impl;

import models.User;
import utils.db.Conn;
import utils.db.BaseDao;
import dao.UserDao;

import java.sql.*;

public class UserDaoImpl extends BaseDao implements UserDao {

    public UserDaoImpl() {
        table = "user";
    }

    private User makeUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setAdmin(resultSet.getBoolean("isAdmin"));
            user.setCreateTime(resultSet.getTimestamp("createTime"));
            user.setLastLoginTime(resultSet.getTimestamp("lastLoginTime"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User find(int id) {
        User user = null;
        Conn conn = new Conn();
        ResultSet resultSet;
        try {
            this.connection = conn.getConnection();
            String rowSql = "SELECT * FROM '?' WHERE id=?";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setString(0, this.table);
            this.statement.setInt(1, id);
            resultSet = this.statement.executeQuery(rowSql);
            if (resultSet.next()) {
                user = makeUser(resultSet);
            }
            this.connection.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User find(String username, String password) {
        User user = null;
        Conn conn = new Conn();
        ResultSet resultSet;
        try {
            this.connection = conn.getConnection();
            String rowSql = "SELECT * FROM ? WHERE username='?' and password='?'";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setString(0, this.table);
            this.statement.setString(1, username);
            this.statement.setString(2, password);
            resultSet = this.statement.executeQuery(rowSql);
            if (resultSet.next()) {
                user = makeUser(resultSet);
            }
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean add(User user) {
        try {
            Conn con = new Conn();
            this.connection = con.getConnection();
            String rowSql = "INSERT INTO ? VALUES (?, '?', '?', ?, '?', '?')";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setString(0, this.table);
            this.statement.setInt(1, user.getId());
            this.statement.setString(2, user.getUsername());
            this.statement.setString(3, user.getPassword());
            this.statement.setInt(4, (user.getAdmin() ? 1 : 0));
            this.statement.setString(5, user.getCreateTime().toString());
            this.statement.setString(6, user.getLastLoginTime().toString());
            this.statement.execute(rowSql);
            this.connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(User user) {
        Conn conn = new Conn();
        try {
            this.connection = conn.getConnection();
            String rowSql = "UPDATE ? SET username='?', password='?', isAdmin=?, createTime='?', lastLoginTime='?' WHERE id=?";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setString(0, this.table);
            this.statement.setString(1, user.getUsername());
            this.statement.setString(2, user.getPassword());
            this.statement.setBoolean(3, user.getAdmin());
            this.statement.setTimestamp(4, user.getCreateTime());
            this.statement.setTimestamp(5, user.getLastLoginTime());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int pk) {
        return super.delete(pk);
    }
}
