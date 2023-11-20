package controle.matricula.telas.impl;

import javax.swing.*;

import java.awt.*;

import static controle.matricula.telas.impl.TelaDisciplinaCursada.telaDisciplinaCursada;
import static controle.matricula.telas.impl.TelaDisciplinaMinistrada.telaDisciplinaMinistrada;
import static controle.matricula.telas.impl.TelaFaturamento.telaFaturamento;
import static controle.matricula.telas.impl.TelaLogin.encerrarSecao;
import static controle.matricula.telas.impl.TelaPrincipalDisciplina.telaDisciplina;
import static controle.matricula.telas.impl.TelaPrincipalMatricula.telaMatricula;
import static controle.matricula.telas.impl.TelaPrincipalPessoa.telaPessoa;
import static controle.matricula.telas.impl.TelaPrincipalUsuario.telaUsuario;
import static java.awt.EventQueue.invokeLater;
import static javax.swing.SwingConstants.CENTER;

public class TelaMenu extends JFrame {

    private final JButton btnDisciplina = new JButton();
    private final JButton btnEncerrarSecao = new JButton();
    private final JButton btnFaturamento = new JButton();
    private final JButton btnMatricula = new JButton();
    private final JButton btnPessoa = new JButton();
    private final JButton btnUsuario = new JButton();
    private final JButton btnDisciplinaAluno = new JButton();
    private final JButton btnDisciplinaProfessor = new JButton();
    private final JPanel panelBtn = new JPanel();
    private final JPanel panelImg = new JPanel();
    private final JLabel menssagem = new JLabel();
    private static TelaMenu telaMenuInstance;


    public TelaMenu() {
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setTitle("SysControl");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(NORMAL);
        toFront();
    }

    private void initComponents() {

        btnUsuario.setText("Usuario");
        btnUsuario.addActionListener(e -> telaUsuario());

        btnDisciplinaProfessor.setText("D. por Professor");
        btnDisciplinaProfessor.addActionListener(e -> telaDisciplinaMinistrada());

        btnMatricula.setText("Matricula");
        btnMatricula.addActionListener(e -> telaMatricula());

        btnFaturamento.setText("Faturamento");
        btnFaturamento.addActionListener(e -> telaFaturamento());

        btnDisciplinaAluno.setText("D. por Aluno");
        btnDisciplinaAluno.addActionListener(e -> telaDisciplinaCursada());

        btnPessoa.setText("Pessoa");
        btnPessoa.addActionListener(e -> telaPessoa());

        btnDisciplina.setText("Disciplina");
        btnDisciplina.addActionListener(e -> telaDisciplina());

        btnEncerrarSecao.setText("Encerrar Seção");
        btnEncerrarSecao.addActionListener(e -> encerrarSecao(this));

        menssagem.setText("SysControl");
        menssagem.setHorizontalAlignment(CENTER);
        menssagem.setFont(new Font("Arial", Font.BOLD, 25));

        javax.swing.GroupLayout panelBtnLayout = new javax.swing.GroupLayout(panelBtn);
        panelBtn.setLayout(panelBtnLayout);
        panelBtnLayout.setHorizontalGroup(
                panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBtnLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnDisciplinaProfessor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(btnDisciplinaAluno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnFaturamento, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                        .addComponent(btnUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnPessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(118, Short.MAX_VALUE))
        );
        panelBtnLayout.setVerticalGroup(
                panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBtnLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFaturamento, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDisciplinaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDisciplinaProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
        );


        javax.swing.GroupLayout panelImgLayout = new javax.swing.GroupLayout(panelImg);
        panelImg.setLayout(panelImgLayout);
        panelImgLayout.setHorizontalGroup(
                panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelImgLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelImgLayout.createSequentialGroup()
                                                .addGap(0, 269, Short.MAX_VALUE)
                                                .addComponent(btnEncerrarSecao))
                                        .addComponent(menssagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panelImgLayout.setVerticalGroup(
                panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImgLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(menssagem, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(148, 148, 148)
                                .addComponent(btnEncerrarSecao)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(panelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(panelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addComponent(panelImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
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
