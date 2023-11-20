package controle.matricula.telas.impl;

import controle.matricula.dao.impl.MatriculaDAOImpl;
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
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.JOptionPane.*;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

public class TelaDisciplinaCursada extends JFrame {

    private JLabel campoAluno = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JList<String> jList1 = new JList<>();
    private JPanel jPanel1 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JSeparator jSeparator1 = new JSeparator();
    private JButton btnPesquisar = new JButton();
    private JLabel campoNomeAluno = new JLabel();
    private JTextField textNomeAluno = new JTextField();

    private JLabel titulo = new JLabel();

    public TelaDisciplinaCursada() {
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("SysControl");

        titulo.setFont(new Font("Arial", BOLD, 15));
        campoAluno.setFont(new Font("Arial", BOLD, 12));
    }

    private void initComponents() {

        titulo.setText("Disciplinas Cursadas");

        jScrollPane1.setViewportView(jList1);

        campoNomeAluno.setText("Nome do Aluno:");

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
                                                                        .addComponent(campoNomeAluno)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(textNomeAluno))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                        .addGap(140, 140, 140)
                                                                        .addComponent(campoAluno)))
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnPesquisar))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(jSeparator1, PREFERRED_SIZE, 434, PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(jScrollPane1, PREFERRED_SIZE, 319, PREFERRED_SIZE)))
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(669, 669, 669)
                                        .addComponent(jLabel2, PREFERRED_SIZE, 73, PREFERRED_SIZE)
                                        .addContainerGap(DEFAULT_SIZE, MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(titulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoNomeAluno)
                                        .addComponent(textNomeAluno, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(btnPesquisar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, MAX_VALUE)
                                .addComponent(campoAluno)
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane1, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(135, 135, 135)
                                        .addComponent(jLabel2)
                                        .addContainerGap(148, MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jPanel1, PREFERRED_SIZE, 449, PREFERRED_SIZE)
                                .addContainerGap(17, MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addGap(0, 15, MAX_VALUE))
        );
    }

    private void btnPesquisar(ActionEvent evt) {

        var input = textNomeAluno.getText();
        var pessoaDAO = new PessoaDAOImpl();

        var aluno = pessoaDAO.findByNomePessoa(input);
        if (aluno != null && aluno.getTipo().equals("Aluno")) {
            campoAluno.setText(input);
            listDisciplina(aluno.getIdPessoa());
        } else {
            showMessageDialog(null, "Aluno Não Encontrado.", AVISO, WARNING_MESSAGE);
        }

    }

    private void listDisciplina(int idAluno) {
        var matriculaDAO = new MatriculaDAOImpl();

        List<String> nomesDisciplinas = matriculaDAO.findDisciplinaByAluno(idAluno);
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
        textNomeAluno.setText("");
    }

    public static void telaDisciplinaCursada() {

        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            getLogger(TelaDisciplinaCursada.class.getName()).log(SEVERE, null, ex);
        }
        invokeLater(() -> new TelaDisciplinaCursada().setVisible(true));
    }
}
