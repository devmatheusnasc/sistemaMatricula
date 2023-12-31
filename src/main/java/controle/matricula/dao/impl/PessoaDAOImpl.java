package controle.matricula.dao.impl;

import controle.matricula.dao.DAO;
import controle.matricula.db.ConexaoDb;
import controle.matricula.model.Pessoa;
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
 * Implementação do DAO para a entidade Pessoa.
 */
public class PessoaDAOImpl implements DAO<Pessoa> {

    /**
     * Obtém uma conexão com o banco de dados.
     *
     * @return Conexão com o banco de dados.
     * @throws SQLException Exceção lançada em caso de erro na conexão.
     */
    private Connection getConnection() throws SQLException {
        return ConexaoDb.getConnection();
    }

    @Override
    public Pessoa findById(int id) {
        var sql = "SELECT * FROM pessoa WHERE idPessoa = ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            return getPessoa(id, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Busca uma pessoa pelo nome.
     *
     * @param nome Nome da pessoa a ser buscada.
     * @return Objeto Pessoa correspondente ao nome fornecido.
     */
    public Pessoa findByNome(String nome) {
        var sql = "SELECT * FROM pessoa WHERE nomePessoa LIKE ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var pessoa = new Pessoa();
                    pessoaResult(rs, pessoa);
                    return pessoa;
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
    public List<Pessoa> findAll() {
        var sql = "SELECT * FROM pessoa;";

        List<Pessoa> pessoas = new ArrayList<>();

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql);
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
                var pessoa = new Pessoa();
                pessoaResult(rs, pessoa);
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    @Override
    public void insert(Pessoa pessoa) {
        var sql = "INSERT INTO pessoa (nomePessoa, endereco, uf, telefone, cpf, email, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pessoa.getNomePessoa());
            stmt.setString(2, pessoa.getEndereco());
            stmt.setString(3, pessoa.getUf());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setString(5, pessoa.getCpf());
            stmt.setString(6, pessoa.getEmail());
            stmt.setString(7, pessoa.getTipo());
            stmt.executeUpdate();
            showMessageDialog(null, "Pessoa cadastrada com sucesso.");
        } catch (SQLException e) {
            showMessageDialog(null, "Erro ao cadastrar pessoa.", "Erro", JOptionPane.ERROR_MESSAGE);
            throw new ValidacaoException("Erro ao cadastrar pessoa.");
        }
    }

    @Override
    public void update(int id, Pessoa pessoa) {
        var sql = "UPDATE pessoa SET nomePessoa = ?, endereco = ?, uf = ?, telefone = ?, email = ? WHERE idPessoa = ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNomePessoa());
            stmt.setString(2, pessoa.getEndereco());
            stmt.setString(3, pessoa.getUf());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setString(5, pessoa.getEmail());
            stmt.setInt(6, id);
            stmt.executeUpdate();

            showMessageDialog(null, "Pessoa atualizada com sucesso.");
        } catch (SQLException e) {
            showMessageDialog(null, "Erro ao atualizar pessoa.", "Erro", JOptionPane.ERROR_MESSAGE);
            throw new ValidacaoException("Erro ao atualizar pessoa.");
        }
    }

    @Override
    public boolean delete(int id) {
        var sql = "DELETE FROM pessoa WHERE idPessoa = ?;";
        var pessoaDAO = new PessoaDAOImpl();
        var pessoa = pessoaDAO.findById(id);
        var mensagemErro = "";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (pessoa.getTipo().equalsIgnoreCase("Professor")) {
                mensagemErro = "Existem disciplinas vinculadas a este professor";
            } else {
                mensagemErro = "Existem matrículas vinculadas a este aluno.";
            }
            showMessageDialog(null, mensagemErro, "Erro", ERROR_MESSAGE);
            throw new ValidacaoException(mensagemErro);
        }
    }

    /**
     * Obtém uma pessoa com base no ID e no PreparedStatement fornecido.
     *
     * @param id   ID da pessoa.
     * @param stmt PreparedStatement.
     * @return Objeto Pessoa correspondente ao ID fornecido.
     * @throws SQLException Exceção lançada em caso de erro no acesso ao banco de dados.
     */
    private Pessoa getPessoa(int id, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, id);

        try (var rs = stmt.executeQuery()) {
            if (rs.next()) {
                var pessoa = new Pessoa();
                pessoaResult(rs, pessoa);
                return pessoa;
            } else {
                return null;
            }
        }
    }

    /**
     * Busca uma pessoa pelo nome completo.
     *
     * @param nome Nome da pessoa a ser buscada.
     * @return Objeto Pessoa correspondente ao nome fornecido.
     */
    public Pessoa findByNomePessoa(String nome) {
        var sql = "SELECT * FROM pessoa WHERE nomePessoa = ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var pessoa = new Pessoa();
                    pessoaResult(rs, pessoa);
                    return pessoa;
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
     * Extrai os resultados do ResultSet e popula um objeto Pessoa.
     *
     * @param rs     ResultSet contendo os resultados da consulta.
     * @param pessoa Objeto Pessoa a ser populado.
     * @throws SQLException Exceção lançada em caso de erro no acesso ao banco de dados.
     */
    private void pessoaResult(ResultSet rs, Pessoa pessoa) throws SQLException {
        pessoa.setIdPessoa(rs.getInt("idPessoa"));
        pessoa.setNomePessoa(rs.getString("nomePessoa"));
        pessoa.setEndereco(rs.getString("endereco"));
        pessoa.setUf(rs.getString("uf"));
        pessoa.setTelefone(rs.getString("telefone"));
        pessoa.setCpf(rs.getString("cpf"));
        pessoa.setEmail(rs.getString("email"));
        pessoa.setTipo(rs.getString("tipo"));
    }
}
