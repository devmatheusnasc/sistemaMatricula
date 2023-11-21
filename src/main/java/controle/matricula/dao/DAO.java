package controle.matricula.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define operações básicas de um DAO (Data Access Object).
 *
 * @param <T> Tipo da entidade que será manipulada pelo DAO.
 */
public interface DAO<T> {

    /**
     * Retorna uma lista de todas as entidades do tipo T.
     *
     * @return Lista de entidades.
     */
    List<T> findAll();

    /**
     * Retorna uma entidade do tipo T com base no ID fornecido.
     *
     * @param id ID da entidade a ser recuperada.
     * @return A entidade correspondente ao ID ou null se não encontrada.
     */
    T findById(int id);

    /**
     * Insere uma nova entidade do tipo T no banco de dados.
     *
     * @param t A entidade a ser inserida.
     * @throws SQLException Exceção lançada em caso de erro durante a inserção.
     */
    void insert(T t) throws SQLException;

    /**
     * Atualiza uma entidade do tipo T com base no ID fornecido.
     *
     * @param id ID da entidade a ser atualizada.
     * @param t  A entidade com as informações atualizadas.
     */
    void update(int id, T t);

    /**
     * Exclui uma entidade do tipo T com base no ID fornecido.
     *
     * @param id ID da entidade a ser excluída.
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    boolean delete(int id);

    /**
     * Retorna uma entidade do tipo T com base no nome fornecido.
     *
     * @param nome Nome da entidade a ser recuperada.
     * @return A entidade correspondente ao nome ou null se não encontrada.
     */
    T findByNome(String nome);
}
