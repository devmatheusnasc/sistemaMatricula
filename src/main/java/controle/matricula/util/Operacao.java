package controle.matricula.util;

/**
 * Enumeração que representa operações de CRUD (Create, Read, Update, Delete).
 * Utilizada para identificar a operação a ser realizada em um contexto específico.
 */
public enum Operacao {

    /**
     * Operação de inserção (CREATE).
     */
    INSERIR,

    /**
     * Operação de atualização (UPDATE).
     */
    ATUALIZAR;

    /**
     * Construtor padrão da enumeração.
     */
    Operacao() {
    }
}
