package controle.matricula.bean;

import controle.matricula.dao.DAO;

import java.util.List;

@FunctionalInterface
public interface BeanOperation<T> {

    List<T> listarTodos(DAO<T> dao);
}
