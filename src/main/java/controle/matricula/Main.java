package controle.matricula;

import controle.matricula.telas.impl.TelaLogin;

/**
 * Classe principal que inicia a aplicação do sistema de controle de matrícula.
 * Chama o método `telaLogin()` da classe `TelaLogin` para iniciar a interface de login.
 */
public class Main {

    /**
     * Método principal que inicia a aplicação.
     *
     * @param args Argumentos de linha de comando (não utilizados neste momento).
     */
    public static void main(String[] args) {
        TelaLogin.telaLogin();
    }
}
