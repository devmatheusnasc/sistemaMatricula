package controle.matricula.dao.daobase;


import controle.matricula.model.Pessoa;

import java.util.List;

public interface PessoaDAO {

    Pessoa findByNome(String nome);

    List<String> obterTipoPessoa();
}
