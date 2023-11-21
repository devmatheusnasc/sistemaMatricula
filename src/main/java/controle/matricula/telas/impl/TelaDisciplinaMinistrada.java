package controle.matricula.telas.impl;

import controle.matricula.dao.impl.DisciplinaDAOImpl;
import controle.matricula.dao.impl.PessoaDAOImpl;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static controle.matricula.telas.telabase.TelaPrincipalBase.AVISO;
import static java.awt.EventQueue.invokeLater;
import static java.awt.Font.BOLD;
import static java.lang.Short.MAX_VALUE;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

/**
 * Representa a tela de Disciplinas Ministradas.
 */
public class TelaDisciplinaMinistrada extends JFrame {

    private JLabel campoProfessor = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JList<String> jList1 = new JList<>();
    private JPanel jPanel1 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JSeparator jSeparator1 = new JSeparator();
    private JButton btnPesquisar = new JButton();
    private JLabel campoNomeProfessor = new JLabel();
    private JTextField textNomeProfessor = new JTextField();

    private JLabel titulo = new JLabel();

    /**
     * Construtor da classe TelaDisciplinaMinistrada.
     */
    public TelaDisciplinaMinistrada() {
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("SysControl");

        titulo.setFont(new Font("Arial", BOLD, 15));
        campoProfessor.setFont(new Font("Arial", BOLD, 12));
    }

    /**
     * Inicializa os componentes da tela.
     */
    private void initComponents() {

        titulo.setText("Disciplinas Ministradas");

        jScrollPane1.setViewportView(jList1);

        campoNomeProfessor.setText("Nome do Aluno:");

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(this::btnPesquisar);

        var jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(158, 158, 158)
                                                .addComponent(titulo))
                                        .addGroup(jPanel1Layout.createParallelGroup(TRAILING, false)
                                                .addGroup(LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(TRAILING)
                                                                .addGroup(LEADING, jPanel1Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addComponent(campoNomeProfessor)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(textNomeProfessor))
                                                                .addGroup(LEADING, jPanel1Layout.createSequentialGroup()
                                                                        .addGap(140, 140, 140)
                                                                        .addComponent(campoProfessor)))
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnPesquisar))
                                                .addGroup(LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(jSeparator1, PREFERRED_SIZE, 434, PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(jScrollPane1, PREFERRED_SIZE, 319, PREFERRED_SIZE)))
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(669, 669, 669)
                                        .addComponent(jLabel2, PREFERRED_SIZE, 73, PREFERRED_SIZE)
                                        .addContainerGap(DEFAULT_SIZE, MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(titulo)
                                .addPreferredGap(UNRELATED)
                                .addComponent(jSeparator1, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(BASELINE)
                                        .addComponent(campoNomeProfessor)
                                        .addComponent(textNomeProfessor, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(btnPesquisar))
                                .addPreferredGap(RELATED, 9, MAX_VALUE)
                                .addComponent(campoProfessor)
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane1, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(135, 135, 135)
                                        .addComponent(jLabel2)
                                        .addContainerGap(148, MAX_VALUE)))
        );

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jPanel1, PREFERRED_SIZE, 449, PREFERRED_SIZE)
                                .addContainerGap(17, MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addGap(0, 15, MAX_VALUE))
        );
    }

    /**
     * Método acionado pelo botão de pesquisa.
     *
     * @param evt Evento associado à ação de pesquisa.
     */
    private void btnPesquisar(ActionEvent evt) {

        var input = textNomeProfessor.getText();
        var pessoaDAO = new PessoaDAOImpl();

        var professor = pessoaDAO.findByNomePessoa(input);
        if (professor != null && professor.getTipo().equals("Professor")) {
            campoProfessor.setText(input);
            listDisciplina(professor.getIdPessoa());
        } else {
            showMessageDialog(null, "Professor Não Encontrado.", AVISO, WARNING_MESSAGE);
        }

    }

    /**
     * Lista as disciplinas ministradas pelo professor.
     *
     * @param idProfessor ID do professor.
     */
    private void listDisciplina(int idProfessor) {
        var disciplinaDAO = new DisciplinaDAOImpl();

        List<String> nomesDisciplinas = disciplinaDAO.findDisciplinaByProfessor(idProfessor);
        var setList = nomesDisciplinas.toArray(new String[0]);

        jList1.setModel(new AbstractListModel<String>() {
            String[] strings = setList;

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        textNomeProfessor.setText("");
    }

    /**
     * Método estático para exibir a tela de Disciplinas Ministradas.
     */
    public static void telaDisciplinaMinistrada() {

        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            getLogger(TelaDisciplinaMinistrada.class.getName()).log(SEVERE, null, ex);
        }
        invokeLater(() -> new TelaDisciplinaMinistrada().setVisible(true));
    }
}
