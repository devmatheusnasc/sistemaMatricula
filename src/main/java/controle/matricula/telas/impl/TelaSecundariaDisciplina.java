package controle.matricula.telas.impl;

import controle.matricula.dao.impl.PessoaDAOImpl;
import controle.matricula.model.Disciplina;
import controle.matricula.model.Pessoa;
import controle.matricula.util.Operacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static controle.matricula.util.Operacao.ATUALIZAR;
import static controle.matricula.util.Operacao.INSERIR;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.GroupLayout.Alignment.*;
import static java.lang.Short.MAX_VALUE;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;
import static javax.swing.SwingUtilities.getRoot;
import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.UIManager.*;

/**
 * Tela secundária para manipulação de disciplinas.
 */
public class TelaSecundariaDisciplina extends JFrame {

    private JPanel jPanel = new JPanel();
    private JLabel campoNomeDisciplina = new JLabel();
    private JLabel campoCargaHoraria = new JLabel();
    private JLabel campoProfessor = new JLabel();
    private JLabel campoLimiteAlunos = new JLabel();
    private JTextField textNomeDisciplina = new JTextField();
    private JTextField textCargaHoraria = new JTextField();
    private JComboBox<Pessoa> textProfessor;
    private JTextField textLimiteAlunos = new JTextField();
    private JButton btnCancelar = new JButton();
    private JButton btnConfirmar = new JButton();
    private Operacao operacao;
    private int id;

    /**
     * Construtor para uma nova disciplina (inserção).
     */
    public TelaSecundariaDisciplina() {
        operacao = INSERIR;

        inicializarCampos();
        professorSetComboBox();
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Construtor para a edição de uma disciplina existente.
     *
     * @param disciplina A disciplina a ser editada.
     */
    public TelaSecundariaDisciplina(Disciplina disciplina) {
        operacao = ATUALIZAR;
        id = disciplina.getCodigo();

        inicializarCampos();
        professorSetComboBox(disciplina);
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

    /**
     * Fecha a janela ao clicar no botão Cancelar.
     *
     * @param evt Evento de ação.
     */
    private void btnCancelar(ActionEvent evt) {
        var frame = (JFrame) getRoot((Component) evt.getSource());
        frame.dispose();
    }

    /**
     * Confirma a operação e fecha a janela ao clicar no botão Confirmar.
     *
     * @param evt Evento de ação.
     */
    private void btnConfirmar(ActionEvent evt) {
        var telaPrincipal = new TelaPrincipalDisciplina();
        if (telaPrincipal.processarUsuario(id, textNomeDisciplina, textCargaHoraria, textProfessor, textLimiteAlunos, operacao)) {
            dispose();
        }
    }

    /**
     * Preenche os campos da tela com os dados da disciplina.
     *
     * @param disciplina Objeto Disciplina contendo os dados.
     */
    private void preencherCampo(Disciplina disciplina) {
        textNomeDisciplina.setText(disciplina.getNomeDisciplina());
        textCargaHoraria.setText(String.valueOf(disciplina.getCargaHoraria()));
        textLimiteAlunos.setText(String.valueOf(disciplina.getLimiteAlunos()));
    }

    /**
     * Inicializa os campos da tela.
     */
    private void inicializarCampos() {
        textNomeDisciplina = new JTextField();
        textCargaHoraria = new JTextField();
        textProfessor = new JComboBox<>();
        textLimiteAlunos = new JTextField();
    }

    /**
     * Configura o ComboBox de professores com base na disciplina fornecida.
     *
     * @param disciplina Objeto Disciplina contendo os dados.
     */
    private void professorSetComboBox(Disciplina disciplina) {
        var professorAtual = disciplina.getProfessor().getNomePessoa();

        var pessoaDAO = new PessoaDAOImpl();
        var professor = pessoaDAO.findAll();

        var professorList = professor.stream()
                .filter(pes -> pes.getTipo().equals("Professor"))
                .filter(pes -> !pes.getNomePessoa().equals(professorAtual))
                .toList();

        var lista = new ArrayList<Pessoa>();

        lista.add(new Pessoa(0, professorAtual, "", "", "", "", "", "Professor"));

        lista.addAll(professorList);

        var pessoaComboBoxModel = new DefaultComboBoxModel<>(lista.toArray(new Pessoa[0]));

        textProfessor.setModel(pessoaComboBoxModel);
    }

    /**
     * Configura o ComboBox de professores sem considerar uma disciplina específica.
     */
    private void professorSetComboBox() {
        var pessoaDAO = new PessoaDAOImpl();
        var professor = pessoaDAO.findAll();

        var professorList = professor.stream()
                .filter(pes -> pes.getTipo().equals("Professor"))
                .toList();
        var pessoaComboBoxModel = new DefaultComboBoxModel<>(professorList.toArray(new Pessoa[0]));

        textProfessor.setModel(pessoaComboBoxModel);
    }


    /**
     * Método para exibir a tela secundária de disciplinas.
     *
     * @param disciplina A disciplina a ser editada (pode ser nulo para inserção).
     */
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
