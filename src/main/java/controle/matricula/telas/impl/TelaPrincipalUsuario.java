package controle.matricula.telas.impl;

import controle.matricula.dao.daobase.DAO;
import controle.matricula.model.Usuario;
import controle.matricula.telas.telabase.TelaPrincipalBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaPrincipalUsuario extends TelaPrincipalBase<Usuario> {

    public TelaPrincipalUsuario() {
        super();
        configurarTitulo();
    }

    @Override
    protected void listar(ActionEvent evt) {

    }

    @Override
    protected List<Usuario> listarTodos(DAO<Usuario> dao) {
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
    protected Usuario criar(String string, String string2, String string3, String string4) {
        return null;
    }

    @Override
    protected void configurarCamposTabela() {

    }

    @Override
    protected void configurarTitulo() {
        labelTitulo.setText("Usuário");
    }

    @Override
    protected void configurarCampoPesquisa() {

    }

    public static void telaUsuario() {
        tela(TelaPrincipalUsuario.class);
    }
}
