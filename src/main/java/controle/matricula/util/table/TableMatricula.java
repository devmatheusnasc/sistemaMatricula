package controle.matricula.util.table;

import controle.matricula.model.Matricula;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class TableMatricula extends AbstractTableModel {

    private final List<Matricula> dados;
    private final String[] colunas;

    public TableMatricula(List<Matricula> dados, String[] colunas) {
        this.dados = dados;
        this.colunas = colunas;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var data = dados.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> data.getIdMat();
            case 1 -> data.getDisciplina().getNomeDisciplina();
            case 2 -> data.getDataMatricula();
            case 3 -> data.getValorPago();
            case 4 -> data.getAluno().getNomePessoa();
            case 5 -> data.getPeriodo();
            default -> null;
        };
    }
}