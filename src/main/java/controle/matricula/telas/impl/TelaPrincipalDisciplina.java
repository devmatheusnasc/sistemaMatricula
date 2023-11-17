package controle.matricula.telas.impl;

import controle.matricula.dao.impl.DisciplinaDAOImpl;
import controle.matricula.dao.impl.UsuarioDAOImpl;
import controle.matricula.model.Disciplina;
import controle.matricula.telas.telabase.TelaPrincipalBase;
import controle.matricula.util.table.TableDisciplina;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class TelaPrincipalDisciplina extends TelaPrincipalBase<Disciplina, DisciplinaDAOImpl> {

    private final String[] colunas = new String[]{"Codigo", "Nome", "Carga Horária", "Professor", "Limite Alunos"};

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

    }

    @Override
    protected void editar(ActionEvent evt) {

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

    protected JPanel criarPainel(JTextField string, JTextField string2, JTextField string3, JTextField string4, JButton salvarButton) {
        return null;
    }

    protected boolean salvar(JTextField string, JTextField string2, JTextField string3, JTextField string4, JFrame frame) {
        return false;
    }

    protected boolean validarCampos(JTextField string, JTextField string2, JTextField string3, JTextField string4) {
        return false;
    }

    protected boolean validarCampoObrigatorio(JTextField campo) {
        return false;
    }

    protected boolean validarCampoDouble(JTextField campo) {
        return false;
    }

    protected Disciplina criar(String string, String string2, String string3, String string4) {
        return null;
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

    public static void telaDisciplina() {
        tela(TelaPrincipalDisciplina.class);
    }
}
