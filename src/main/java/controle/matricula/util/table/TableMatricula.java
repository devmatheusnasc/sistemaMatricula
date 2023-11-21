package controle.matricula.util.table;

import controle.matricula.model.Matricula;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Um modelo de tabela para a entidade Matricula.
 */
public class TableMatricula extends AbstractTableModel {

    private final List<Matricula> dados;
    private final String[] colunas;

    /**
     * Construtor que recebe dados e colunas para a tabela.
     *
     * @param dados   Lista de objetos Matricula.
     * @param colunas Nomes das colunas da tabela.
     */
    public TableMatricula(List<Matricula> dados, String[] colunas) {
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
        return 6;
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
                return data.getIdMat();
            case 1:
                return data.getDisciplina().getNomeDisciplina();
            case 2:
                return data.getDataMatricula();
            case 3:
                return data.getValorPago();
            case 4:
                return data.getAluno().getNomePessoa();
            case 5:
                return data.getPeriodo();
            default:
                return null;
        }
    }
}
