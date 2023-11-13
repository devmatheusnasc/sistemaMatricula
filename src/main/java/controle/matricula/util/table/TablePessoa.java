package controle.matricula.util.table;

import controle.matricula.model.Pessoa;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TablePessoa extends AbstractTableModel {

    private final List<Pessoa> dados;
    private final String[] colunas;

    public TablePessoa(List<Pessoa> dados, String[] colunas) {
        this.dados = dados;
        this.colunas = colunas;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var data = dados.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> data.getIdPessoa();
            case 1 -> data.getNomePessoa();
            case 2 -> data.getEndereco();
            case 3 -> data.getUf();
            case 4 -> data.getTelefone();
            case 5 -> data.getCpf();
            case 6 -> data.getEmail();
            case 7 -> data.getTipo();
            default -> null;
        };
    }
}