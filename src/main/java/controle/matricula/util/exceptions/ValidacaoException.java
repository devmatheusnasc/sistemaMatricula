package controle.matricula.util.exceptions;

/**
 * Exceção lançada durante validações no sistema.
 */
public class ValidacaoException extends RuntimeException {

    /**
     * Construtor da exceção com uma mensagem específica.
     *
     * @param message Mensagem detalhando a validação que falhou.
     */
    public ValidacaoException(String message) {
        super(message);
    }
}
