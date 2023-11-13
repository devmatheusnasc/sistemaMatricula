package controle.matricula.telas.impl;

import controle.matricula.dao.impl.DisciplinaDAOImpl;
import controle.matricula.dao.impl.PessoaDAOImpl;
import controle.matricula.model.Matricula;
import controle.matricula.model.Pessoa;
import controle.matricula.telas.telabase.TelaPrincipalBase;
import controle.matricula.util.table.TablePessoa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class TelaPrincipalPessoa extends TelaPrincipalBase<Pessoa, PessoaDAOImpl> {

    private final String[] colunas = new String[]{"ID", "Nome", "Endereço", "UF", "Telefone", "CPF", "E-mail", "Tipo"};

    public TelaPrincipalPessoa() {
        super();
        configurarTitulo();
    }

    @Override
    protected void listarAction(ActionEvent evt) {
        var pessoaDAO = new PessoaDAOImpl();

        listar(pessoaDAO, colunas);
    }

    @Override
    protected TableModel criarTableModel(List<Pessoa> itens) {
        return new TablePessoa(itens, colunas);
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
    protected void excluirAction(ActionEvent evt) {
        var item = excluir();

        if (item != -1) {
            var pessoa = new PessoaDAOImpl();
            var delete = pessoa.delete(item);

            if (delete) {
                showMessageDialog(null, "Pessoa excluída com sucesso.");
                listar(pessoa, colunas);
            } else {
                showMessageDialog(null, "Erro ao excluir a Pessoa.", "Erro", ERROR_MESSAGE);
            }
        }
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
    protected Pessoa criar(String string, String string2, String string3, String string4) {
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
