package controle.matricula.telas.impl;

import controle.matricula.dao.impl.DisciplinaDAOImpl;
import controle.matricula.dao.impl.MatriculaDAOImpl;
import controle.matricula.model.Disciplina;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;

import static controle.matricula.telas.impl.TelaMenu.telaMenu;
import static java.awt.EventQueue.invokeLater;
import static java.awt.Font.BOLD;
import static java.lang.Short.MAX_VALUE;
import static java.lang.String.valueOf;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

public class TelaFaturamento extends JFrame {

    private JPanel jPanel = new JPanel();
    private JButton btnMenu = new JButton();
    private JLabel jLabel = new JLabel();
    private JSeparator jSeparator1 = new JSeparator();
    private JLabel selecionarDisciplina = new JLabel();
    private JLabel selecionarPeriodo = new JLabel();
    private JLabel textRS = new JLabel();
    private JComboBox<String> textSelectDisciplina = new JComboBox<>();
    private JComboBox<String> textSelectPeriodo = new JComboBox<>();
    private JLabel textValorObtido = new JLabel();
    private JLabel titulo = new JLabel();
    private JLabel valorObtido = new JLabel();

    private static TelaFaturamento telaMenuInstance;

    public TelaFaturamento() {
        periodoSelectModel();
        disciplinasSelectModel();
        valorObtidoSelectModel();
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setSize(750, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("SysControl");
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMenu.setText("Menu");
        btnMenu.addActionListener(this::btnMenu);

        titulo.setText("Consultar Faturamento");
        titulo.setFont(new Font("Arial", BOLD, 15));


        selecionarPeriodo.setText("Selecionar Periodo:");
        textSelectPeriodo.addActionListener(this::btnPeriodo);

        selecionarDisciplina.setText("Selecionar Disciplina:");
        textSelectDisciplina.addActionListener(this::btnPeriodo);

        textRS.setText("R$:");

        valorObtido.setText("Valor obtido:");

        var jPanel1Layout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(valorObtido)
                                                .addPreferredGap(RELATED, DEFAULT_SIZE, MAX_VALUE)
                                                .addComponent(textRS)
                                                .addGap(18, 18, 18)
                                                .addComponent(textValorObtido, PREFERRED_SIZE, 72, PREFERRED_SIZE))
                                        .addGroup(LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(selecionarDisciplina, PREFERRED_SIZE, 138, PREFERRED_SIZE)
                                                .addPreferredGap(RELATED, DEFAULT_SIZE, MAX_VALUE)
                                                .addComponent(textSelectDisciplina, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(selecionarPeriodo, PREFERRED_SIZE, 138, PREFERRED_SIZE)
                                                .addPreferredGap(RELATED, DEFAULT_SIZE, MAX_VALUE)
                                                .addComponent(textSelectPeriodo, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(LEADING)
                                                .addComponent(jSeparator1, PREFERRED_SIZE, 536, PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(btnMenu)
                                                        .addGap(161, 161, 161)
                                                        .addComponent(titulo))))
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(669, 669, 669)
                                        .addComponent(jLabel, PREFERRED_SIZE, 73, PREFERRED_SIZE)
                                        .addContainerGap(DEFAULT_SIZE, MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(titulo)
                                        .addComponent(btnMenu))
                                .addGap(12, 12, 12)
                                .addComponent(jSeparator1, PREFERRED_SIZE, 19, PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(textSelectPeriodo, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(selecionarPeriodo))
                                .addPreferredGap(RELATED, 63, MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(textSelectDisciplina, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(selecionarDisciplina))
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(textValorObtido)
                                        .addComponent(textRS)
                                        .addComponent(valorObtido))
                                .addGap(78, 78, 78))
                        .addGroup(jPanel1Layout.createParallelGroup(LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(135, 135, 135)
                                        .addComponent(jLabel)
                                        .addContainerGap(215, MAX_VALUE)))
        );

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jPanel, PREFERRED_SIZE, 575, PREFERRED_SIZE)
                                .addContainerGap(65, MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addContainerGap(26, MAX_VALUE))
        );
    }

    private void btnMenu(ActionEvent evt) {
        telaMenu(this);
    }

    private void btnPeriodo(ActionEvent evt) {
        valorObtidoSelectModel();
    }

    private void periodoSelectModel() {

        String[] semestres = new String[12];
        for (int i = 0; i < 12; i++) {
            semestres[i] = (i + 1) + "º Semestre ";
        }

        textSelectPeriodo.setModel(new DefaultComboBoxModel<>(semestres));
    }

    private void disciplinasSelectModel() {
        var disciplinaDAO = new DisciplinaDAOImpl();
        var disciplinas = disciplinaDAO.findAllByNome();

        ArrayList<String> nomesDisciplinas = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            nomesDisciplinas.add(disciplina.getNomeDisciplina());
        }

        var arrayNomesDisciplinas = nomesDisciplinas.toArray(new String[0]);

        textSelectDisciplina.setModel(new DefaultComboBoxModel<>(arrayNomesDisciplinas));
    }

    private void valorObtidoSelectModel() {
        String disciplinaSelecionada = (String) textSelectDisciplina.getSelectedItem();
        String periodoSelecionado = (String) textSelectPeriodo.getSelectedItem();

        var disciplinaDAO = new DisciplinaDAOImpl();
        var disciplinaNome = disciplinaDAO.findByNome(disciplinaSelecionada);
        var matriculaDAO = new MatriculaDAOImpl();
        var valorPorMatricula = matriculaDAO.findValorPagoByNomeDisciplina(disciplinaNome.getCodigo());
        var numeroPeriodo = Integer.parseInt(Objects.requireNonNull(periodoSelecionado).replaceAll("\\D", ""));
        var valorFinal = valorPorMatricula * numeroPeriodo;
        textValorObtido.setText(valueOf(valorFinal));
    }


    public static void telaFaturamento() {

        if (telaMenuInstance == null) {
            try {
                for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ex) {
                getLogger(TelaFaturamento.class.getName()).log(SEVERE, null, ex);
            }

            telaMenuInstance = new TelaFaturamento();
            invokeLater(() -> telaMenuInstance.setVisible(true));
        } else {
            telaMenuInstance.setExtendedState(NORMAL);
            telaMenuInstance.setVisible(true);
            telaMenuInstance.toFront();
        }
    }
}
