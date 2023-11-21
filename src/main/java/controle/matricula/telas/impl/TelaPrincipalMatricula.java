package controle.matricula.telas.impl;

import controle.matricula.dao.impl.DisciplinaDAOImpl;
import controle.matricula.dao.impl.MatriculaDAOImpl;
import controle.matricula.dao.impl.PessoaDAOImpl;
import controle.matricula.model.Disciplina;
import controle.matricula.model.Matricula;
import controle.matricula.model.Pessoa;
import controle.matricula.telas.telabase.TelaPrincipalBase;
import controle.matricula.util.Operacao;
import controle.matricula.util.exceptions.ValidacaoException;
import controle.matricula.util.table.TableMatricula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static java.lang.Double.parseDouble;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class TelaPrincipalMatricula extends TelaPrincipalBase<Matricula, MatriculaDAOImpl> {

    private final String[] colunas = new String[]{"ID", "Disciplina", "Data da Matricula", "Valor Pago", "Aluno", "Periodo"};

    public TelaPrincipalMatricula() {
        super();
        configurarTitulo();
    }

    @Override
    protected void listarAction(ActionEvent evt) {
        var matriculaDAO = new MatriculaDAOImpl();
        listar(matriculaDAO, colunas);
    }

    @Override
    protected TableModel criarTableModel(List<Matricula> itens) {
        return new TableMatricula(itens, colunas);

    }

    @Override
    protected void inserir(ActionEvent evt) {
        var tela = new TelaSecundariaMatricula();
        tela.telaSecundariaMatricula(null);
    }

    @Override
    protected void editar(ActionEvent evt) {
        var selectedRow = tabelaPrincipal.getSelectedRow();

        if (selectedRow >= 0) {
            var idMatricula = (int) tabelaPrincipal.getValueAt(selectedRow, 0);
            var matriculaDAO = new MatriculaDAOImpl();

            var matricula = matriculaDAO.findById(idMatricula);

            if (matricula != null) {
                var tela = new TelaSecundariaMatricula(matricula);
                tela.telaSecundariaMatricula(matricula);
                listar(matriculaDAO, colunas);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma matrícula para editar.", AVISO, JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    protected void excluirAction(ActionEvent evt) {
        var id = excluir();

        if (id != -1) {
            var matriculaDAO = new MatriculaDAOImpl();
            matriculaDAO.delete(id);
            showMessageDialog(null, "Matrícula excluída com sucesso.");
            listar(matriculaDAO, colunas);
        }
    }

    public boolean processarMatricula(int id, JComboBox disciplinaField, JTextField valorPagoField, JComboBox alunoField,
                                      JTextField periodoField, Operacao operacao) {

        var matriculaDAO = new MatriculaDAOImpl();

        var disciplinaText = (Disciplina) disciplinaField.getSelectedItem();
        var valorPagoText = valorPagoField.getText();
        var alunoText = (Pessoa) alunoField.getSelectedItem();
        var periodo = periodoField.getText();

        if (validarCampos(valorPagoField, periodoField)) {
            var matricula = setMatricula(disciplinaText, valorPagoText, alunoText, periodo);

            switch (operacao) {
                case INSERIR -> matriculaDAO.insert(matricula);
                case ATUALIZAR -> matriculaDAO.update(id, matricula);
                default -> throw new IllegalArgumentException();
            }

            return true;
        }

        return false;
    }

    private Matricula setMatricula(Disciplina disciplina, String valorPagoText, Pessoa aluno, String periodo) {
        var matricula = new Matricula();
        matricula.setDisciplina(disciplina);
        matricula.setValorPago(parseDouble(valorPagoText));
        matricula.setAluno(aluno);
        matricula.setPeriodo(periodo);
        return matricula;
    }

    private boolean validarCampos(JTextField valorPagoField, JTextField periodoField) {
        var camposValidos = true;

        camposValidos &= validarCampoObrigatorio(periodoField);
        camposValidos &= validarCampoDouble(valorPagoField);

        return camposValidos;
    }

    private boolean validarCampoObrigatorio(JTextField campo) {
        var valido = !campo.getText().isEmpty();
        if (!valido) {
            campo.setBorder(createLineBorder(Color.RED));
        } else {
            campo.setBorder(null);
        }
        return valido;
    }

    private boolean validarCampoDouble(JTextField campo) {
        try {
            parseDouble(campo.getText());
            campo.setBorder(null);
            return true;
        } catch (NumberFormatException ex) {
            campo.setBorder(createLineBorder(Color.RED));
            return false;
        }
    }

    private Disciplina validarDisciplina(String nomeDisciplina) {
        var disciplinaDAO = new DisciplinaDAOImpl();
        var disciplina = disciplinaDAO.findByNome(nomeDisciplina);

        if (disciplina != null && disciplina.getNomeDisciplina().equalsIgnoreCase(nomeDisciplina)) {
            return disciplina;
        } else {
            showMessageDialog(null, "Disciplina não encontrada.", "Erro", ERROR_MESSAGE);
            throw new ValidacaoException("Disciplina não encontrada.");
        }
    }

    @Override
    protected void configurarCamposTabela() {
        tabelaPrincipal.setModel(new DefaultTableModel(colunas, 0));
    }

    @Override
    protected void configurarTitulo() {
        labelTitulo.setText("Matricula");
    }

    @Override
    protected void configurarCampoPesquisa() {
        labelPesquisar.setText("Número da Matrícula:");
    }

    public static void telaMatricula() {
        tela(TelaPrincipalMatricula.class);
    }

}
