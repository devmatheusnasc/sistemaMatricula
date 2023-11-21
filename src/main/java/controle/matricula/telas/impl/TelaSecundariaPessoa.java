package controle.matricula.telas.impl;

import controle.matricula.model.Pessoa;
import controle.matricula.util.Operacao;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;

import static controle.matricula.util.Operacao.ATUALIZAR;
import static controle.matricula.util.Operacao.INSERIR;
import static java.awt.EventQueue.invokeLater;
import static java.lang.Short.MAX_VALUE;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;
import static javax.swing.SwingUtilities.getRoot;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

/**
 * Esta classe representa a tela secundária para manipulação de informações de Pessoa.
 */
public class TelaSecundariaPessoa extends JFrame {

    private JPanel jPanel = new JPanel();
    private JLabel campoEmail = new JLabel();
    private JLabel campoEndereco = new JLabel();
    private JLabel campoNome = new JLabel();
    private JLabel campoTelefone = new JLabel();
    private JLabel campoUf = new JLabel();
    private JLabel campoTipo = new JLabel();
    private JLabel campoCpf = new JLabel();
    private JButton btnCancelar = new JButton();
    private JButton btnConfirmar = new JButton();
    private JTextField textNome;
    private JTextField textEndereco;
    private JComboBox<String> textUf;
    private JTextField textCpf;
    private JTextField textTelefone;
    private JTextField textEmail;
    private JComboBox<String> textTipo;
    private Operacao operacao;
    private int id;

