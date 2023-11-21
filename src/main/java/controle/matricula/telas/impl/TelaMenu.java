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
import static java.lang.Short.MAX_VALUE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.GroupLayout.Alignment.TRAILING;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import static javax.swing.SwingConstants.CENTER;

/**
 * Representa a tela do menu principal do sistema.
 */
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
    private final JLabel mensagem = new JLabel();
    private static TelaMenu telaMenuInstance;

    /**
     * Construtor da classe TelaMenu.
     */
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

    /**
     * Inicializa os componentes da tela.
     */
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

        mensagem.setText("SysControl");
        mensagem.setHorizontalAlignment(CENTER);
        mensagem.setFont(new Font("Arial", Font.BOLD, 25));

        var panelBtnLayout = new GroupLayout(panelBtn);
        panelBtn.setLayout(panelBtnLayout);
        panelBtnLayout.setHorizontalGroup(
                panelBtnLayout.createParallelGroup(LEADING)
                        .addGroup(panelBtnLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBtnLayout.createParallelGroup(LEADING, false)
                                        .addComponent(btnDisciplinaProfessor, TRAILING, PREFERRED_SIZE, 0, MAX_VALUE)
                                        .addComponent(btnDisciplinaAluno, TRAILING, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                        .addComponent(btnFaturamento, DEFAULT_SIZE, 109, MAX_VALUE)
                                        .addComponent(btnUsuario, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                        .addComponent(btnPessoa, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                        .addComponent(btnDisciplina, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                        .addComponent(btnMatricula, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE))
                                .addContainerGap(118, MAX_VALUE))
        );
        panelBtnLayout.setVerticalGroup(
                panelBtnLayout.createParallelGroup(LEADING)
                        .addGroup(panelBtnLayout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE)
                                .addComponent(btnMatricula, PREFERRED_SIZE, 60, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(btnDisciplina, PREFERRED_SIZE, 63, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(btnPessoa, PREFERRED_SIZE, 65, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(btnUsuario, PREFERRED_SIZE, 62, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(btnFaturamento, PREFERRED_SIZE, 61, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(btnDisciplinaAluno, PREFERRED_SIZE, 60, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(btnDisciplinaProfessor, PREFERRED_SIZE, 58, PREFERRED_SIZE)
                                .addGap(16, 16, 16))
        );

        var panelImgLayout = new GroupLayout(panelImg);
        panelImg.setLayout(panelImgLayout);
        panelImgLayout.setHorizontalGroup(
                panelImgLayout.createParallelGroup(LEADING)
                        .addGroup(panelImgLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelImgLayout.createParallelGroup(LEADING)
                                        .addGroup(panelImgLayout.createSequentialGroup()
                                                .addGap(0, 269, MAX_VALUE)
                                                .addComponent(btnEncerrarSecao))
                                        .addComponent(mensagem, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE))
                                .addContainerGap())
        );
        panelImgLayout.setVerticalGroup(
                panelImgLayout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, panelImgLayout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE)
                                .addComponent(mensagem, PREFERRED_SIZE, 154, PREFERRED_SIZE)
                                .addGap(148, 148, 148)
                                .addComponent(btnEncerrarSecao)
                                .addContainerGap())
        );

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, layout.createSequentialGroup()
                                .addComponent(panelBtn, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(panelImg, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addContainerGap(82, MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, layout.createSequentialGroup()
                                .addComponent(panelBtn, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                .addContainerGap())
                        .addComponent(panelImg, TRAILING, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
        );
    }

    /**
     * Método estático para exibir a tela do menu principal.
     *
     * @param telaAtual A tela atual que será fechada.
     */
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