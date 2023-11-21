package controle.matricula.telas.impl;

import controle.matricula.dao.impl.DisciplinaDAOImpl;
import controle.matricula.model.Disciplina;
import controle.matricula.model.Pessoa;
import controle.matricula.telas.telabase.TelaPrincipalBase;
import controle.matricula.util.Operacao;
import controle.matricula.util.table.TableDisciplina;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static java.lang.Integer.parseInt;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Tela principal para gerenciar disciplinas.
 */
public class TelaPrincipalDisciplina extends TelaPrincipalBase<Disciplina, DisciplinaDAOImpl> {

    private final String[] colunas = new String[]{"Codigo", "Nome", "Carga Horária", "Professor", "Limite Alunos"};

    /**
     * Construtor da classe TelaPrincipalDisciplina.
     */
    public TelaPrincipalDisciplina() {
        super();
        configurarTitulo();
    }

    @Override
    protected void listarAction(ActionEvent evt) {
        var disciplinaDAO = new DisciplinaDAOImpl();
        listar(disciplinaDAO, colunas);
    }

    @Override
    protected TableModel criarTableModel(List<Disciplina> itens) {
        return new TableDisciplina(itens, colunas);
    }

    @Override
    protected void inserir(ActionEvent evt) {
        var telaSecundaria = new TelaSecundariaDisciplina();
        telaSecundaria.telaSecundariaDisciplina(null);
    }

    @Override
    protected void editar(ActionEvent evt) {
        var selectedRow = tabelaPrincipal.getSelectedRow();

        if (selectedRow >= 0) {
            var id = (int) tabelaPrincipal.getValueAt(selectedRow, 0);
            var disciplinaDAO = new DisciplinaDAOImpl();
            var disciplina = disciplinaDAO.findById(id);

            if (disciplina != null) {
                var tela = new TelaSecundariaDisciplina(disciplina);
                tela.telaSecundariaDisciplina(disciplina);
                listar(disciplinaDAO, colunas);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma disciplina para editar.", AVISO, JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    protected void excluirAction(ActionEvent evt) {
        var item = excluir();

        if (item != -1) {
            var disciplinaDAO = new DisciplinaDAOImpl();
            disciplinaDAO.delete(item);
            showMessageDialog(null, "Disciplina excluída com sucesso.");
            listar(disciplinaDAO, colunas);
        }
    }

    /**
     * Processa as informações da disciplina fornecidas pelo usuário.
     *
     * @param id             O ID da disciplina (pode ser nulo para operações de inserção).
     * @param nomeDisciplina O nome da disciplina.
     * @param cargaHoraria   A carga horária da disciplina.
     * @param professor      O professor responsável pela disciplina.
     * @param limiteAlunos   O limite de alunos para a disciplina.
     * @param operacao       A operação a ser realizada (inserir ou atualizar).
     * @return true se o processamento for bem-sucedido, false caso contrário.
     */
    public boolean processarUsuario(int id, JTextField nomeDisciplina, JTextField cargaHoraria, JComboBox professor,
                                    JTextField limiteAlunos, Operacao operacao) {

        var disciplinaDAO = new DisciplinaDAOImpl();

        var nomeDisciplinaText = nomeDisciplina.getText();
        var cargaHorariaText = cargaHoraria.getText();
        var professorText = (Pessoa) professor.getSelectedItem();
        var limiteAlunosText = limiteAlunos.getText();
        if (validarCampos(nomeDisciplina, cargaHoraria, limiteAlunos)) {

            var disciplina = setDisciplina(nomeDisciplinaText, cargaHorariaText, professorText, limiteAlunosText);

            if (operacao == Operacao.INSERIR) {
                disciplinaDAO.insert(disciplina);
                return true;
            }

            if (operacao == Operacao.ATUALIZAR) {
                disciplinaDAO.update(id, disciplina);
                return true;
            }
        }
        return false;
    }

    /**
     * Cria um objeto Disciplina com base nos parâmetros fornecidos.
     *
     * @param nomeDisciplina O nome da disciplina.
     * @param cargaHoraria   A carga horária da disciplina.
     * @param professor      O professor responsável pela disciplina.
     * @param limiteAlunos   O limite de alunos para a disciplina.
     * @return Um objeto Disciplina criado com base nos parâmetros.
     */
    private Disciplina setDisciplina(String nomeDisciplina, String cargaHoraria, Pessoa professor, String limiteAlunos) {
        var disciplina = new Disciplina();

        disciplina.setNomeDisciplina(nomeDisciplina);
        disciplina.setCargaHoraria(parseInt(cargaHoraria));
        disciplina.setProfessor(professor);
        disciplina.setLimiteAlunos(parseInt(limiteAlunos));

        return disciplina;
    }

    /**
     * Valida os campos obrigatórios da tela.
     *
     * @param nomeDisciplina O campo do nome da disciplina.
     * @param cargaHoraria   O campo da carga horária da disciplina.
     * @param limiteAlunos   O campo do limite de alunos.
     * @return true se os campos forem válidos, false caso contrário.
     */
    private boolean validarCampos(JTextField nomeDisciplina, JTextField cargaHoraria, JTextField limiteAlunos) {
        var camposValidos = true;

        camposValidos &= validarCampoObrigatorio(nomeDisciplina);
        camposValidos &= validarCampoObrigatorio(cargaHoraria);
        camposValidos &= validarCampoObrigatorio(limiteAlunos);

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

    @Override
    protected void configurarCamposTabela() {
        tabelaPrincipal.setModel(new DefaultTableModel(colunas, 0));
    }

    @Override
    protected void configurarTitulo() {
        labelTitulo.setText("Disciplina");
    }

    @Override
    protected void configurarCampoPesquisa() {
        labelPesquisar.setText("Nome da Disciplina:");
    }

    /**
     * Método estático para abrir a tela principal de disciplinas.
     */
    public static void telaDisciplina() {
        tela(TelaPrincipalDisciplina.class);
    }
}
