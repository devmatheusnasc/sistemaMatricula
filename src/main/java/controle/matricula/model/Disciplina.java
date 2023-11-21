package controle.matricula.model;

/**
 * Representa uma disciplina em um sistema de controle de matrículas.
 */
public class Disciplina {

    private int codigo;
    private String nomeDisciplina;
    private int cargaHoraria;
    private Pessoa professor;
    private int limiteAlunos;

    /**
     * Construtor padrão da classe Disciplina.
     */
    public Disciplina() {

    }

    /**
     * Construtor parametrizado da classe Disciplina.
     *
     * @param codigo          O código da disciplina.
     * @param nomeDisciplina  O nome da disciplina.
     * @param cargaHoraria    A carga horária da disciplina.
     * @param professor       O professor responsável pela disciplina.
     * @param limiteAlunos    O limite de alunos para a disciplina.
     */
    public Disciplina(int codigo, String nomeDisciplina, int cargaHoraria, Pessoa professor, int limiteAlunos) {
        this.codigo = codigo;
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.limiteAlunos = limiteAlunos;
    }

    /**
     * Obtém o código da disciplina.
     *
     * @return O código da disciplina.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Define o código da disciplina.
     *
     * @param codigo O código a ser definido.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtém o nome da disciplina.
     *
     * @return O nome da disciplina.
     */
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    /**
     * Define o nome da disciplina.
     *
     * @param nomeDisciplina O nome a ser definido.
     */
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    /**
     * Obtém a carga horária da disciplina.
     *
     * @return A carga horária da disciplina.
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * Define a carga horária da disciplina.
     *
     * @param cargaHoraria A carga horária a ser definida.
     */
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * Obtém o professor responsável pela disciplina.
     *
     * @return O professor responsável pela disciplina.
     */
    public Pessoa getProfessor() {
        return professor;
    }

    /**
     * Define o professor responsável pela disciplina.
     *
     * @param professor O professor a ser definido.
     */
    public void setProfessor(Pessoa professor) {
        this.professor = professor;
    }

    /**
     * Obtém o limite de alunos para a disciplina.
     *
     * @return O limite de alunos para a disciplina.
     */
    public int getLimiteAlunos() {
        return limiteAlunos;
    }

    /**
     * Define o limite de alunos para a disciplina.
     *
     * @param limiteAlunos O limite de alunos a ser definido.
     */
    public void setLimiteAlunos(int limiteAlunos) {
        this.limiteAlunos = limiteAlunos;
    }

    /**
     * Sobrescrita do método toString para representar a disciplina pelo seu nome.
     *
     * @return O nome da disciplina.
     */
    @Override
    public String toString() {
        return nomeDisciplina;
    }
}
