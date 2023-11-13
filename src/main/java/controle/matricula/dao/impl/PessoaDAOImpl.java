package controle.matricula.dao.impl;

import controle.matricula.dao.DAO;
import controle.matricula.db.ConexaoDb;
import controle.matricula.model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAOImpl implements DAO<Pessoa> {

    private Connection getConnection() throws SQLException {
        return ConexaoDb.getConnection();
    }

    @Override
    public Pessoa findById(int id) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM pessoa WHERE idPessoa = ?")) {
            return getPessoa(id, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Pessoa> findAll() {
        List<Pessoa> pessoas = new ArrayList<>();
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM pessoa");
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
    public boolean insert(Pessoa pessoa) {
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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Pessoa pessoa) {

        return false;
    }

    @Override
    public boolean delete(int id) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("DELETE FROM pessoa WHERE id = ?")) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pessoa findByNome(String nome) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM pessoa WHERE nomePessoa = ?")) {
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

    public Pessoa getByProfessor(int id) {
        var sql = "SELECT * FROM pessoa WHERE idPessoa = ? AND tipo = 'Professor';";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            return getPessoa(id, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Pessoa getByAluno(int id) {
        var sql = "SELECT * FROM pessoa WHERE idPessoa = ? AND tipo = 'Aluno';";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            return getPessoa(id, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

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

    public List<String> obterTipoPessoa() {
        var tiposPessoa = new ArrayList<String>();
        var sql = "SELECT descricao FROM tipo_pessoa;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {

            try (var resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    var descricaoTipo = resultSet.getString("descricao");
                    tiposPessoa.add(descricaoTipo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tiposPessoa;
    }

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