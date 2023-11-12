package controle.matricula.model;

import java.util.Date;

public class Matricula {

    private int idMat;
    private Disciplina disciplina;
    private Date dataMatricula;
    private double valorPago;
    private Pessoa aluno;
    private String periodo;

    public Matricula() {

    }

    public Matricula(int idMat, Disciplina disciplina, Date dataMatricula, double valorPago, Pessoa aluno, String periodo) {
        this.idMat = idMat;
        this.disciplina = disciplina;
        this.dataMatricula = dataMatricula;
        this.valorPago = valorPago;
        this.aluno = aluno;
        this.periodo = periodo;
    }

    public int getIdMat() {
        return idMat;
    }

    public void setIdMat(int idMat) {
        this.idMat = idMat;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Pessoa getAluno() {
        return aluno;
    }

    public void setAluno(Pessoa aluno) {
        this.aluno = aluno;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
