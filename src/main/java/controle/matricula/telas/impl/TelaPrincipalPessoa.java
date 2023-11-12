package controle.matricula.telas.impl;

import controle.matricula.dao.daobase.DAO;
import controle.matricula.dao.impl.PessoaDAOImpl;
import controle.matricula.model.Matricula;
import controle.matricula.telas.telabase.TelaPrincipalBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaPrincipalPessoa extends TelaPrincipalBase<Matricula> {

    public TelaPrincipalPessoa() {
        super();
        configurarTitulo();
    }

    @Override
    protected void listar(ActionEvent evt) {
        var ok = new PessoaDAOImpl();
        var ok1 = ok.obterTipoPessoa();
        System.out.println(ok1);
    }

    @Override
    protected List<Matricula> listarTodos(DAO<Matricula> dao) {
        return null;
    }

    @Override
    protected void inserir(ActionEvent evt) {

    }

    @Override
    protected void editar(ActionEvent evt) {
        TelaEditarPessoa ok = new TelaEditarPessoa();
        ok.editarTela();
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
    protected Matricula criar(String string, String string2, String string3, String string4) {
        return null;
    }

    @Override
    protected void configurarCamposTabela() {
        var colunas = new String[]{"ID", "Nome", "Endereço", "UF", "Telefone", "CPF", "E-mail", "Tipo"};
        tabelaPrincipal.setModel(new DefaultTableModel(colunas, 0));
    }

    @Override
    protected void configurarTitulo() {
        labelTitulo.setText("Pessoa");
    }

    @Override
    protected void configurarCampoPesquisa() {
        labelPesquisar.setText("Nome:");
    }

    public static void telaPessoa() {
        tela(TelaPrincipalPessoa.class);
    }
}
