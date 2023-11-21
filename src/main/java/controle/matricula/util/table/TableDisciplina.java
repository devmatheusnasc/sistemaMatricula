package controle.matricula.util.table;

import controle.matricula.model.Disciplina;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Um modelo de tabela para a entidade Disciplina.
 */
public class TableDisciplina extends AbstractTableModel {

    private final List<Disciplina> dados;
    private final String[] colunas;

    /**
     * Construtor que recebe dados e colunas para a tabela.
     *
     * @param dados   Lista de objetos Disciplina.
     * @param colunas Nomes das colunas da tabela.
     */
    public TableDisciplina(List<Disciplina> dados, String[] colunas) {
        this.dados = dados;
        this.colunas = colunas;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var disciplina = dados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return disciplina.getCodigo();
            case 1:
                return disciplina.getNomeDisciplina();
            case 2:
                return disciplina.getCargaHoraria();
            case 3:
                return disciplina.getProfessor().getNomePessoa();
            case 4:
                return disciplina.getLimiteAlunos();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
