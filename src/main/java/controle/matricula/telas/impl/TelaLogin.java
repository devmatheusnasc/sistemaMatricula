package controle.matricula.telas.impl;


import controle.matricula.dao.impl.UsuarioDAOImpl;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;

import static controle.matricula.telas.impl.TelaMenu.telaMenu;
import static java.awt.EventQueue.invokeLater;
import static java.awt.Font.BOLD;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

public class TelaLogin extends javax.swing.JFrame {


    private JPasswordField campoSenha;
    private JTextField campoLogin;
    private static TelaLogin telaLoginInstance;

    public TelaLogin() {
        initComponents();
    }

    private void initComponents() {

        setTitle("Login");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        var container = new JPanel();
        var btnEntrar = new JButton();
        campoSenha = new JPasswordField();
        campoLogin = new JTextField();
        var login = new JLabel();
        var senha = new JLabel();
        var separador = new JSeparator();
        var titulo = new JLabel();


        btnEntrar.setText("ENTRAR");
        btnEntrar.addActionListener(e -> checkLogin());
        campoLogin.addActionListener(e -> checkLogin());
        campoSenha.addActionListener(e -> checkLogin());

        login.setText("Usuário:");
        senha.setText("Senha:");

        titulo.setText("SysControl");
        titulo.setFont(new Font("Arial", BOLD, 15));

        var containerLayout = new GroupLayout(container);

        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
                containerLayout.createParallelGroup(CENTER)
                        .addGroup(containerLayout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(btnEntrar, PREFERRED_SIZE, 114, PREFERRED_SIZE)
                                .addContainerGap(178, Short.MAX_VALUE))
                        .addGroup(CENTER, containerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(separador)
                                .addContainerGap())
                        .addGroup(TRAILING, containerLayout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(containerLayout.createParallelGroup(LEADING)
                                        .addGroup(TRAILING, containerLayout.createSequentialGroup()
                                                .addGroup(containerLayout.createParallelGroup(LEADING)
                                                        .addComponent(senha, TRAILING)
                                                        .addComponent(login, TRAILING))
                                                .addGap(26, 26, 26)
                                                .addGroup(containerLayout.createParallelGroup(LEADING, false)
                                                        .addComponent(campoLogin)
                                                        .addComponent(campoSenha, DEFAULT_SIZE, 258, Short.MAX_VALUE))
                                                .addGap(87, 87, 87))
                                        .addGroup(TRAILING, containerLayout.createSequentialGroup()
                                                .addComponent(titulo, PREFERRED_SIZE, 108, PREFERRED_SIZE)
                                                .addGap(172, 172, 172))))
        );
        containerLayout.setVerticalGroup(
                containerLayout.createParallelGroup(CENTER)
                        .addGroup(TRAILING, containerLayout.createSequentialGroup()
                                .addContainerGap(39, Short.MAX_VALUE)
                                .addComponent(titulo, PREFERRED_SIZE, 27, PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separador, PREFERRED_SIZE, 10, PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addGroup(containerLayout.createParallelGroup(BASELINE)
                                        .addComponent(campoLogin, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(login))
                                .addGap(27, 27, 27)
                                .addGroup(containerLayout.createParallelGroup(BASELINE)
                                        .addComponent(campoSenha, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(senha))
                                .addGap(45, 45, 45)
                                .addComponent(btnEntrar, PREFERRED_SIZE, 44, PREFERRED_SIZE)
                                .addGap(55, 55, 55))
        );

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(container, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(container, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void checkLogin() {
        var usuario = campoLogin.getText();
        var senha = new String(campoSenha.getPassword());

        var usuarioDAO = new UsuarioDAOImpl();
        var usuarioBD = usuarioDAO.findByLoginAndSenha(usuario, senha);

        if (usuarioBD != null) {
            telaMenu(this);
            this.dispose();
        } else {
            showMessageDialog(this, "Credenciais inválidas",
                    "Erro de Login", ERROR_MESSAGE);
        }
    }

    public static void encerrarSecao(JFrame telaAtual) {
        if (telaAtual != null) {
            telaAtual.dispose();
        }

        abrirTela();
        telaLoginInstance = new TelaLogin();
        invokeLater(() -> telaLoginInstance.setVisible(true));

    }

    public static void telaLogin() {
        abrirTela();
        invokeLater(() -> new TelaLogin().setVisible(true));
    }

    public static void abrirTela() {
        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            getLogger(TelaMenu.class.getName()).log(SEVERE, null, ex);
        }
    }
}
