package controle.matricula.telas.telabase;

import controle.matricula.dao.DAO;
import controle.matricula.telas.impl.TelaMenu;
import controle.matricula.util.table.TableColumnAdjuster;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static controle.matricula.telas.impl.TelaMenu.telaMenu;
import static java.awt.EventQueue.invokeLater;
import static java.awt.Font.BOLD;
import static java.lang.Short.MAX_VALUE;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static java.util.stream.IntStream.range;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.JOptionPane.*;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import static javax.swing.UIManager.getInstalledLookAndFeels;

public abstract class TelaPrincipalBase<T, D extends DAO<T>> extends JFrame {

    protected JTable tabelaPrincipal;
    protected JTextField campoPesquisar;
    protected JLabel labelPesquisar;
    protected JLabel labelTitulo;

    private static final Map<Class<? extends JFrame>, JFrame> telaInstances = new HashMap<>();

    public static final String AVISO = "AVISO";

    public TelaPrincipalBase() {
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setSize(750, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {

        var btnPesquisar = new JButton();
        var btnExcluir = new JButton();
        var btnInserir = new JButton();
        var btnAtualizar = new JButton();
        var btnMenu = new JButton();


        var panelTitulo = new JPanel();
        var panelPrincipal = new JPanel();
        var panelCampos = new JPanel();
        var jScrollPaneTabela = new JScrollPane();

        labelTitulo = new JLabel();
        labelPesquisar = new JLabel();
        campoPesquisar = new JTextField();
        tabelaPrincipal = new JTable();



        btnMenu.setText("Menu");
        btnMenu.addActionListener(e -> telaMenu(this));

        setTitle("SysControl");
        labelTitulo.setFont(new Font("Arial", BOLD, 15));

        var jPanel18Layout = new GroupLayout(panelTitulo);
        panelTitulo.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
                jPanel18Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(LEADING)
                                        .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnMenu))
                                        .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addGap(246, 246, 246)
                                                .addComponent(labelTitulo)))
                                .addContainerGap(268, MAX_VALUE))
        );

        jPanel18Layout.setVerticalGroup(
                jPanel18Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel18Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnMenu)
                                .addPreferredGap(RELATED)
                                .addComponent(labelTitulo)
                                .addContainerGap(8, MAX_VALUE))
        );

        panelPrincipal.setBorder(createLineBorder(new java.awt.Color(204, 204, 204)));

        labelPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
        configurarCampoPesquisa();

        campoPesquisar.addActionListener(this::listarComEnter);
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setFocusable(false);
        btnPesquisar.addActionListener(this::listarAction);

        btnInserir.addActionListener(this::inserir);

        btnAtualizar.addActionListener(this::editar);

        btnExcluir.addActionListener(this::excluirAction);


        var jPanel12Layout = new GroupLayout(panelCampos);
        panelCampos.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelPesquisar)
                                .addGap(18, 18, 18)
                                .addComponent(campoPesquisar, PREFERRED_SIZE, 263, PREFERRED_SIZE)
                                .addPreferredGap(RELATED, 47, MAX_VALUE)
                                .addComponent(btnPesquisar)
                                .addGap(34, 34, 34))
        );
        jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, jPanel12Layout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(LEADING, false)
                                        .addComponent(btnPesquisar, TRAILING, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                        .addGroup(TRAILING, jPanel12Layout.createParallelGroup(BASELINE)
                                                .addComponent(labelPesquisar)
                                                .addComponent(campoPesquisar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)))
                                .addContainerGap())
        );

        configurarCamposTabela();

        jScrollPaneTabela.setViewportView(tabelaPrincipal);

        btnExcluir.setText("Excluir");

        btnAtualizar.setText("Editar");

        btnInserir.setText("Inserir");

        var jPanel19Layout = new GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
                jPanel19Layout.createParallelGroup(LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPaneTabela)
                                .addContainerGap())
                        .addGroup(TRAILING, jPanel19Layout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE)
                                .addComponent(btnExcluir)
                                .addGap(18, 18, 18)
                                .addComponent(btnAtualizar)
                                .addGap(18, 18, 18)
                                .addComponent(btnInserir)
                                .addGap(50, 50, 50))
                        .addGroup(jPanel19Layout.createParallelGroup(LEADING)
                                .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(panelCampos, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                        .addContainerGap()))
        );
        jPanel19Layout.setVerticalGroup(
                jPanel19Layout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, jPanel19Layout.createSequentialGroup()
                                .addContainerGap(80, MAX_VALUE)
                                .addComponent(jScrollPaneTabela, PREFERRED_SIZE, 237, PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(jPanel19Layout.createParallelGroup(BASELINE)
                                        .addComponent(btnExcluir)
                                        .addComponent(btnAtualizar)
                                        .addComponent(btnInserir))
                                .addContainerGap())
                        .addGroup(jPanel19Layout.createParallelGroup(LEADING)
                                .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(panelCampos, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addContainerGap(238, MAX_VALUE)))
        );

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelTitulo, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addContainerGap(34, MAX_VALUE))
                        .addComponent(panelPrincipal, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelTitulo, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(panelPrincipal, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                                .addContainerGap(30, MAX_VALUE))
        );
    }

    protected void listar(D dao, String[] colunas) {
        try {
            var input = campoPesquisar.getText();
            List<T> itens = new ArrayList<>();

            if (!input.isEmpty()) {
                var item = dao.findById(Integer.parseInt(input));
                if (item != null) {
                    itens.add(item);
                } else {
                    showMessageDialog(null, "Item não encontrado.", AVISO, WARNING_MESSAGE);
                }
            } else {
                itens = dao.findAll();
            }

            var newTableModel = criarTableModel(itens);
            tabelaPrincipal.setModel(newTableModel);
            range(0, colunas.length)
                    .forEach(i -> tabelaPrincipal.getColumnModel().getColumn(i).setHeaderValue(colunas[i]));
            ajustarColunas();

        } catch (Exception e) {
            e.printStackTrace();
            showMessageDialog(null, "Erro ao Pesquisar.", "Erro", ERROR_MESSAGE);
        }
    }

    protected abstract TableModel criarTableModel(List<T> itens);

    private void ajustarColunas() {
        var adjuster = new TableColumnAdjuster(tabelaPrincipal);
        adjuster.adjustColumns();
    }

    protected int excluir() {
        var selectedRow = tabelaPrincipal.getSelectedRow();
        if (selectedRow < 0) {
            showMessageDialog(null, "Selecione um item para excluir.", AVISO, WARNING_MESSAGE);
            return -1;
        }

        var confirmar = showConfirmDialog(null,
                "Tem certeza de que deseja excluir este item?", "Confirmação", YES_NO_OPTION);
        if (confirmar != YES_OPTION) {
            return -1;
        }

        return (int) tabelaPrincipal.getValueAt(selectedRow, 0);
    }

    protected abstract void inserir(ActionEvent evt);

    protected abstract void editar(ActionEvent evt);

    protected abstract JPanel criarPainel(JTextField string, JTextField string2, JTextField string3, JTextField string4, JButton salvarButton);

    protected abstract boolean salvar(JTextField string, JTextField string2, JTextField string3, JTextField string4, JFrame frame);

    protected abstract boolean validarCampos(JTextField string, JTextField string2, JTextField string3, JTextField string4);

    protected abstract boolean validarCampoObrigatorio(JTextField campo);

    protected abstract boolean validarCampoDouble(JTextField campo);

    protected abstract T criar(String string, String string2, String string3, String string4);

    protected abstract void configurarCamposTabela();

    protected abstract void configurarTitulo();

    protected abstract void configurarCampoPesquisa();

    private void listarComEnter(ActionEvent e) {
        listarAction(new ActionEvent(e.getSource(), e.getID(), null));
    }

    protected abstract void listarAction(ActionEvent env);

    protected abstract void excluirAction(ActionEvent evt);

    protected static void tela(Class<? extends JFrame> tela) {

        if (!telaInstances.containsKey(tela)) {
            try {
                for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ex) {
                getLogger(TelaMenu.class.getName()).log(SEVERE, null, ex);
            }

            try {
                var novaInstancia = tela.getDeclaredConstructor().newInstance();
                telaInstances.put(tela, novaInstancia);
                invokeLater(() -> novaInstancia.setVisible(true));
            } catch (Exception ex) {
                getLogger(TelaMenu.class.getName()).log(SEVERE, null, ex);
            }

        } else {
            var telaExistente = telaInstances.get(tela);
            telaExistente.setExtendedState(NORMAL);
            telaExistente.setVisible(true);
            telaExistente.toFront();
        }
    }
}
