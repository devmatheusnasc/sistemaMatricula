package controle.matricula.util.table;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * Uma classe utilitária para ajustar automaticamente a largura das colunas em uma JTable com base no conteúdo das células.
 */
public class TableColumnAdjuster {

    private final JTable table;

    /**
     * Construtor que recebe a JTable a ser ajustada.
     *
     * @param table A JTable a ser ajustada.
     */
    public TableColumnAdjuster(JTable table) {
        this.table = table;
    }

    /**
     * Ajusta automaticamente a largura de todas as colunas na JTable.
     */
    public void adjustColumns() {
        for (int column = 0; column < table.getColumnCount(); column++) {
            adjustColumn(column);
        }
    }

    /**
     * Ajusta automaticamente a largura de uma coluna específica na JTable.
     *
     * @param column O índice da coluna a ser ajustada.
     */
    private void adjustColumn(int column) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);

        int preferredWidth = tableColumn.getMinWidth();
        int maxWidth = tableColumn.getMaxWidth();

        for (int row = 0; row < table.getRowCount(); row++) {
            preferredWidth = Math.max(preferredWidth, getCellWidth(row, column));
        }

        tableColumn.setPreferredWidth(Math.min(preferredWidth, maxWidth));
    }

    /**
     * Obtém a largura da célula na posição especificada.
     *
     * @param row    O índice da linha.
     * @param column O índice da coluna.
     * @return A largura da célula.
     */
    private int getCellWidth(int row, int column) {
        int width = 0;
        FontMetrics metrics = table.getFontMetrics(table.getFont());

        Object value = table.getValueAt(row, column);
        if (value != null) {
            width = metrics.stringWidth(value.toString()) + 2 * table.getIntercellSpacing().width;
        }

        return width;
    }
}

