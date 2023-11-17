package controle.matricula.telas.impl;

import controle.matricula.model.Usuario;
import controle.matricula.util.Operacao;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;

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

public class TelaSecundariaUsuario extends JFrame {

    private JTextField textNome;
    private JTextField textEmail;
    private JTextField textCargo;
    private JTextField textLogin;
    private JTextField textSenha;

    private final JPanel panel = new JPanel();
    private final JLabel campoNome = new JLabel();
    private final JLabel campoEmail = new JLabel();
    private final JLabel campoCargo = new JLabel();
    private final JLabel campoLogin = new JLabel();
    private final JLabel campoSenha = new JLabel();

    private final JButton btnCancelar = new JButton();
    private final JButton btnConfirmar = new JButton();
    private Operacao operacao;
    private int id;

    public TelaSecundariaUsuario() {
        operacao = Operacao.INSERIR;
        inicializarCampos();
        initComponentsInserir();
        pack();
        setLocationRelativeTo(null);
    }

    public TelaSecundariaUsuario(Usuario usuario) {
        operacao = Operacao.ATUALIZAR;
        id = usuario.getId();

        inicializarCampos();
        preencherCampo(usuario);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {

        campoNome.setText("Nome:");

        campoEmail.setText("Email:");

        campoCargo.setText("Cargo");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelar);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(this::btnConfirmar);

        var jPanel1Layout = new GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(TRAILING, false)
                                                        .addComponent(campoEmail, LEADING, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                                        .addComponent(campoNome, LEADING, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                                        .addComponent(campoCargo, PREFERRED_SIZE, 89, PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createParallelGroup(LEADING, false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(textCargo, PREFERRED_SIZE, 245, PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(LEADING)
                                                                        .addComponent(textEmail, PREFERRED_SIZE, 245, PREFERRED_SIZE)
                                                                        .addComponent(textNome, PREFERRED_SIZE, 245, PREFERRED_SIZE)))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnCancelar)
                                                .addPreferredGap(RELATED, DEFAULT_SIZE, MAX_VALUE)
                                                .addComponent(btnConfirmar)))
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(85, 85, 85)
                                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                                        .addComponent(campoEmail, PREFERRED_SIZE, 23, PREFERRED_SIZE)
                                                        .addComponent(textEmail, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                                        .addComponent(textNome, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                                        .addComponent(campoNome, PREFERRED_SIZE, 23, PREFERRED_SIZE))))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(textCargo, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(campoCargo, PREFERRED_SIZE, 23, PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(btnConfirmar)
                                        .addComponent(btnCancelar))
                                .addContainerGap(14, MAX_VALUE))
        );

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addGap(0, 16, MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, layout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE)
                                .addComponent(panel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addGap(14, 14, 14))
        );

        pack();
    }

    private void initComponentsInserir() {

        campoNome.setText("Nome:");
        campoEmail.setText("Email:");
        campoCargo.setText("Cargo");
        campoLogin.setText("Login:");
        campoSenha.setText("Senha:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelar);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(this::btnConfirmar);


        var jPanel1Layout = new GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(campoNome, PREFERRED_SIZE, 89, PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(textNome, PREFERRED_SIZE, 245, PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnCancelar)
                                                .addGap(186, 186, 186)
                                                .addComponent(btnConfirmar)))
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE))
                        .addGroup(TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(LEADING, false)
                                        .addGroup(TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(campoSenha, DEFAULT_SIZE, 89, MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(textSenha, PREFERRED_SIZE, 245, PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(TRAILING, false)
                                                        .addComponent(campoLogin, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                                        .addComponent(campoCargo, LEADING, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                                        .addComponent(campoEmail, LEADING, DEFAULT_SIZE, 89, MAX_VALUE))
                                                .addGroup(jPanel1Layout.createParallelGroup(LEADING, false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(textEmail, DEFAULT_SIZE, 245, MAX_VALUE))
                                                        .addGroup(TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(textCargo))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(textLogin)))))
                                .addContainerGap())
        );

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(campoNome)
                                        .addComponent(textNome, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(textEmail, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(campoEmail))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(textCargo, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(campoCargo))
                                .addPreferredGap(UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(LEADING)
                                        .addComponent(campoLogin)
                                        .addComponent(textLogin, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(LEADING)
                                        .addComponent(campoSenha)
                                        .addComponent(textSenha, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(btnConfirmar)
                                        .addComponent(btnCancelar))
                                .addContainerGap(14, MAX_VALUE))
        );

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addGap(0, 16, MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addContainerGap(28, MAX_VALUE))
        );
    }

    private void btnCancelar(ActionEvent evt) {
        var frame = (JFrame) getRoot((Component) evt.getSource());
        frame.dispose();
    }

    private void btnConfirmar(ActionEvent evt) {
        var telaPrincipal = new TelaPrincipalUsuario();
        if (telaPrincipal.processarUsuario(id, textNome, textEmail, textCargo, textLogin, textSenha, operacao)) {
            dispose();
        }
    }

    private void preencherCampo(Usuario usuario) {
        textNome.setText(usuario.getNome());
        textEmail.setText(usuario.getEmail());
        textCargo.setText(usuario.getCargo());
    }

    private void inicializarCampos() {
        textNome = new JTextField();
        textEmail = new JTextField();
        textCargo = new JTextField();
        textLogin = new JTextField();
        textSenha = new JTextField();
    }

    public void telaSecundariaUsuario(Usuario usuario) {
        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            getLogger(TelaSecundariaUsuario.class.getName()).log(SEVERE, null, ex);
        }
        if (usuario == null) {
            invokeLater(() -> new TelaSecundariaUsuario().setVisible(true));
        } else {
            invokeLater(() -> new TelaSecundariaUsuario(usuario).setVisible(true));
        }
    }
}
