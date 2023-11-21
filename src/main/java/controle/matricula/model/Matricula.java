package controle.matricula.model;

import java.util.Date;

/**
 * Representa uma matrícula em um sistema de controle de matrículas.
 */
public class Matricula {

    private int idMat;
    private Disciplina disciplina;
    private Date dataMatricula;
    private double valorPago;
    private Pessoa aluno;
    private String periodo;

    /**
     * Construtor padrão da classe Matricula.
     */
    public Matricula() {

    }

    /**
     * Construtor parametrizado da classe Matricula.
     *
     * @param idMat          O identificador da matrícula.
     * @param disciplina     A disciplina associada à matrícula.
     * @param dataMatricula  A data da matrícula.
     * @param valorPago      O valor pago na matrícula.
     * @param aluno          O aluno associado à matrícula.
     * @param periodo        O período da matrícula.
     */
    public Matricula(int idMat, Disciplina disciplina, Date dataMatricula, double valorPago, Pessoa aluno, String periodo) {
        this.idMat = idMat;
        this.disciplina = disciplina;
        this.dataMatricula = dataMatricula;
        this.valorPago = valorPago;
        this.aluno = aluno;
        this.periodo = periodo;
    }

    /**
     * Obtém o identificador da matrícula.
     *
     * @return O identificador da matrícula.
     */
    public int getIdMat() {
        return idMat;
    }

    /**
     * Define o identificador da matrícula.
     *
     * @param idMat O identificador a ser definido.
     */
    public void setIdMat(int idMat) {
        this.idMat = idMat;
    }

    /**
     * Obtém a disciplina associada à matrícula.
     *
     * @return A disciplina associada à matrícula.
     */
    public Disciplina getDisciplina() {
        return disciplina;
    }

    /**
     * Define a disciplina associada à matrícula.
     *
     * @param disciplina A disciplina a ser definida.
     */
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * Obtém a data da matrícula.
     *
     * @return A data da matrícula.
     */
    public Date getDataMatricula() {
        return dataMatricula;
    }

    /**
     * Define a data da matrícula.
     *
     * @param dataMatricula A data a ser definida.
     */
    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    /**
     * Obtém o valor pago na matrícula.
     *
     * @return O valor pago na matrícula.
     */
    public double getValorPago() {
        return valorPago;
    }

    /**
     * Define o valor pago na matrícula.
     *
     * @param valorPago O valor pago a ser definido.
     */
    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    /**
     * Obtém o aluno associado à matrícula.
     *
     * @return O aluno associado à matrícula.
     */
    public Pessoa getAluno() {
        return aluno;
    }

    /**
     * Define o aluno associado à matrícula.
     *
     * @param aluno O aluno a ser definido.
     */
    public void setAluno(Pessoa aluno) {
        this.aluno = aluno;
    }

    /**
     * Obtém o período da matrícula.
     *
     * @return O período da matrícula.
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * Define o período da matrícula.
     *
     * @param periodo O período a ser definido.
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
