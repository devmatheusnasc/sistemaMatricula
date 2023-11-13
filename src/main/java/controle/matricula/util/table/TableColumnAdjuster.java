package controle.matricula.util.table;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TableColumnAdjuster {

    private final JTable table;

    public TableColumnAdjuster(JTable table) {
        this.table = table;
    }

    public void adjustColumns() {
        for (int column = 0; column < table.getColumnCount(); column++) {
            adjustColumn(column);
        }
    }

    private void adjustColumn(int column) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);

        int preferredWidth = tableColumn.getMinWidth();
        int maxWidth = tableColumn.getMaxWidth();

        for (int row = 0; row < table.getRowCount(); row++) {
            preferredWidth = Math.max(preferredWidth, getCellWidth(row, column));
        }

        tableColumn.setPreferredWidth(Math.min(preferredWidth, maxWidth));
    }

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
