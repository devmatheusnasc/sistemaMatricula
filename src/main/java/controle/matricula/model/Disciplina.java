package controle.matricula.model;

public class Disciplina {

    private int codigo;
    private String nomeDisciplina;
    private int cargaHoraria;
    private Pessoa professor;
    private int limiteAlunos;

    public Disciplina() {

    }

    public Disciplina(int codigo, String nomeDisciplina, int cargaHoraria, Pessoa professor, int limiteAlunos) {
        this.codigo = codigo;
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.limiteAlunos = limiteAlunos;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Pessoa getProfessor() {
        return professor;
    }

    public void setProfessor(Pessoa professor) {
        this.professor = professor;
    }

    public int getLimiteAlunos() {
        return limiteAlunos;
    }

    public void setLimiteAlunos(int limiteAlunos) {
        this.limiteAlunos = limiteAlunos;
    }
}
