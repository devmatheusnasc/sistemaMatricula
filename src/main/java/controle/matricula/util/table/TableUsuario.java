package controle.matricula.util.table;

import controle.matricula.model.Usuario;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableUsuario extends AbstractTableModel {

    private final List<Usuario> dados;
    private final String[] colunas;

    public TableUsuario(List<Usuario> dados, String[] colunas) {
        this.dados = dados;
        this.colunas = colunas;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var data = dados.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> data.getId();
            case 1 -> data.getNome();
            case 2 -> data.getEmail();
            case 3 -> data.getCargo();
            default -> null;
        };
    }
}