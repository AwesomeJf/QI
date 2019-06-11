package dao.impl;

import models.User;
import utils.db.Conn;
import utils.db.BaseDao;
import dao.UserDao;

import java.sql.*;

public class UserDaoImpl extends BaseDao implements UserDao {

    private User makeUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setAdmin(resultSet.getBoolean("isAdmin"));
            user.setCreateTime(resultSet.getTimestamp("createTime"));
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
            String rowSql = "SELECT * FROM user WHERE id=?";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setInt(1, id);
            resultSet = this.statement.executeQuery();
            if (resultSet.next()) {
                user = makeUser(resultSet);
            }
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User find(String username, String password) {
        User user = null;
        Conn conn = new Conn();
        ResultSet resultSet;
        try {
            this.connection = conn.getConnection();
            String rowSql = "SELECT * FROM user WHERE username=? and password=?";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setString(1, username);
            this.statement.setString(2, password);
            resultSet = this.statement.executeQuery();
            if (resultSet.next()) {
                user = makeUser(resultSet);
            }
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User find(String username) {
        User user = null;
        Conn conn = new Conn();
        ResultSet resultSet;
        try {
            this.connection = conn.getConnection();
            String rowSql = "SELECT * FROM user WHERE username=?";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setString(1, username);
            resultSet = this.statement.executeQuery();
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
            int index;
            this.connection = con.getConnection();
            String maxIDSql = "SELECT MAX(id) as id from user";
            this.statement = this.connection.prepareStatement(maxIDSql);
            ResultSet sqlRst = this.statement.executeQuery();
            if (sqlRst.next()) {
                index = sqlRst.getInt("id") + 1;
            } else {
                index = 1;
            }
            String rowSql = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setInt(1, index);
            this.statement.setString(2, user.getUsername());
            this.statement.setString(3, user.getPassword());
            this.statement.setBoolean(4, user.getAdmin());
            this.statement.setTimestamp(5, user.getCreateTime());
            this.statement.execute();
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
            String rowSql = "UPDATE user SET username=?, password=?, isAdmin=?, createTime=? WHERE id=?";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setString(1, user.getUsername());
            this.statement.setString(2, user.getPassword());
            this.statement.setBoolean(3, user.getAdmin());
            this.statement.setTimestamp(4, user.getCreateTime());
            this.statement.setInt(5, user.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int pk) {
        Conn con = new Conn();
        try {
            this.connection = con.getConnection();
            // 判断该条记录是否存在
            String findSql = "SELECT * from user WHERE id=?";
            this.statement = this.connection.prepareStatement(findSql);
            this.statement.setInt(1, pk);
            ResultSet resultSet = this.statement.executeQuery();
            if (resultSet.next()) {
                String deleteSql = "DELETE FROM user WHERE id=?";
                this.statement = this.connection.prepareStatement(deleteSql);
                this.statement.setInt(1, pk);
                statement.execute();
                this.connection.close();
                return true;
            } else {
                // 记录不存在
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
