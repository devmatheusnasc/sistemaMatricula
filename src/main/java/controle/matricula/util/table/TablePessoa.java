package controle.matricula.util.table;

import controle.matricula.model.Pessoa;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Um modelo de tabela para a entidade Pessoa.
 */
public class TablePessoa extends AbstractTableModel {

    private final List<Pessoa> dados;
    private final String[] colunas;

    /**
     * Construtor que recebe dados e colunas para a tabela.
     *
     * @param dados   Lista de objetos Pessoa.
     * @param colunas Nomes das colunas da tabela.
     */
    public TablePessoa(List<Pessoa> dados, String[] colunas) {
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
        return 8;
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
                return data.getIdPessoa();
            case 1:
                return data.getNomePessoa();
            case 2:
                return data.getEndereco();
            case 3:
                return data.getUf();
            case 4:
                return data.getTelefone();
            case 5:
                return data.getCpf();
            case 6:
                return data.getEmail();
            case 7:
                return data.getTipo();
            default:
                return null;
        }
    }
}
