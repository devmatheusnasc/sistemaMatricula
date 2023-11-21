package controle.matricula.telas.impl;

import controle.matricula.dao.impl.PessoaDAOImpl;
import controle.matricula.model.Pessoa;
import controle.matricula.telas.impl.TelaSecundariaPessoa;
import controle.matricula.telas.telabase.TelaPrincipalBase;
import controle.matricula.util.Operacao;
import controle.matricula.util.table.TablePessoa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Tela principal para gerenciar pessoas.
 */
public class TelaPrincipalPessoa extends TelaPrincipalBase<Pessoa, PessoaDAOImpl> {

    private final String[] colunas = new String[]{"ID", "Nome", "Endereço", "UF", "Telefone", "CPF", "E-mail", "Tipo"};

    /**
     * Construtor da classe TelaPrincipalPessoa.
     */
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
        var telaSecundaria = new TelaSecundariaPessoa();
        telaSecundaria.telaSecundariaPessoa(null);
    }

    @Override
    protected void editar(ActionEvent evt) {
        var selectedRow = tabelaPrincipal.getSelectedRow();

        if (selectedRow >= 0) {
            var id = (int) tabelaPrincipal.getValueAt(selectedRow, 0);
            var pessoaDAO = new PessoaDAOImpl();
            var pessoa = pessoaDAO.findById(id);

            if (pessoa != null) {
                var tela = new TelaSecundariaPessoa(pessoa);
                tela.telaSecundariaPessoa(pessoa);
                listar(pessoaDAO, colunas);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma pessoa para editar.", AVISO, JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    protected void excluirAction(ActionEvent evt) {
        var item = excluir();

        if (item != -1) {
            var pessoaDAO = new PessoaDAOImpl();
            pessoaDAO.delete(item);
            showMessageDialog(null, "Pessoa excluída com sucesso.");
            listar(pessoaDAO, colunas);
        }
    }

    /**
     * Processa as informações da pessoa fornecidas pelo usuário.
     *
     * @param id       O ID da pessoa (pode ser nulo para operações de inserção).
     * @param nome     O campo do nome da pessoa.
     * @param endereco O campo do endereço da pessoa.
     * @param uf       O campo do UF da pessoa.
     * @param cpf      O campo do CPF da pessoa.
     * @param telefone O campo do telefone da pessoa.
     * @param email    O campo do e-mail da pessoa.
     * @param tipo     O campo do tipo da pessoa.
     * @param operacao A operação a ser realizada (inserir ou atualizar).
     * @return true se o processamento for bem-sucedido, false caso contrário.
     */
    public boolean processarUsuario(int id, JTextField nome, JTextField endereco, JComboBox uf,
                                    JTextField cpf, JTextField telefone, JTextField email, JComboBox tipo,
                                    Operacao operacao) {

        var pessoaDao = new PessoaDAOImpl();

        var nomeText = nome.getText();
        var enderecoText = endereco.getText();
        var ufText = (String) uf.getSelectedItem();
        var cpfText = cpf.getText();
        var telefoneText = telefone.getText();
        var emailText = email.getText();
        var tipoText = (String) tipo.getSelectedItem();

        var pessoa = setPessoa(nomeText, enderecoText, ufText, cpfText, telefoneText, emailText, tipoText);

        if (operacao == Operacao.INSERIR && (validarCampos(nome, endereco, cpf, telefone, email))) {
            pessoaDao.insert(pessoa);
            return true;
        }

        if (operacao == Operacao.ATUALIZAR && (validarCampos(nome, endereco, null, telefone, email))) {
            pessoaDao.update(id, pessoa);
            return true;
        }

        return false;
    }

    /**
     * Cria um objeto Pessoa com base nos parâmetros fornecidos.
     *
     * @param nome     O nome da pessoa.
     * @param endereco O endereço da pessoa.
     * @param uf       O UF da pessoa.
     * @param cpf      O CPF da pessoa.
     * @param telefone O telefone da pessoa.
     * @param email    O e-mail da pessoa.
     * @param tipo     O tipo da pessoa.
     * @return Um objeto Pessoa criado com base nos parâmetros.
     */
    private Pessoa setPessoa(String nome, String endereco, String uf, String cpf, String telefone, String email, String tipo) {
        var pessoa = new Pessoa();

        pessoa.setNomePessoa(nome);
        pessoa.setEndereco(endereco);
        pessoa.setUf(uf);
        pessoa.setCpf(cpf);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        pessoa.setTipo(tipo);

        return pessoa;
    }

    /**
     * Valida os campos obrigatórios da tela.
     *
     * @param nome     O campo do nome da pessoa.
     * @param endereco O campo do endereço da pessoa.
     * @param cpf      O campo do CPF da pessoa.
     * @param telefone O campo do telefone da pessoa.
     * @param email    O campo do e-mail da pessoa.
     * @return true se os campos forem válidos, false caso contrário.
     */
    private boolean validarCampos(JTextField nome, JTextField endereco, JTextField cpf, JTextField telefone, JTextField email) {
        var camposValidos = true;

        camposValidos &= validarCampoObrigatorio(nome);
        camposValidos &= validarCampoObrigatorio(endereco);
        camposValidos &= validarCampoObrigatorio(telefone);
        camposValidos &= validarCampoObrigatorio(email);

        if (cpf != null) {
            camposValidos &= validarCampoObrigatorio(cpf);
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
        labelTitulo.setText("Pessoa");
    }

    @Override
    protected void configurarCampoPesquisa() {
        labelPesquisar.setText("Nome:");
    }

    /**
     * Método estático para abrir a tela principal de pessoas.
     */
    public static void telaPessoa() {
        tela(TelaPrincipalPessoa.class);
    }
}
