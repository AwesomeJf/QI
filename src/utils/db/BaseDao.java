package utils.db;

import java.sql.*;

public class BaseDao {
    protected Connection connection;
    protected PreparedStatement statement;
    protected String table;

    protected boolean delete(int pk) {
        try {
            Conn con = new Conn();
            this.connection = con.getConnection();
            String rowSql = "DELETE FROM  WHERE id=?";
            this.statement = this.connection.prepareStatement(rowSql);
            this.statement.setInt(1, pk);
            statement.execute(rowSql);
            this.connection.close();
            return true;
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

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
