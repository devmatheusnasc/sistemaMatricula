package controle.matricula.telas.impl;

import controle.matricula.dao.daobase.DAO;
import controle.matricula.model.Disciplina;
import controle.matricula.telas.telabase.TelaPrincipalBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaPrincipalDisciplina extends TelaPrincipalBase<Disciplina> {

    public TelaPrincipalDisciplina() {
        super();
        configurarTitulo();
    }

    @Override
    protected void listar(ActionEvent evt) {

    }

    @Override
    protected List<Disciplina> listarTodos(DAO<Disciplina> dao) {
        return null;
    }

    @Override
    protected void inserir(ActionEvent evt) {

    }

    @Override
    protected void editar(ActionEvent evt) {

    }

    @Override
    protected void excluir(ActionEvent evt) {

    }

    @Override
    protected JPanel criarPainel(JTextField string, JTextField string2, JTextField string3, JTextField string4, JButton salvarButton) {
        return null;
    }

    @Override
    protected boolean salvar(JTextField string, JTextField string2, JTextField string3, JTextField string4, JFrame frame) {
        return false;
    }

    @Override
    protected boolean validarCampos(JTextField string, JTextField string2, JTextField string3, JTextField string4) {
        return false;
    }

    @Override
    protected boolean validarCampoObrigatorio(JTextField campo) {
        return false;
    }

    @Override
    protected boolean validarCampoDouble(JTextField campo) {
        return false;
    }

    @Override
    protected Disciplina criar(String string, String string2, String string3, String string4) {
        return null;
    }

    @Override
    protected void configurarCamposTabela() {
        var colunas = new String[]{"ID", "Nome", "Cargo Horária", "Professor", "Limite de Alunos"};
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
