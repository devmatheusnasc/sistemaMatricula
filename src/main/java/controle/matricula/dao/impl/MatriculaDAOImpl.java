package controle.matricula.dao.impl;

import controle.matricula.dao.DAO;
import controle.matricula.db.ConexaoDb;
import controle.matricula.model.Matricula;
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
 * Implementação do DAO para a entidade Matricula.
 */
public class MatriculaDAOImpl implements DAO<Matricula> {

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
    public Matricula findById(int id) {
        var sql = "SELECT * FROM matricula WHERE idMat = ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
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
        var sql = "SELECT * FROM matricula;";

        List<Matricula> matriculaList = new ArrayList<>();

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql);
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
    public void insert(Matricula matricula) {

        var sql = "INSERT INTO matricula (disciplina, dataMatricula, valorPago, aluno, periodo) VALUES (?, Now(), ?, ?, ?);";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {

            var idDisciplina = getCodigoDisciplina(matricula.getDisciplina().getNomeDisciplina());

            if (vagasDisponiveis(idDisciplina)) {
                setStatement(matricula, stmt);
                stmt.executeUpdate();

                showMessageDialog(null, "Matrícula cadastrada com sucesso.");
            } else {
                showMessageDialog(null, "Limite de alunos atingido nessa matrícula. Não é possível matricular.",
                        "Erro", ERROR_MESSAGE);
                throw new ValidacaoException("Limite de alunos atingido. Não é possível matricular.");
            }
        } catch (SQLException ex) {
            showMessageDialog(null, "Erro ao cadastar matrícula.", "Erro", JOptionPane.ERROR_MESSAGE);
            throw new ValidacaoException("Erro ao cadastar matrícula.");
        }
    }


    @Override
    public void update(int id, Matricula matricula) {
        var sql = "UPDATE matricula SET disciplina = ?, valorPago = ?, aluno = ?, periodo = ? WHERE idMat = ?;";

        var disciplinaDAO = new DisciplinaDAOImpl();

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var idDisciplina = matricula.getDisciplina().getCodigo();
            var disciplina = disciplinaDAO.findById(idDisciplina);

            if (vagasDisponiveis(idDisciplina) || matricula.getDisciplina().getNomeDisciplina().equalsIgnoreCase(disciplina.getNomeDisciplina())) {
                stmt.setInt(1, matricula.getDisciplina().getCodigo());
                stmt.setDouble(2, matricula.getValorPago());
                stmt.setInt(3, matricula.getAluno().getIdPessoa());
                stmt.setString(4, matricula.getPeriodo());
                stmt.setInt(5, id);
                stmt.executeUpdate();

                showMessageDialog(null, "Matrícula atualizada com sucesso.");
            } else {
                showMessageDialog(null, "Limite de alunos atingido nessa matrícula. Não é possível matricular.",
                        "Erro", ERROR_MESSAGE);
                throw new ValidacaoException("Limite de alunos atingido. Não é possível matricular.");
            }
        } catch (SQLException e) {
            showMessageDialog(null, "Erro ao atualizar a matrícula.", "Erro", JOptionPane.ERROR_MESSAGE);
            throw new ValidacaoException("Erro ao atualizar a matrícula.");
        }
    }

    @Override
    public boolean delete(int id) {
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("DELETE FROM matricula WHERE idMat = ?")) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            showMessageDialog(null, "Erro ao excluir a Matrícula.", "Erro", ERROR_MESSAGE);
            throw new ValidacaoException("Erro ao excluir a Matrícula.");
        }

    }

    /**
     * Retorna o valor total pago em uma disciplina com base no ID da disciplina.
     *
     * @param id ID da disciplina.
     * @return Valor total pago.
     */
    public double findValorPagoByNomeDisciplina(int id) {
        var sql = "SELECT SUM(valorPago) AS somaValorPago FROM matricula WHERE disciplina = ?;";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("somaValorPago");
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retorna uma lista de nomes de disciplinas associadas a um aluno.
     *
     * @param idAluno ID do aluno.
     * @return Lista de nomes de disciplinas associadas ao aluno.
     */
    public List<String> findDisciplinaByAluno(int idAluno) {
        var sql = "SELECT DISTINCT disciplina FROM matricula WHERE aluno = ?;";

        List<String> nomesDisciplinas = new ArrayList<>();
        var disciplinaDAO = new DisciplinaDAOImpl();

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idDisciplina = rs.getInt("disciplina");
                    var disciplina = disciplinaDAO.findById(idDisciplina);
                    nomesDisciplinas.add(disciplina.getNomeDisciplina());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nomesDisciplinas;
    }

    /**
     * Define os parâmetros no PreparedStatement para uma matrícula.
     *
     * @param matricula Matrícula a ser cadastrada ou atualizada.
     * @param stmt      PreparedStatement.
     * @throws SQLException Exceção lançada em caso de erro no acesso ao banco de dados.
     */
    private void setStatement(Matricula matricula, PreparedStatement stmt) throws SQLException {
        stmt.setObject(1, matricula.getDisciplina().getCodigo());
        stmt.setDouble(2, matricula.getValorPago());
        stmt.setObject(3, matricula.getAluno().getIdPessoa());
        stmt.setString(4, matricula.getPeriodo());
    }

    @Override
    public Matricula findByNome(String nome) {
        return null;
    }

    /**
     * Extrai os resultados do ResultSet e popula um objeto Matricula.
     *
     * @param rs        ResultSet contendo os resultados da consulta.
     * @param matricula Objeto Matricula a ser populado.
     * @throws SQLException Exceção lançada em caso de erro no acesso ao banco de dados.
     */
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

    /**
     * Verifica se há vagas disponíveis em uma disciplina.
     *
     * @param codigoDisciplina Código da disciplina.
     * @return True se há vagas disponíveis, False caso contrário.
     */
    private boolean vagasDisponiveis(int codigoDisciplina) {
        var sql = "SELECT COUNT(*) as totalMatriculas FROM matricula WHERE disciplina = ?";

        try (Connection connection = getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoDisciplina);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    var totalMatriculas = resultSet.getInt("totalMatriculas");

                    var limiteAlunos = getLimiteAlunos(codigoDisciplina);

                    return totalMatriculas < limiteAlunos;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtém o limite de alunos para uma disciplina com base no código da disciplina.
     *
     * @param codigoDisciplina Código da disciplina.
     * @return Limite de alunos para a disciplina.
     */
    private int getLimiteAlunos(int codigoDisciplina) {

        var sql = "SELECT limiteAlunos FROM disciplina WHERE codigo = ?";

        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoDisciplina);

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("limiteAlunos");
                } else {
                    throw new SQLException("Limite de alunos não encontrado para a disciplina.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Obtém o código da disciplina com base no nome da disciplina.
     *
     * @param nomeDisciplina Nome da disciplina.
     * @return Código da disciplina.
     * @throws SQLException Exceção lançada em caso de erro no acesso ao banco de dados.
     */
    private int getCodigoDisciplina(String nomeDisciplina) throws SQLException {
        String sql = "SELECT codigo FROM disciplina WHERE nomeDisciplina = ?";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeDisciplina);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("codigo");
                } else {
                    throw new SQLException("Disciplina não encontrada pelo nome: " + nomeDisciplina);
                }
            }
        }
    }
}
