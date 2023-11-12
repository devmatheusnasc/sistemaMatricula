package controle.matricula.dao.daobase;

import controle.matricula.model.Disciplina;

public interface DisciplinaDAO {

    Disciplina findByNome(String nome);
}
