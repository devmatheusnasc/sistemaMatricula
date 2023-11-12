package controle.matricula.dao.impl;


import controle.matricula.dao.daobase.DAO;
import controle.matricula.dao.daobase.UsuarioDAO;
import controle.matricula.db.ConexaoDb;
import controle.matricula.model.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAOImpl implements DAO<Usuario>, UsuarioDAO {

    private Connection getConnection() throws SQLException {
        return ConexaoDb.getConnection();
    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public Usuario findById(int id) {
        return null;
    }

    @Override
    public boolean insert(Usuario usuario) {
        return false;
    }

    @Override
    public boolean update(Usuario usuario) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Usuario findByLoginAndSenha(String login, String senha) {
        var sql = "SELECT login, senha FROM usuario WHERE login = ? AND senha = ?;";
        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var usuario = new Usuario();
                    String loginBD = rs.getString("login");
                    String senhaBD = rs.getString("senha");
                    if (login.equals(loginBD) && senha.equals(senhaBD)) {
                        usuario.setLogin(loginBD);
                        usuario.setSenha(senhaBD);
                        return usuario;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void usuarioResult(ResultSet rs, Usuario usuario) throws SQLException {
        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setLogin(rs.getString("login"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setCargo(rs.getString("cargo"));
        usuario.setEmail(rs.getString("email"));
    }
}
