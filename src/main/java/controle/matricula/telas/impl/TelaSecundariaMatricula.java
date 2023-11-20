package controle.matricula.telas.impl;

import controle.matricula.model.Matricula;
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
import static javax.swing.SwingUtilities.getRoot;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

public class TelaSecundariaMatricula extends JFrame {

    private JTextField textDisciplina;
    private JTextField textValorPago;
    private JTextField textAluno;
    private JTextField textPeriodo;
    private Operacao operacao;
    private int id;

    public TelaSecundariaMatricula() {
        operacao = Operacao.INSERIR;
        inicializarCampos();
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public TelaSecundariaMatricula(Matricula matricula) {
        operacao = Operacao.ATUALIZAR;
        id = matricula.getIdMat();
        inicializarCampos();
        preencherCampo(matricula);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {

        var panel = new JPanel();
        var campoDisciplina = new JLabel();
        var campoValorPago = new JLabel();
        var campoAluno = new JLabel();
        var campoPeriodo = new JLabel();
        var btnCancelar = new JButton();
        var btnConfirmar = new JButton();

        campoDisciplina.setText("Disciplina:");

        campoValorPago.setText("Valor Pago:");

        campoAluno.setText("Aluno:");

        campoPeriodo.setText("Período:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelar);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(this::btnConfirmar);

        var groupLayout = new GroupLayout(panel);
        panel.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(groupLayout.createParallelGroup(TRAILING, false)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(TRAILING, false)
                                                        .addComponent(campoAluno, LEADING, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                                        .addComponent(campoValorPago, LEADING, DEFAULT_SIZE, 89, MAX_VALUE)
                                                        .addComponent(campoDisciplina, LEADING, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                                        .addComponent(campoPeriodo, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE))
                                                .addGroup(groupLayout.createParallelGroup(LEADING, false)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(textPeriodo, PREFERRED_SIZE, 245, PREFERRED_SIZE))
                                                        .addGroup(TRAILING, groupLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(groupLayout.createParallelGroup(LEADING)
                                                                        .addComponent(textAluno, PREFERRED_SIZE, 245, PREFERRED_SIZE)
                                                                        .addComponent(textValorPago, PREFERRED_SIZE, 245, PREFERRED_SIZE)))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(textDisciplina, PREFERRED_SIZE, 245, PREFERRED_SIZE))))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(btnCancelar)
                                                .addPreferredGap(RELATED, DEFAULT_SIZE, MAX_VALUE)
                                                .addComponent(btnConfirmar)))
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(groupLayout.createParallelGroup(BASELINE)
                                        .addComponent(textDisciplina, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(campoDisciplina, PREFERRED_SIZE, 23, PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(groupLayout.createParallelGroup(BASELINE)
                                        .addComponent(campoValorPago, PREFERRED_SIZE, 23, PREFERRED_SIZE)
                                        .addComponent(textValorPago, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(groupLayout.createParallelGroup(BASELINE)
                                        .addComponent(campoAluno, PREFERRED_SIZE, 23, PREFERRED_SIZE)
                                        .addComponent(textAluno, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(groupLayout.createParallelGroup(BASELINE)
                                        .addComponent(campoPeriodo, PREFERRED_SIZE, 23, PREFERRED_SIZE)
                                        .addComponent(textPeriodo, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(groupLayout.createParallelGroup(BASELINE)
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
    }

    private void btnCancelar(ActionEvent evt) {
        var frame = (JFrame) getRoot((Component) evt.getSource());
        frame.dispose();
    }

    private void btnConfirmar(ActionEvent evt) {
        var telaPrincipal = new TelaPrincipalMatricula();
        if (telaPrincipal.processarMatricula(id, textDisciplina, textValorPago, textAluno, textPeriodo, operacao)) {
            dispose();
        }
    }

    private void preencherCampo(Matricula matricula) {
        textDisciplina.setText(matricula.getDisciplina().getNomeDisciplina());
        textValorPago.setText(String.valueOf(matricula.getValorPago()));
        textAluno.setText(matricula.getAluno().getNomePessoa());
        textPeriodo.setText(matricula.getPeriodo());
    }

    private void inicializarCampos() {
        textDisciplina = new JTextField();
        textValorPago = new JTextField();
        textAluno = new JTextField();
        textPeriodo = new JTextField();
    }

    public void telaSecundariaMatricula(Matricula matricula) {
        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            getLogger(TelaSecundariaMatricula.class.getName()).log(SEVERE, null, ex);
        }
        if (matricula == null) {
            invokeLater(() -> new TelaSecundariaMatricula().setVisible(true));
        } else {
            invokeLater(() -> new TelaSecundariaMatricula(matricula).setVisible(true));
        }
    }
}
