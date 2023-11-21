package controle.matricula.util.table;

import controle.matricula.model.Usuario;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Um modelo de tabela para a entidade Usuario.
 */
public class TableUsuario extends AbstractTableModel {

    private final List<Usuario> dados;
    private final String[] colunas;

    /**
     * Construtor que recebe dados e colunas para a tabela.
     *
     * @param dados   Lista de objetos Usuario.
     * @param colunas Nomes das colunas da tabela.
     */
    public TableUsuario(List<Usuario> dados, String[] colunas) {
        this.dados = dados;
        this.colunas = colunas;
    }

    /**
     * Retorna o número de linhas na tabela.
     *
     * @return Número de linhas na tabela.
     */
    @Override
    public int getRowCount() {
        return dados.size();
    }

    /**
     * Retorna o número de colunas na tabela.
     *
     * @return Número de colunas na tabela.
     */
    @Override
    public int getColumnCount() {
        return 4;
    }

    /**
     * Retorna o valor na posição especificada pelos índices de linha e coluna.
     *
     * @param rowIndex    Índice da linha.
     * @param columnIndex Índice da coluna.
     * @return Valor na posição especificada.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var data = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return data.getId();
            case 1:
                return data.getNome();
            case 2:
                return data.getEmail();
            case 3:
                return data.getCargo();
            default:
                return null;
        }
    }
}
