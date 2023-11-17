package controle.matricula.dao.impl;


import controle.matricula.dao.DAO;
import controle.matricula.db.ConexaoDb;
import controle.matricula.model.Usuario;
import controle.matricula.util.exceptions.ValidacaoException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

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

    public Usuario findByNome(String nome) {
        var sql = "SELECT * FROM usuario WHERE nome LIKE ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");

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
        var sql = "SELECT * FROM usuario;";

        List<Usuario> usuarios = new ArrayList<>();

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql);
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
    public void insert(Usuario usuario) {
        var sql = "INSERT INTO usuario (login, senha, nome, email, cargo) VALUES (?, ?, ?, ?, ?);";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            setStatement(usuario, stmt);
            stmt.executeUpdate();

            showMessageDialog(null, "Usuário cadastrado com sucesso.");
        } catch (SQLException e) {
            showMessageDialog(null, "Erro ao cadastar usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
            throw new ValidacaoException("Erro ao cadastar usuário.");        }
    }

    @Override
    public void update(int id, Usuario usuario) {
        var sql = "UPDATE usuario SET nome = ?, email = ?, cargo = ? WHERE id = ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getCargo());
                stmt.setInt(4, id);
                stmt.executeUpdate();

                showMessageDialog(null, "Usuário atualizado com sucesso.");
        } catch (SQLException e) {
            showMessageDialog(null, "Erro ao atualizar usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
            throw new ValidacaoException("Erro ao atualizar usuário.");
        }
    }

    @Override
    public boolean delete(int id) {
        var sql = "DELETE FROM usuario WHERE id = ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            showMessageDialog(null, "Erro ao excluir usuário.", "Erro", ERROR_MESSAGE);
            throw new ValidacaoException("Erro ao excluir usuário.");
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
                    var loginBD = rs.getString("login");
                    var senhaBD = rs.getString("senha");
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
