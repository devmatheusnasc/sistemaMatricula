package controle.matricula.dao.daobase;


import controle.matricula.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    Usuario findByLoginAndSenha(String  login, String senha);
}
