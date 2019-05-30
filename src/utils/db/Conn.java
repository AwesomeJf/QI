package utils.db;

import java.sql.*;

public class Conn {
    private Connection con;
    private String driverClass;
    private String dbUrl;

    public Conn() {
        this.driverClass = "com.mysql.cj.jdbc.Driver";
        this.dbUrl = "jdbc:mysql://t.finlu.com.cn:3306/QI?user=admin&password=mysqlvip";
        loadDriver();
    }

    public Conn(String driverClass, String dbUrl) {
        this.driverClass = driverClass;
        this.dbUrl = dbUrl;
        loadDriver();
    }

    private void loadDriver() {
        try {
            Class.forName(this.driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(this.dbUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
