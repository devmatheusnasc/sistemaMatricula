package controle.matricula.dao.impl;

import controle.matricula.dao.DAO;
import controle.matricula.db.ConexaoDb;
import controle.matricula.model.Disciplina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAOImpl implements DAO<Disciplina> {

    private Connection getConnection() throws SQLException {
        return ConexaoDb.getConnection();
    }

    @Override
    public Disciplina findById(int id) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM disciplina WHERE codigo = ?")) {
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

    public Disciplina findByNome(String nome) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM disciplina WHERE nomeDisciplina = ?")) {
            stmt.setString(1, nome);

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
        List<Disciplina> disciplinaList = new ArrayList<>();
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM disciplina");
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
    public boolean insert(Disciplina disciplina) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement(
                     "INSERT INTO disciplina (nomeDisciplina, cargaHoraria, professor, limiteAlunos) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, disciplina.getNomeDisciplina());
            stmt.setInt(2, disciplina.getCargaHoraria());
            stmt.setInt(3, disciplina.getProfessor().getIdPessoa());
            stmt.setInt(4, disciplina.getLimiteAlunos());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(Disciplina disciplina) {

        return false;
    }

    @Override
    public boolean delete(int id) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("DELETE FROM disciplina WHERE id = ?")) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void disciplinaResult(ResultSet rs, Disciplina disciplina) throws SQLException {
        var pessoaDAO = new PessoaDAOImpl();
        var professor = pessoaDAO.findById(rs.getInt("professor"));

        disciplina.setCodigo(rs.getInt("codigo"));
        disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
        disciplina.setCargaHoraria(rs.getInt("cargaHoraria"));
        disciplina.setProfessor(professor);
        disciplina.setLimiteAlunos(50);

    }
}
