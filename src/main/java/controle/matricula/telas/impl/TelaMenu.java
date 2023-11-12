package controle.matricula.telas.impl;

import javax.swing.*;

import static controle.matricula.telas.impl.TelaFaturamento.telaFaturamento;
import static controle.matricula.telas.impl.TelaLogin.encerrarSecao;
import static controle.matricula.telas.impl.TelaPrincipalDisciplina.telaDisciplina;
import static controle.matricula.telas.impl.TelaPrincipalMatricula.telaMatricula;
import static controle.matricula.telas.impl.TelaPrincipalPessoa.telaPessoa;
import static controle.matricula.telas.impl.TelaPrincipalUsuario.telaUsuario;
import static java.awt.EventQueue.invokeLater;
import static java.lang.Short.MAX_VALUE;
import static javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

public class TelaMenu extends JFrame {

    private final JButton btDisciplina = new JButton();
    private final JButton btEncerrar = new JButton();
    private final JButton btFaturamento = new JButton();
    private final JButton btMatricula = new JButton();
    private final JButton btPessoa = new JButton();
    private final JButton btTelaUsuario = new JButton();
    private static TelaMenu telaMenuInstance;


    public TelaMenu() {
        initComponents();
    }

    private void initComponents() {

        setTitle("SysControl");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(NORMAL);
        toFront();

        btEncerrar.setText("Encerrar Seção");
        btEncerrar.addActionListener(e -> encerrarSecao(this));

        btMatricula.setText("matricula");
        btMatricula.addActionListener(e -> telaMatricula());

        btTelaUsuario.setText("Usuario");
        btTelaUsuario.addActionListener(e -> telaUsuario());

        btPessoa.setText("Pessoa");
        btPessoa.addActionListener(e -> telaPessoa());

        btFaturamento.setText("Faturamento");
        btFaturamento.addActionListener(e -> telaFaturamento());

        btDisciplina.setText("Disciplina");
        btDisciplina.addActionListener(e -> telaDisciplina());

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btMatricula, PREFERRED_SIZE, 111, PREFERRED_SIZE)
                                                .addPreferredGap(RELATED, DEFAULT_SIZE, MAX_VALUE)
                                                .addComponent(btDisciplina, PREFERRED_SIZE, 122, PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btPessoa, PREFERRED_SIZE, 111, PREFERRED_SIZE)
                                                .addGap(318, 318, 318)
                                                .addComponent(btTelaUsuario, DEFAULT_SIZE, 114, MAX_VALUE)))
                                .addGap(34, 34, 34))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(246, 246, 246)
                                .addComponent(btFaturamento, PREFERRED_SIZE, 111, PREFERRED_SIZE)
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btEncerrar)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btMatricula, PREFERRED_SIZE, 89, PREFERRED_SIZE)
                                        .addComponent(btDisciplina, PREFERRED_SIZE, 89, PREFERRED_SIZE))
                                .addPreferredGap(RELATED, 50, MAX_VALUE)
                                .addComponent(btFaturamento, PREFERRED_SIZE, 91, PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btPessoa, PREFERRED_SIZE, 84, PREFERRED_SIZE)
                                        .addComponent(btTelaUsuario, PREFERRED_SIZE, 89, PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addComponent(btEncerrar)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }

    public static void telaMenu(JFrame telaAtual) {
        if (telaAtual != null) {
            telaAtual.dispose();
        }

        if (telaMenuInstance == null) {
            TelaLogin.abrirTela();

            telaMenuInstance = new TelaMenu();
            invokeLater(() -> telaMenuInstance.setVisible(true));
        } else {
            telaMenuInstance.setExtendedState(NORMAL);
            telaMenuInstance.setVisible(true);
            telaMenuInstance.toFront();
        }
    }

}
