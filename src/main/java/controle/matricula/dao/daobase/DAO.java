package controle.matricula.dao.daobase;

import java.util.List;

public interface DAO<T> {

    List<T> findAll();

    T findById(int id);

    boolean insert(T t);

    boolean update(T t);

    boolean delete(int id);
}
