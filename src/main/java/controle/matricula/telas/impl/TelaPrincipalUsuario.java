package controle.matricula.telas.impl;

import controle.matricula.dao.impl.UsuarioDAOImpl;
import controle.matricula.model.Usuario;
import controle.matricula.telas.telabase.TelaPrincipalBase;
import controle.matricula.util.Operacao;
import controle.matricula.util.table.TableUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Tela principal para gerenciar usuários.
 */
public class TelaPrincipalUsuario extends TelaPrincipalBase<Usuario, UsuarioDAOImpl> {

    private final String[] colunas = new String[]{"ID", "Nome", "E-mail", "Cargo"};

    /**
     * Construtor da classe TelaPrincipalUsuario.
     */
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
        var tela = new TelaSecundariaUsuario();
        tela.telaSecundariaUsuario(null);
    }

    @Override
    protected void editar(ActionEvent evt) {
        var selectedRow = tabelaPrincipal.getSelectedRow();

        if (selectedRow >= 0) {
            var id = (int) tabelaPrincipal.getValueAt(selectedRow, 0);
            var usuarioDAO = new UsuarioDAOImpl();

            var usuario = usuarioDAO.findById(id);

            if (usuario != null) {
                var tela = new TelaSecundariaUsuario(usuario);
                tela.telaSecundariaUsuario(usuario);
                listar(usuarioDAO, colunas);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um usuário para editar.", AVISO, JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    protected void excluirAction(ActionEvent evt) {
        var item = excluir();

        if (item != -1) {
            var usuarioDAO = new UsuarioDAOImpl();
            usuarioDAO.delete(item);
            showMessageDialog(null, "Usuário excluído com sucesso.");
            listar(usuarioDAO, colunas);
        }
    }

    /**
     * Processa as informações do usuário fornecidas pelo usuário.
     *
     * @param id       O ID do usuário (pode ser nulo para operações de inserção).
     * @param nome     O campo do nome do usuário.
     * @param email    O campo do e-mail do usuário.
     * @param cargo    O campo do cargo do usuário.
     * @param login    O campo do login do usuário.
     * @param senha    O campo da senha do usuário.
     * @param operacao A operação a ser realizada (inserir ou atualizar).
     * @return true se o processamento for bem-sucedido, false caso contrário.
     */
    public boolean processarUsuario(int id, JTextField nome, JTextField email, JTextField cargo, JTextField login,
                                    JTextField senha, Operacao operacao) {

        var usuarioDAO = new UsuarioDAOImpl();

        var nomeText = nome.getText();
        var emailText = email.getText();
        var cargoText = cargo.getText();
        var loginText = login.getText();
        var senhaText = senha.getText();

        var usuario = setUsuario(nomeText, emailText, cargoText, loginText, senhaText);

        if (operacao == Operacao.INSERIR && (validarCampos(nome, email, cargo, login, senha))) {
            usuarioDAO.insert(usuario);
            return true;
        }

        if (operacao == Operacao.ATUALIZAR && (validarCampos(nome, email, cargo, null, null))) {
            usuarioDAO.update(id, usuario);
            return true;
        }

        return false;
    }

    /**
     * Cria um objeto Usuario com base nos parâmetros fornecidos.
     *
     * @param nome  O nome do usuário.
     * @param email O e-mail do usuário.
     * @param cargo O cargo do usuário.
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return Um objeto Usuario criado com base nos parâmetros.
     */
    private Usuario setUsuario(String nome, String email, String cargo, String login, String senha) {
        var usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setCargo(cargo);
        usuario.setLogin(login);
        usuario.setSenha(senha);

        return usuario;
    }

    /**
     * Valida os campos de login e senha.
     *
     * @param login O campo do login do usuário.
     * @param senha O campo da senha do usuário.
     * @return true se os campos de login e senha forem válidos, false caso contrário.
     */
    private boolean validarLoginAndSenha(JTextField login, JTextField senha) {
        var camposValidos = true;

        camposValidos &= validarCampoObrigatorio(login);
        camposValidos &= validarCampoObrigatorio(senha);

        return camposValidos;
    }

    /**
     * Valida os campos da tela.
     *
     * @param nome  O campo do nome do usuário.
     * @param email O campo do e-mail do usuário.
     * @param cargo O campo do cargo do usuário.
     * @param login O campo do login do usuário.
     * @param senha O campo da senha do usuário.
     * @return true se os campos forem válidos, false caso contrário.
     */
    private boolean validarCampos(JTextField nome, JTextField email, JTextField cargo, JTextField login, JTextField senha) {
        var camposValidos = true;

        camposValidos &= validarCampoObrigatorio(nome);
        camposValidos &= validarCampoObrigatorio(email);
        camposValidos &= validarCampoObrigatorio(cargo);

        if (login != null) {
            camposValidos &= validarLoginAndSenha(login, senha);
        }

        return camposValidos;
    }

    /**
     * Valida se um campo é obrigatório e exibe uma borda vermelha se estiver vazio.
     *
     * @param campo O campo a ser validado.
     * @return true se o campo não estiver vazio, false caso contrário.
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
        labelTitulo.setText("Usuário");
    }

    @Override
    protected void configurarCampoPesquisa() {
        labelPesquisar.setText("Nome: ");
    }

    /**
     * Método estático para abrir a tela principal de usuários.
     */
    public static void telaUsuario() {
        tela(TelaPrincipalUsuario.class);
    }
}
