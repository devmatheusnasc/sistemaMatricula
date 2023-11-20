package controle.matricula.telas.impl;

import controle.matricula.model.Disciplina;
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

public class TelaSecundariaDisciplina extends JFrame {

    private JPanel jPanel = new JPanel();
    private JLabel campoNomeDisciplina = new JLabel();
    private JLabel campoCargaHoraria = new JLabel();
    private JLabel campoProfessor = new JLabel();
    private JLabel campoLimiteAlunos = new JLabel();
    private JTextField textNomeDisciplina = new JTextField();
    private JTextField textCargaHoraria = new JTextField();
    private JTextField textProfessor = new JTextField();
    private JTextField textLimiteAlunos = new JTextField();
    private JButton btnCancelar = new JButton();
    private JButton btnConfirmar = new JButton();
    private Operacao operacao;
    private int id;

    public TelaSecundariaDisciplina() {
        operacao = INSERIR;

        inicializarCampos();
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public TelaSecundariaDisciplina(Disciplina disciplina) {
        operacao = ATUALIZAR;
        id = disciplina.getCodigo();

        inicializarCampos();
        preencherCampo(disciplina);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {

        campoNomeDisciplina.setText("Nome Disciplina:");
        campoCargaHoraria.setText("Carga Horaria:");
        campoProfessor.setText("Professor:");
        campoLimiteAlunos.setText("Limite Alunos:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelar);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(this::btnConfirmar);

        var jPanel1Layout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnCancelar)
                                                .addPreferredGap(RELATED, DEFAULT_SIZE, MAX_VALUE)
                                                .addComponent(btnConfirmar))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(campoNomeDisciplina)
                                                .addPreferredGap(RELATED, DEFAULT_SIZE, MAX_VALUE)
                                                .addComponent(textNomeDisciplina, PREFERRED_SIZE, 245, PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(TRAILING)
                                                        .addComponent(campoProfessor, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                                        .addComponent(campoCargaHoraria, DEFAULT_SIZE, 98, MAX_VALUE)
                                                        .addComponent(campoLimiteAlunos, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE))
                                                .addGap(29, 29, 29)
                                                .addGroup(jPanel1Layout.createParallelGroup(LEADING)
                                                        .addComponent(textCargaHoraria, PREFERRED_SIZE, 245, PREFERRED_SIZE)
                                                        .addComponent(textProfessor, TRAILING, PREFERRED_SIZE, 245, PREFERRED_SIZE)
                                                        .addComponent(textLimiteAlunos, TRAILING, PREFERRED_SIZE, 245, PREFERRED_SIZE))))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(campoNomeDisciplina)
                                        .addComponent(textNomeDisciplina, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(textCargaHoraria, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(campoCargaHoraria, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE))
                                .addPreferredGap(UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(textProfessor, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(campoProfessor))
                                .addPreferredGap(UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(textLimiteAlunos, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(campoLimiteAlunos))
                                .addPreferredGap(RELATED, 27, MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(btnCancelar)
                                        .addComponent(btnConfirmar)))
        );

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(jPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE))
        );
    }

    private void btnCancelar(ActionEvent evt) {
        var frame = (JFrame) getRoot((Component) evt.getSource());
        frame.dispose();
    }

    private void btnConfirmar(ActionEvent evt) {
        var telaPrincipal = new TelaPrincipalDisciplina();
        if (telaPrincipal.processarUsuario(id, textNomeDisciplina, textCargaHoraria, textProfessor, textLimiteAlunos, operacao)) {
            dispose();
        }
    }

    private void preencherCampo(Disciplina disciplina) {
        textNomeDisciplina.setText(disciplina.getNomeDisciplina());
        textCargaHoraria.setText(String.valueOf(disciplina.getCargaHoraria()));
        textProfessor.setText(disciplina.getProfessor().getNomePessoa());
        textLimiteAlunos.setText(String.valueOf(disciplina.getLimiteAlunos()));
    }

    private void inicializarCampos() {
        textNomeDisciplina = new JTextField();
        textCargaHoraria = new JTextField();
        textProfessor = new JTextField();
        textLimiteAlunos = new JTextField();
    }

    public void telaSecundariaDisciplina(Disciplina disciplina) {
        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            getLogger(TelaSecundariaDisciplina.class.getName()).log(SEVERE, null, ex);
        }

        if (disciplina == null) {
            invokeLater(() -> new TelaSecundariaDisciplina().setVisible(true));
        } else {
            invokeLater(() -> new TelaSecundariaDisciplina(disciplina).setVisible(true));
        }
    }
}
