package controle.matricula.telas.impl;

import controle.matricula.dao.impl.DisciplinaDAOImpl;
import controle.matricula.dao.impl.MatriculaDAOImpl;
import controle.matricula.model.Disciplina;
import controle.matricula.model.Matricula;
import controle.matricula.model.Pessoa;
import controle.matricula.telas.impl.TelaSecundariaMatricula;
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

/**
 * Tela principal para gerenciar matrículas.
 */
public class TelaPrincipalMatricula extends TelaPrincipalBase<Matricula, MatriculaDAOImpl> {

    private final String[] colunas = new String[]{"ID", "Disciplina", "Data da Matricula", "Valor Pago", "Aluno", "Período"};

    /**
     * Construtor da classe TelaPrincipalMatricula.
     */
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

    /**
     * Processa as informações da matrícula fornecidas pelo usuário.
     *
     * @param id              O ID da matrícula (pode ser nulo para operações de inserção).
     * @param disciplinaField O campo da disciplina da matrícula.
     * @param valorPagoField  O campo do valor pago na matrícula.
     * @param alunoField      O campo do aluno da matrícula.
     * @param periodoField    O campo do período da matrícula.
     * @param operacao        A operação a ser realizada (inserir ou atualizar).
     * @return true se o processamento for bem-sucedido, false caso contrário.
     */
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

    /**
     * Cria um objeto Matricula com base nos parâmetros fornecidos.
     *
     * @param disciplina A disciplina da matrícula.
     * @param valorPago  O valor pago na matrícula.
     * @param aluno      O aluno da matrícula.
     * @param periodo    O período da matrícula.
     * @return Um objeto Matricula criado com base nos parâmetros.
     */
    private Matricula setMatricula(Disciplina disciplina, String valorPago, Pessoa aluno, String periodo) {
        var matricula = new Matricula();
        matricula.setDisciplina(disciplina);
        matricula.setValorPago(parseDouble(valorPago));
        matricula.setAluno(aluno);
        matricula.setPeriodo(periodo);
        return matricula;
    }

    /**
     * Valida os campos obrigatórios da tela.
     *
     * @param valorPagoField O campo do valor pago na matrícula.
     * @param periodoField   O campo do período da matrícula.
     * @return true se os campos forem válidos, false caso contrário.
     */
    private boolean validarCampos(JTextField valorPagoField, JTextField periodoField) {
        var camposValidos = true;

        camposValidos &= validarCampoObrigatorio(periodoField);
        camposValidos &= validarCampoDouble(valorPagoField);

        return camposValidos;
    }

    /**
     * Valida se um campo obrigatório foi preenchido.
     *
     * @param campo O campo a ser validado.
     * @return true se o campo for válido, false caso contrário.
     */
    private boolean validarCampoObrigatorio(JTextField campo) {
        var valido = !campo.getText().isEmpty();
        if (!valido) {
            campo.setBorder(createLineBorder(Color.RED));
        } else {
            campo.setBorder(null);
        }
        return valido;
    }

    /**
     * Valida se um campo contém um valor numérico do tipo double.
     *
     * @param campo O campo a ser validado.
     * @return true se o campo contiver um valor numérico do tipo double, false caso contrário.
     */
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

    /**
     * Método estático para abrir a tela principal de matrículas.
     */
    public static void telaMatricula() {
        tela(TelaPrincipalMatricula.class);
    }
}