    private final String[] uf = {
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
            "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    /**
     * Construtor padrão para a inserção de uma nova Pessoa.
     */
    public TelaSecundariaPessoa() {
        operacao = INSERIR;
        inicializarCampos();
        initComponentsInserir();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Construtor para a atualização de uma Pessoa existente.
     *
     * @param pessoa A Pessoa a ser atualizada.
     */
    public TelaSecundariaPessoa(Pessoa pessoa) {
        operacao = ATUALIZAR;
        id = pessoa.getIdPessoa();

        inicializarCampos();
        preencherCampo(pessoa);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Configura os componentes para a operação de inserção.
     */
    private void initComponentsInserir() {

        textTelefone.setColumns(11);
        campoNome.setText("Nome:");
        campoEndereco.setText("Endereço");
        campoUf.setText("Uf");
        campoTelefone.setText("Telefone");
        campoCpf.setText("CPF");
        campoEmail.setText("Email");
        campoTipo.setText("Tipo");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelar);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(this::btnConfirmar);

        textUf.setModel(new DefaultComboBoxModel<>(uf));
        textTipo.setModel(new DefaultComboBoxModel<>(new String[]{"Professor", "Aluno"}));

        var panel = new GroupLayout(jPanel);
        jPanel.setLayout(panel);
        panel.setHorizontalGroup(
                panel.createParallelGroup(LEADING)
                        .addGroup(TRAILING, panel.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE)
                                .addGroup(panel.createParallelGroup(LEADING, false)
                                        .addGroup(panel.createSequentialGroup()
                                                .addGroup(panel.createParallelGroup(TRAILING, false)
                                                        .addComponent(campoTelefone, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                                        .addComponent(campoUf, LEADING, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                                        .addComponent(campoEndereco, LEADING, DEFAULT_SIZE, 89, MAX_VALUE))
                                                .addGroup(panel.createParallelGroup(LEADING, false)
                                                        .addGroup(panel.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(textEndereco))
                                                        .addGroup(panel.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(panel.createParallelGroup(LEADING)
                                                                        .addComponent(textUf, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                                                        .addComponent(textTelefone, DEFAULT_SIZE, 245, MAX_VALUE)))))
                                        .addGroup(TRAILING, panel.createSequentialGroup()
                                                .addComponent(campoCpf, DEFAULT_SIZE, 89, MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(textCpf, PREFERRED_SIZE, 245, PREFERRED_SIZE)))
                                .addContainerGap())
                        .addGroup(panel.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(panel.createParallelGroup(LEADING, false)
                                        .addGroup(panel.createParallelGroup(TRAILING)
                                                .addGroup(panel.createSequentialGroup()
                                                        .addComponent(campoNome, PREFERRED_SIZE, 89, PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(textNome, PREFERRED_SIZE, 245, PREFERRED_SIZE))
                                                .addGroup(panel.createSequentialGroup()
                                                        .addComponent(btnCancelar)
                                                        .addGap(186, 186, 186)
                                                        .addComponent(btnConfirmar)))
                                        .addGroup(TRAILING, panel.createSequentialGroup()
                                                .addGroup(panel.createParallelGroup(LEADING)
                                                        .addComponent(campoTipo, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                                        .addComponent(campoEmail, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel.createParallelGroup(LEADING)
                                                        .addGroup(panel.createSequentialGroup()
                                                                .addComponent(textTipo, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                                        .addComponent(textEmail, TRAILING, PREFERRED_SIZE, 245, PREFERRED_SIZE))))
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE))
        );
        panel.setVerticalGroup(
                panel.createParallelGroup(LEADING)
                        .addGroup(panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel.createParallelGroup(BASELINE)
                                        .addComponent(campoNome)
                                        .addComponent(textNome, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(UNRELATED)
                                .addGroup(panel.createParallelGroup(BASELINE)
                                        .addComponent(textEndereco, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(campoEndereco))
                                .addGap(8, 8, 8)
                                .addGroup(panel.createParallelGroup(BASELINE)
                                        .addComponent(campoUf)
                                        .addComponent(textUf, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(UNRELATED)
                                .addGroup(panel.createParallelGroup(LEADING)
                                        .addComponent(campoTelefone)
                                        .addComponent(textTelefone, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(UNRELATED)
                                .addGroup(panel.createParallelGroup(LEADING)
                                        .addComponent(campoCpf)
                                        .addComponent(textCpf, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(UNRELATED)
                                .addGroup(panel.createParallelGroup(BASELINE)
                                        .addComponent(campoEmail)
                                        .addComponent(textEmail, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(UNRELATED)
                                .addGroup(panel.createParallelGroup(BASELINE)
                                        .addComponent(campoTipo)
                                        .addComponent(textTipo, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(RELATED, 21, MAX_VALUE)
                                .addGroup(panel.createParallelGroup(BASELINE)
                                        .addComponent(btnCancelar)
                                        .addComponent(btnConfirmar)))
        );

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addGap(0, 15, MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                .addContainerGap())
        );
    }

    /**
     * Configura os componentes para a operação de atualização.
     */
    private void initComponents() {

        textTelefone.setColumns(11);
        campoNome.setText("Nome:");
        campoEndereco.setText("Endereço");
        campoUf.setText("Uf");
        campoTelefone.setText("Telefone");
        campoEmail.setText("Email");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelar);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(this::btnConfirmar);


        textUf.setModel(new DefaultComboBoxModel<>(uf));

        var jPanel1Layout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(campoTelefone, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(campoUf, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(campoEndereco, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                        .addComponent(campoEmail, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(textEndereco))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(textEmail, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textUf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textTelefone))))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(campoNome, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(textNome, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnCancelar)
                                                .addGap(186, 186, 186)
                                                .addComponent(btnConfirmar)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoNome)
                                        .addComponent(textNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campoEndereco))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoUf)
                                        .addComponent(textUf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(campoTelefone)
                                        .addComponent(textTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoEmail)
                                        .addComponent(textEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancelar)
                                        .addComponent(btnConfirmar)))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    /**
     * Manipula o evento de clique no botão Cancelar.
     *
     * @param evt O evento de clique.
     */
    private void btnCancelar(ActionEvent evt) {
        var frame = (JFrame) getRoot((Component) evt.getSource());
        frame.dispose();
    }

    /**
     * Manipula o evento de clique no botão Confirmar.
     *
     * @param evt O evento de clique.
     */
    private void btnConfirmar(ActionEvent evt) {
        var telaPrincipal = new TelaPrincipalPessoa();
        if (telaPrincipal.processarUsuario(id, textNome, textEndereco, textUf, textCpf, textTelefone, textEmail, textTipo, operacao)) {
            dispose();
        }
    }

    /**
     * Preenche os campos com informações da Pessoa existente.
     *
     * @param pessoa A Pessoa cujas informações serão exibidas.
     */
    private void preencherCampo(Pessoa pessoa) {
        textNome.setText(pessoa.getNomePessoa());
        textEndereco.setText(pessoa.getEndereco());
        textTelefone.setText(pessoa.getTelefone());
        textEmail.setText(pessoa.getEmail());
    }

    /**
     * Inicializa os campos necessários.
     */
    private void inicializarCampos() {
        textNome = new JTextField();
        textEndereco = new JTextField();
        textUf = new JComboBox<>();
        textCpf = new JTextField();
        textTelefone = new JTextField();
        textEmail = new JTextField();
        textTipo = new JComboBox<>();
    }

    /**
     * Método principal para exibir a TelaSecundariaPessoa.
     *
     * @param pessoa A Pessoa a ser exibida. Se nula, uma nova Pessoa será criada.
     */
    public void telaSecundariaPessoa(Pessoa pessoa) {
        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            getLogger(TelaSecundariaPessoa.class.getName()).log(SEVERE, null, ex);
        }
        if (pessoa == null) {
            invokeLater(() -> new TelaSecundariaPessoa().setVisible(true));
        } else {
            invokeLater(() -> new TelaSecundariaPessoa(pessoa).setVisible(true));
        }
    }
}