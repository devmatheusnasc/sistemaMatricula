package controle.matricula.dao.impl;

import controle.matricula.dao.daobase.DAO;
import controle.matricula.db.ConexaoDb;
import controle.matricula.model.Disciplina;
import controle.matricula.model.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAOImpl implements DAO<Matricula> {

    private Connection getConnection() throws SQLException {
        return ConexaoDb.getConnection();
    }

    @Override
    public Matricula findById(int id) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM matricula WHERE idMat = ?")) {
            stmt.setInt(1, id);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var matricula = new Matricula();
                    matriculaResult(rs, matricula);
                    return matricula;
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
    public List<Matricula> findAll() {
        List<Matricula> matriculaList = new ArrayList<>();
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM matricula");
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
                var matricula = new Matricula();
                matriculaResult(rs, matricula);
                matriculaList.add(matricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matriculaList;
    }

    @Override
    public boolean insert(Matricula matricula) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement(
                     "INSERT INTO matricula (disciplina, dataMatricula, valorPago, aluno, periodo) VALUES (?, ?, ?, ?, ?)")) {

            setStatement(matricula, stmt);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(Matricula matricula) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement(
                     "UPDATE matricula SET disciplina = ?, dataMatricula = ?, valorPago = ?, aluno = ?, periodo = ? WHERE idMat = ?")) {

            setStatement(matricula, stmt);
            stmt.setInt(6, matricula.getIdMat());
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setStatement(Matricula matricula, PreparedStatement stmt) throws SQLException {
        stmt.setObject(1, matricula.getDisciplina().getCodigo());
        stmt.setDate(2, new Date(matricula.getDataMatricula().getTime()));
        stmt.setDouble(3, matricula.getValorPago());
        stmt.setObject(4, matricula.getAluno().getIdPessoa());
        stmt.setString(5, matricula.getPeriodo());
    }

    @Override
    public boolean delete(int id) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("DELETE FROM matricula WHERE idMat = ?")) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    private void matriculaResult(ResultSet rs, Matricula matricula) throws SQLException {
        var disciplinaDAO = new DisciplinaDAOImpl();
        var disciplina = disciplinaDAO.findById(rs.getInt("disciplina"));

        var alunoDAO = new PessoaDAOImpl();
        var aluno = alunoDAO.findById(rs.getInt("aluno"));

        matricula.setIdMat(rs.getInt("idMat"));
        matricula.setDisciplina(disciplina);
        matricula.setDataMatricula(rs.getDate("dataMatricula"));
        matricula.setValorPago(rs.getDouble("valorPago"));
        matricula.setAluno(aluno);
        matricula.setPeriodo(rs.getString("periodo"));
    }

    public Integer contarMatriculas(Disciplina disciplina) {
        var numeroMatriculas = 0;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Matricula WHERE disciplina = ?")) {

            stmt.setObject(1, disciplina);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                numeroMatriculas = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numeroMatriculas;
    }
}
