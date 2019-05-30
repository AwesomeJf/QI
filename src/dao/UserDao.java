package dao;

import models.User;
import utils.db.Conn;
import utils.db.BaseDao;

import java.sql.*;

public class UserDao extends BaseDao {

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
            user.setCreateTime(Timestamp.valueOf(resultSet.getString("createTime")));
            user.setLastLoginTime(Timestamp.valueOf(resultSet.getString("lastLoginTime")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findOne(int id) {
        User user = null;
        try {
            String rowSql = "SELECT * FROM " + this.table + " WHERE id=" + id;
            ResultSet resultSet = this.executeQuery(rowSql);
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

    public User findOne(String username, String password) {
        User user = null;
        try {
            String rowSql = "SELECT * FROM " + this.table + " WHERE username=" + "'" + username + "'" + " and password='" + password + "'";
            ResultSet resultSet = this.executeQuery(rowSql);
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

    public User addOne(User user) {
        try {
            Conn con = new Conn();
            this.connection = con.getConnection();
            String rowSql = "INSERT INTO " + this.table + " VALUES ("
                    + user.getId() + ","
                    + "'" + user.getUsername() + "'" + ","
                    + "'" + user.getPassword() + "',"
                    + (user.getAdmin() ? 1 : 0) + ","
                    + "'" + user.getCreateTime() + "',"
                    + "'" + user.getLastLoginTime() + "'" + ")";
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
        super.deleteOne(user.getId());
    }
}
