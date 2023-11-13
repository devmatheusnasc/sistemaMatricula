package controle.matricula.telas.impl;

import controle.matricula.dao.impl.UsuarioDAOImpl;
import controle.matricula.model.Usuario;
import controle.matricula.telas.telabase.TelaPrincipalBase;
import controle.matricula.util.table.TableUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class TelaPrincipalUsuario extends TelaPrincipalBase<Usuario, UsuarioDAOImpl> {

    private final String[] colunas = new String[]{"ID", "Nome", "E-mail", "Cargo"};

    public TelaPrincipalUsuario() {
        super();
        configurarTitulo();
    }

    @Override
    protected void listarAction(ActionEvent evt) {
        var usuarioDAO = new UsuarioDAOImpl();
        listar(usuarioDAO, colunas);
    }

    @Override
    protected TableModel criarTableModel(List<Usuario> itens) {
        return new TableUsuario(itens, colunas);
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
            var usuario = new UsuarioDAOImpl();
            var delete = usuario.delete(item);

            if (delete) {
                showMessageDialog(null, "Usuário excluído com sucesso.");
                listar(usuario, colunas);
            } else {
                showMessageDialog(null, "Erro ao excluir o usuário.", "Erro", ERROR_MESSAGE);
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
    protected Usuario criar(String string, String string2, String string3, String string4) {
        return null;
    }

    @Override
    protected void configurarCamposTabela() {
        var colunas = new String[]{"ID", "Nome", "E-mail", "Cargo"};
        tabelaPrincipal.setModel(new DefaultTableModel(colunas, 0));
    }

    @Override
    protected void configurarTitulo() {
        labelTitulo.setText("Usuário");
    }

    @Override
    protected void configurarCampoPesquisa() {
        labelPesquisar.setText("Nome: ");
    }

    public static void telaUsuario() {
        tela(TelaPrincipalUsuario.class);
    }
}
