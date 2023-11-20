package controle.matricula.telas.impl;

import controle.matricula.dao.impl.DisciplinaDAOImpl;
import controle.matricula.dao.impl.PessoaDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static controle.matricula.telas.telabase.TelaPrincipalBase.AVISO;
import static java.awt.EventQueue.invokeLater;
import static java.awt.Font.BOLD;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

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

    private void initComponents() {

        titulo.setText("Disciplinas Ministradas");

        jScrollPane1.setViewportView(jList1);

        campoNomeProfessor.setText("Nome do Aluno:");

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(this::btnPesquisar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(158, 158, 158)
                                                .addComponent(titulo))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addComponent(campoNomeProfessor)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(textNomeProfessor))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                        .addGap(140, 140, 140)
                                                                        .addComponent(campoProfessor)))
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnPesquisar))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(669, 669, 669)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(titulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoNomeProfessor)
                                        .addComponent(textNomeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPesquisar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(campoProfessor)
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(135, 135, 135)
                                        .addComponent(jLabel2)
                                        .addContainerGap(148, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 15, Short.MAX_VALUE))
        );
    }

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

    public static void telaDisciplinaMinistrada() {

        try {
            for (UIManager.LookAndFeelInfo info : getInstalledLookAndFeels()) {
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

