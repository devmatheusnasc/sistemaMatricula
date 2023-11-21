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

/**
 * Implementação da interface DAO para lidar com entidades de Usuário.
 */
public class UsuarioDAOImpl implements DAO<Usuario> {

    /**
     * Obtém uma conexão com o banco de dados.
     *
     * @return A conexão com o banco de dados.
     * @throws SQLException Se ocorrer um erro ao obter a conexão.
     */
    private Connection getConnection() throws SQLException {
        return ConexaoDb.getConnection();
    }

    /**
     * Busca um usuário pelo ID.
     *
     * @param id O ID do usuário a ser recuperado.
     * @return O usuário com o ID especificado, ou null se não encontrado.
     */
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

    /**
     * Busca um usuário pelo nome (correspondência parcial).
     *
     * @param nome O nome (ou parte do nome) do usuário a ser recuperado.
     * @return O usuário com o nome especificado, ou null se não encontrado.
     */
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

    /**
     * Busca todos os usuários no banco de dados.
     *
     * @return Uma lista de todos os usuários no banco de dados.
     */
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

    /**
     * Insere um novo usuário no banco de dados.
     *
     * @param usuario O usuário a ser inserido.
     */
    @Override
    public void insert(Usuario usuario) {
        var sql = "INSERT INTO usuario (login, senha, nome, email, cargo) VALUES (?, ?, ?, ?, ?);";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            setStatement(usuario, stmt);
            stmt.executeUpdate();

            showMessageDialog(null, "Usuário cadastrado com sucesso.");
        } catch (SQLException e) {
            showMessageDialog(null, "Erro ao cadastrar usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
            throw new ValidacaoException("Erro ao cadastrar usuário.");
        }
    }

    /**
     * Atualiza as informações de um usuário no banco de dados.
     *
     * @param id      O ID do usuário a ser atualizado.
     * @param usuario O objeto contendo as novas informações do usuário.
     */
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

    /**
     * Exclui um usuário do banco de dados com base no ID.
     *
     * @param id O ID do usuário a ser excluído.
     * @return True se o usuário foi excluído com sucesso, False caso contrário.
     */
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

    /**
     * Busca um usuário pelo login e senha.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return O usuário com o login e senha especificados, ou null se não encontrado.
     */
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

    /**
     * Preenche um objeto PreparedStatement com os valores do usuário.
     *
     * @param usuario O usuário com os valores a serem definidos no PreparedStatement.
     * @param stmt    O PreparedStatement a ser preenchido.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    private void setStatement(Usuario usuario, PreparedStatement stmt) throws SQLException {
        stmt.setObject(1, usuario.getLogin());
        stmt.setString(2, usuario.getSenha());
        stmt.setString(3, usuario.getNome());
        stmt.setString(4, usuario.getEmail());
        stmt.setString(5, usuario.getCargo());
    }

    /**
     * Preenche um objeto Usuário com os resultados de uma consulta ResultSet.
     *
     * @param rs      O ResultSet contendo os dados do usuário.
     * @param usuario O objeto Usuário a ser preenchido.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    private void usuarioResult(ResultSet rs, Usuario usuario) throws SQLException {
        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setLogin(rs.getString("login"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setCargo(rs.getString("cargo"));
        usuario.setEmail(rs.getString("email"));
    }
}
