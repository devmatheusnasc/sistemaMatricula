package controle.matricula.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    List<T> findAll();

    T findById(int id);

    void insert(T t) throws SQLException;

    void update(int id, T t);

    boolean delete(int id);

    T findByNome(String nome);
}
