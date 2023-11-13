package controle.matricula.dao.impl;


import controle.matricula.dao.DAO;
import controle.matricula.db.ConexaoDb;
import controle.matricula.model.Matricula;
import controle.matricula.model.Pessoa;
import controle.matricula.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements DAO<Usuario> {

    private Connection getConnection() throws SQLException {
        return ConexaoDb.getConnection();
    }

    @Override
    public Usuario findById(int id) {
        var sql = "SELECT * FROM usuario WHERE id = ?;";
        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var usuario = new Usuario();
                    usuarioResult(rs, usuario);
                    return usuario;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM usuario");
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
                var usuario = new Usuario();
                usuarioResult(rs, usuario);
                usuarios.add(usuario);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public boolean insert(Usuario usuario) {
        var sql = "INSERT INTO usuario (login, senha, nome, email, cargo) VALUES (?, ?, ?, ?, ?);";
        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {

            setStatement(usuario, stmt);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("DELETE FROM usuario WHERE id = ?")) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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


    private void setStatement(Usuario usuario, PreparedStatement stmt) throws SQLException {
        stmt.setObject(1, usuario.getLogin());
        stmt.setString(2, usuario.getSenha());
        stmt.setString(3, usuario.getNome());
        stmt.setString(4, usuario.getEmail());
        stmt.setString(5, usuario.getCargo());
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
