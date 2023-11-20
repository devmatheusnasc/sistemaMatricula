package controle.matricula.telas.impl;

import controle.matricula.dao.impl.DisciplinaDAOImpl;
import controle.matricula.dao.impl.PessoaDAOImpl;
import controle.matricula.model.Disciplina;
import controle.matricula.model.Pessoa;
import controle.matricula.telas.telabase.TelaPrincipalBase;
import controle.matricula.util.Operacao;
import controle.matricula.util.exceptions.ValidacaoException;
import controle.matricula.util.table.TableDisciplina;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static java.lang.Integer.parseInt;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class TelaPrincipalDisciplina extends TelaPrincipalBase<Disciplina, DisciplinaDAOImpl> {

    private final String[] colunas = new String[]{"Codigo", "Nome", "Carga Horária", "Professor", "Limite Alunos"};

    public TelaPrincipalDisciplina() {
        super();
        configurarTitulo();
    }

    @Override
    protected void listarAction(ActionEvent evt) {
            var disciplinaDAO = new DisciplinaDAOImpl();
            listar(disciplinaDAO, colunas);
    }

    @Override
    protected TableModel criarTableModel(List<Disciplina> itens) {
        return new TableDisciplina(itens, colunas);
    }

    @Override
    protected void inserir(ActionEvent evt) {
        var telaSecundaria = new TelaSecundariaDisciplina();
        telaSecundaria.telaSecundariaDisciplina(null);
    }

    @Override
    protected void editar(ActionEvent evt) {
        var selectedRow = tabelaPrincipal.getSelectedRow();

        if (selectedRow >= 0) {
            var id = (int) tabelaPrincipal.getValueAt(selectedRow, 0);
            var disciplinaDAO = new DisciplinaDAOImpl();
            var disciplina = disciplinaDAO.findById(id);

            if (disciplina != null) {
                var tela = new TelaSecundariaDisciplina(disciplina);
                tela.telaSecundariaDisciplina(disciplina);
                listar(disciplinaDAO, colunas);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma disciplina para editar.", AVISO, JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    protected void excluirAction(ActionEvent evt) {
        var item = excluir();

        if (item != -1) {
            var disciplinaDAO = new DisciplinaDAOImpl();
            disciplinaDAO.delete(item);
            showMessageDialog(null, "Disciplina excluída com sucesso.");
            listar(disciplinaDAO, colunas);
        }
    }

    public boolean processarUsuario(int id, JTextField nomeDisciplina, JTextField cargaHoraria, JTextField professor,
                                    JTextField limiteAlunos, Operacao operacao) {

        var disciplinaDAO = new DisciplinaDAOImpl();

        var nomeDisciplinaText = nomeDisciplina.getText();
        var cargaHorariaText = cargaHoraria.getText();
        var professorText = professor.getText();
        var limiteAlunosText = limiteAlunos.getText();
        if (validarCampos(nomeDisciplina, cargaHoraria, professor, limiteAlunos)) {

            var professorValido = validarProfessor(professorText);

            var pessoa = setDisciplina(nomeDisciplinaText, cargaHorariaText, professorValido, limiteAlunosText);

            if (operacao == Operacao.INSERIR) {
                disciplinaDAO.insert(pessoa);
                return true;
            }

            if (operacao == Operacao.ATUALIZAR) {
                disciplinaDAO.update(id, pessoa);
                return true;
            }
        }
        return false;
    }

    private Disciplina setDisciplina(String nomeDisciplina, String cargaHoraria, Pessoa professor, String limiteAlunos) {
        var disciplina = new Disciplina();

        disciplina.setNomeDisciplina(nomeDisciplina);
        disciplina.setCargaHoraria(parseInt(cargaHoraria));
        disciplina.setProfessor(professor);
        disciplina.setLimiteAlunos(parseInt(limiteAlunos));

        return disciplina;
    }

    private Pessoa validarProfessor(String professor) {
        var pessoaDAO = new PessoaDAOImpl();
        var pessoa = pessoaDAO.findByNome(professor);

        if (pessoa != null && pessoa.getTipo().equalsIgnoreCase("Professor")) {
            return pessoa;
        } else {
            showMessageDialog(null, "Professor não encontrado.", "Erro", ERROR_MESSAGE);
            throw new ValidacaoException("Professor não encontrado.");
        }
    }

    private boolean validarCampos(JTextField nomeDisciplina, JTextField cargaHoraria, JTextField professor, JTextField limiteAlunos) {
        var camposValidos = true;

        camposValidos &= validarCampoObrigatorio(nomeDisciplina);
        camposValidos &= validarCampoObrigatorio(cargaHoraria);
        camposValidos &= validarCampoObrigatorio(professor);
        camposValidos &= validarCampoObrigatorio(limiteAlunos);

        return camposValidos;
    }

    private boolean validarCampoObrigatorio(JTextField campo) {
        var valido = !campo.getText().isEmpty();
        if (!valido) {
            campo.setBorder(createLineBorder(Color.RED));
        } else {
            campo.setBorder(null);
        }
        return valido;
    }

    @Override
    protected void configurarCamposTabela() {
        tabelaPrincipal.setModel(new DefaultTableModel(colunas, 0));
    }

    @Override
    protected void configurarTitulo() {
        labelTitulo.setText("Disciplina");
    }

    @Override
    protected void configurarCampoPesquisa() {
        labelPesquisar.setText("Nome da Disciplina:");
    }

    public static void telaDisciplina() {
        tela(TelaPrincipalDisciplina.class);
    }
}
