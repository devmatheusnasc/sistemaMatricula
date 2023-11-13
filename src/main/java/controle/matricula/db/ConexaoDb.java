package controle.matricula.db;

import java.sql.*;

public class ConexaoDb {

    public ConexaoDb() {

    }

    public static Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://172.17.0.5:3306/controle_matriculas";
        String username = "root";
        String password = "root";
        try {
            return DriverManager.getConnection(jdbcUrl, username, password);

        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados.", e);
        }
    }

    public static void closeConnection(Connection conn,
                                       Statement stmt, ResultSet rs) throws SQLException {
        close(conn, stmt, rs);
    }

    public static void closeConnection(Connection conn, Statement stmt) throws SQLException {
        close(conn, stmt, null);
    }

    public static void closeConnection(Connection conn) throws SQLException {
        close(conn, null, null);
    }

    private static void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao fechar recursos do banco de dados.", e);
        }
    }
}