package controle.matricula.db;

import java.sql.*;

/**
 * Classe para gerenciar a conexão com o banco de dados.
 */
public class ConexaoDb {

    /**
     * Construtor padrão.
     */
    public ConexaoDb() {

    }

    /**
     * Obtém uma conexão com o banco de dados.
     *
     * @return A conexão com o banco de dados.
     * @throws SQLException Se ocorrer um erro ao obter a conexão.
     */
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

    /**
     * Fecha a conexão com o banco de dados, o Statement e o ResultSet.
     *
     * @param conn A conexão com o banco de dados.
     * @param stmt O Statement a ser fechado.
     * @param rs   O ResultSet a ser fechado.
     * @throws SQLException Se ocorrer um erro ao fechar os recursos.
     */
    public static void closeConnection(Connection conn,
                                       Statement stmt, ResultSet rs) throws SQLException {
        close(conn, stmt, rs);
    }

    /**
     * Fecha a conexão com o banco de dados e o Statement.
     *
     * @param conn A conexão com o banco de dados.
     * @param stmt O Statement a ser fechado.
     * @throws SQLException Se ocorrer um erro ao fechar os recursos.
     */
    public static void closeConnection(Connection conn, Statement stmt) throws SQLException {
        close(conn, stmt, null);
    }

    /**
     * Fecha a conexão com o banco de dados.
     *
     * @param conn A conexão com o banco de dados.
     * @throws SQLException Se ocorrer um erro ao fechar os recursos.
     */
    public static void closeConnection(Connection conn) throws SQLException {
        close(conn, null, null);
    }

    /**
     * Método privado para fechar a conexão com o banco de dados, o Statement e o ResultSet.
     *
     * @param conn A conexão com o banco de dados.
     * @param stmt O Statement a ser fechado.
     * @param rs   O ResultSet a ser fechado.
     * @throws SQLException Se ocorrer um erro ao fechar os recursos.
     */
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
