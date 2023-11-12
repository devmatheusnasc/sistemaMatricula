package controle.matricula.telas.impl;

import controle.matricula.dao.daobase.DAO;
import controle.matricula.dao.impl.DisciplinaDAOImpl;
import controle.matricula.dao.impl.MatriculaDAOImpl;
import controle.matricula.dao.impl.PessoaDAOImpl;
import controle.matricula.model.Matricula;
import controle.matricula.telas.telabase.TelaPrincipalBase;
import controle.matricula.util.TableMatricula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.util.stream.IntStream.range;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.JOptionPane.*;

public class TelaPrincipalMatricula extends TelaPrincipalBase<Matricula> {

    public TelaPrincipalMatricula() {
        super();
        configurarTitulo();
    }

    @Override
    protected void listar(ActionEvent evt) {
        try {
            var matriculaDAO = new MatriculaDAOImpl();
            var input = campoPesquisar.getText();
            List<Matricula> matriculas = new ArrayList<>();
            var colunas = new String[]{"ID", "Disciplina", "Data da Matricula", "Valor Pago", "Aluno", "Periodo"};

            if (!input.isEmpty()) {
                var matricula = matriculaDAO.findById(Integer.parseInt(input));
                if (matricula != null) {
                    matriculas.add(matricula);
                } else {
                    showMessageDialog(null, "Matrícula não encontrada.",
                            AVISO, WARNING_MESSAGE);
                }
            } else {
                matriculas = listarTodos(matriculaDAO);
            }

            var newTableModel = new TableMatricula(matriculas, colunas);
            tabelaPrincipal.setModel(newTableModel);
            range(0, colunas.length)
                    .forEach(i -> tabelaPrincipal.getColumnModel().getColumn(i).setHeaderValue(colunas[i]));

        } catch (Exception e) {
            e.printStackTrace();
            showMessageDialog(null, "Erro ao Pesquisar.",
                    "Erro", ERROR_MESSAGE);
        }
    }

    @Override
    protected List<Matricula> listarTodos(DAO<Matricula> matriculaDAO) {
        return matriculaDAO.findAll();
    }

