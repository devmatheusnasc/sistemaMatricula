package controle.matricula.util.table;

import controle.matricula.model.Disciplina;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableDisciplina extends AbstractTableModel {

    private final List<Disciplina> dados;
    private final String[] colunas;

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
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var data = dados.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> data.getCodigo();
            case 1 -> data.getNomeDisciplina();
            case 2 -> data.getCargaHoraria();
            case 3 -> data.getProfessor().getNomePessoa();
            case 4 -> data.getLimiteAlunos();
            default -> null;
        };
    }
}