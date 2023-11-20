package controle.matricula.dao.impl;

import controle.matricula.dao.DAO;
import controle.matricula.db.ConexaoDb;
import controle.matricula.model.Disciplina;
import controle.matricula.util.exceptions.ValidacaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class DisciplinaDAOImpl implements DAO<Disciplina> {

    private Connection getConnection() throws SQLException {
        return ConexaoDb.getConnection();
    }

    @Override
    public Disciplina findById(int id) {
        var sql = "SELECT * FROM disciplina WHERE codigo = ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var disciplina = new Disciplina();
                    disciplinaResult(rs, disciplina);
                    return disciplina;
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
    public Disciplina findByNome(String nome) {
        var sql = "SELECT * FROM disciplina WHERE nomeDisciplina LIKE ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var disciplina = new Disciplina();
                    disciplinaResult(rs, disciplina);
                    return disciplina;
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
    public List<Disciplina> findAll() {
        var sql = "SELECT * FROM disciplina;";

        List<Disciplina> disciplinaList = new ArrayList<>();

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql);
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
                var disciplina = new Disciplina();
                disciplinaResult(rs, disciplina);
                disciplinaList.add(disciplina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinaList;
    }

    @Override
    public void insert(Disciplina disciplina) {
        var sql = "INSERT INTO disciplina (nomeDisciplina, cargaHoraria, professor, limiteAlunos) VALUES (?, ?, ?, ?);";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, disciplina.getNomeDisciplina());
            stmt.setInt(2, disciplina.getCargaHoraria());
            stmt.setInt(3, disciplina.getProfessor().getIdPessoa());
            stmt.setInt(4, disciplina.getLimiteAlunos());
            stmt.executeUpdate();

            showMessageDialog(null, "Disciplina cadastrada com sucesso.");
        } catch (SQLException e) {
            showMessageDialog(null, "Erro ao cadastrar disciplina.", "Erro", ERROR_MESSAGE);
            throw new ValidacaoException("Erro ao cadastrar pessoa.");
        }
    }

    @Override
    public void update(int id, Disciplina disciplina) {
        var sql = "UPDATE disciplina SET nomeDisciplina = ?, cargaHoraria = ?, professor = ?, limiteAlunos = ? WHERE codigo = ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, disciplina.getNomeDisciplina());
            stmt.setInt(2, disciplina.getCargaHoraria());
            stmt.setInt(3, disciplina.getProfessor().getIdPessoa());
            stmt.setInt(4, disciplina.getLimiteAlunos());
            stmt.setInt(5, id);

            stmt.executeUpdate();

            showMessageDialog(null, "disciplina atualizada com sucesso.");
        } catch (SQLException e) {
            showMessageDialog(null, "Erro ao atualizar disciplina.", "Erro", ERROR_MESSAGE);
            throw new ValidacaoException("Erro ao atualizar disciplina.");
        }
    }

    @Override
    public boolean delete(int id) {
        var sql = "DELETE FROM disciplina WHERE id = ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            showMessageDialog(null, "Existem matrículas vinculadas a essa disciplina.",
                    "Erro", ERROR_MESSAGE);
            throw new ValidacaoException("Existem matriculas vinculadas a essa disciplina.");
        }
    }

    public List<Disciplina> findAllByNome() {
        var sql = "SELECT nomeDisciplina FROM disciplina;";

        List<Disciplina> disciplinas = new ArrayList<>();

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql);
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
                var disciplina = new Disciplina();
                disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinas;
    }

    public List<String> findDisciplinaByProfessor(int idProfessor) {
        var sql = "SELECT DISTINCT nomeDisciplina FROM disciplina WHERE professor = ?;";

        List<String> nomesDisciplinas = new ArrayList<>();
        var disciplinaDAO = new DisciplinaDAOImpl();

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProfessor);

            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    var disciplina = rs.getString("nomeDisciplina");
                    nomesDisciplinas.add(disciplina);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nomesDisciplinas;
    }

    private void disciplinaResult(ResultSet rs, Disciplina disciplina) throws SQLException {
        var pessoaDAO = new PessoaDAOImpl();
        var professor = pessoaDAO.findById(rs.getInt("professor"));

        disciplina.setCodigo(rs.getInt("codigo"));
        disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
        disciplina.setCargaHoraria(rs.getInt("cargaHoraria"));
        disciplina.setProfessor(professor);
        disciplina.setLimiteAlunos(rs.getInt("limiteAlunos"));
    }
}