    @Override
    protected void inserir(ActionEvent evt) {
        var frame = new JFrame("Inserir Matrícula");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        var disciplinaField = new JTextField(20);
        var valorPagoField = new JTextField(10);
        var alunoField = new JTextField(20);
        var periodoField = new JTextField(10);

        var salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            if (salvar(disciplinaField, valorPagoField, alunoField, periodoField, frame)) {
                listar(evt);
            }
        });

        var panel = criarPainel(disciplinaField, valorPagoField, alunoField, periodoField, salvarButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void editar(ActionEvent evt) {
        var selectedRow = tabelaPrincipal.getSelectedRow();

        if (selectedRow >= 0) {
            var idMatricula = (int) tabelaPrincipal.getValueAt(selectedRow, 0);
            var matriculaDAO = new MatriculaDAOImpl();

            var matriculaExistente = matriculaDAO.findById(idMatricula);

            if (matriculaExistente != null) {
                var frame = new JFrame("Editar Matrícula");
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                var disciplinaField = new JTextField(20);
                var valorPagoField = new JTextField(10);
                var alunoField = new JTextField(20);
                var periodoField = new JTextField(10);

                var salvarButton = new JButton("Atualizar");
                salvarButton.addActionListener(e -> {
                    var disciplina = disciplinaField.getText();
                    var valorPagoText = valorPagoField.getText();
                    var aluno = alunoField.getText();
                    var periodo = periodoField.getText();

                    if (validarCampos(disciplinaField, valorPagoField, alunoField, periodoField)) {
                        var matriculaAtualizada = criar(disciplina, valorPagoText, aluno, periodo);

                        matriculaAtualizada.setIdMat(idMatricula);

                        var matricula = new MatriculaDAOImpl();
                        var atualizado = matricula.update(matriculaAtualizada);

                        if (atualizado) {
                            JOptionPane.showMessageDialog(null, "Matrícula atualizada com sucesso.");
                            frame.dispose();
                            listar(evt);
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao atualizar a matrícula.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos corretamente.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    }
                });
                frame.add(criarPainel(disciplinaField, valorPagoField, alunoField, periodoField, salvarButton));
                frame.pack();
                frame.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma matrícula para editar.", AVISO, JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    protected void excluir(ActionEvent evt) {

        var selectedRow = tabelaPrincipal.getSelectedRow();
        if (selectedRow < 0) {
            showMessageDialog(null, "Selecione uma matrícula para excluir.",
                    AVISO, WARNING_MESSAGE);
            return;
        }

        var confirmar = showConfirmDialog(null,
                "Tem certeza de que deseja excluir esta matrícula?", "Confirmação", YES_NO_OPTION);
        if (confirmar != YES_OPTION) {
            return;
        }

        var idMatricula = (int) tabelaPrincipal.getValueAt(selectedRow, 0);
        var matriculaDAO = new MatriculaDAOImpl();
        var delete = matriculaDAO.delete(idMatricula);

        if (delete) {
            showMessageDialog(null, "Matrícula excluída com sucesso.");
            listar(evt);
        } else {
            showMessageDialog(null, "Erro ao excluir a matrícula.", "Erro", ERROR_MESSAGE);
        }
    }

    @Override
    protected JPanel criarPainel(JTextField disciplinaField, JTextField valorPagoField, JTextField alunoField, JTextField periodoField, JButton salvarButton) {
        var panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Disciplina:*"));
        panel.add(disciplinaField);
        panel.add(new JLabel("Valor Pago:*"));
        panel.add(valorPagoField);
        panel.add(new JLabel("Aluno:*"));
        panel.add(alunoField);
        panel.add(new JLabel("Período:*"));
        panel.add(periodoField);
        panel.add(salvarButton);
        return panel;
    }

    @Override
    protected boolean salvar(JTextField disciplinaField, JTextField valorPagoField, JTextField alunoField, JTextField periodoField, JFrame frame) {
        var disciplina = disciplinaField.getText();
        var valorPagoText = valorPagoField.getText();
        var aluno = alunoField.getText();
        var periodo = periodoField.getText();

        var camposValidos = validarCampos(disciplinaField, valorPagoField, alunoField, periodoField);

        if (camposValidos) {
            var matricula = criar(disciplina, valorPagoText, aluno, periodo);

            showMessageDialog(null, "Disciplina excedeu o limite.", "Erro", ERROR_MESSAGE);
            var matriculaDAO = new MatriculaDAOImpl();
            var cadastrado = matriculaDAO.insert(matricula);

            if (cadastrado) {
                showMessageDialog(null, "Matrícula inserida com sucesso.");
                frame.dispose();
            } else {
                showMessageDialog(null, "Erro ao inserir a matrícula.", "Erro", ERROR_MESSAGE);
            }
            return true;
        } else {
            showMessageDialog(null, "Por favor, preencha todos os campos corretamente.", "Erro de Validação", ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    protected boolean validarCampos(JTextField disciplinaField, JTextField valorPagoField, JTextField alunoField, JTextField periodoField) {
        var camposValidos = true;

        camposValidos &= validarCampoObrigatorio(disciplinaField);
        camposValidos &= validarCampoObrigatorio(alunoField);
        camposValidos &= validarCampoObrigatorio(periodoField);
        camposValidos &= validarCampoDouble(valorPagoField);

        return camposValidos;
    }

    @Override
    protected boolean validarCampoObrigatorio(JTextField campo) {
        var valido = !campo.getText().isEmpty();
        if (!valido) {
            campo.setBorder(createLineBorder(Color.RED));
        } else {
            campo.setBorder(null);
        }
        return valido;
    }

    @Override
    protected boolean validarCampoDouble(JTextField campo) {
        try {
            var valor = parseDouble(campo.getText());
            campo.setBorder(null);
            return true;
        } catch (NumberFormatException ex) {
            campo.setBorder(createLineBorder(Color.RED));
            return false;
        }
    }

    @Override
    protected Matricula criar(String disciplina, String valorPagoText, String aluno, String periodo) {
        var findByDisciplina = new DisciplinaDAOImpl();
        var disciplinabd = findByDisciplina.findByNome(disciplina);
        if (disciplinabd == null) {
            showMessageDialog(null, "Disciplina não encontrada.", "Erro", ERROR_MESSAGE);
        }

        var findByAluno = new PessoaDAOImpl();
        var alunobd = findByAluno.findByNome(aluno);
        if (alunobd == null) {
            showMessageDialog(null, "Aluno não encontrado.", "Erro", ERROR_MESSAGE);
        }

        var matricula = new Matricula();
        matricula.setDisciplina(disciplinabd);
        matricula.setValorPago(parseDouble(valorPagoText));
        matricula.setAluno(alunobd);
        matricula.setPeriodo(periodo);
        matricula.setDataMatricula(new Date());

        return matricula;
    }

    @Override
    protected void configurarCamposTabela() {
        var colunas = new String[]{"ID", "Disciplina", "Data da Matricula", "Valor Pago", "Aluno", "Periodo"};
        tabelaPrincipal.setModel(new DefaultTableModel(colunas, 0));
    }

    @Override
    protected void configurarTitulo() {
        labelTitulo.setText("Matricula");
    }

    @Override
    protected void configurarCampoPesquisa() {
        labelPesquisar.setText("Número da Matrícula:");
    }

    public static void telaMatricula() {
        tela(TelaPrincipalMatricula.class);
    }

}
