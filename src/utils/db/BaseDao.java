package utils.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
    protected Connection connection;
    protected Statement statement;
    protected String table;

    protected ResultSet executeQuery(String sql) {
        Conn conn = new Conn();
        ResultSet resultSet = null;
        try {
            this.connection = conn.getConnection();
            this.statement = this.connection.createStatement();
            resultSet = this.statement.executeQuery(sql);
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    protected void deleteOne(int id) {
        try {
            Conn con = new Conn();
            this.connection = con.getConnection();
            String rowSql = "DELETE FROM " + this.table +" WHERE id=" + id;
            this.statement = this.connection.createStatement();
            statement.execute(rowSql);
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
