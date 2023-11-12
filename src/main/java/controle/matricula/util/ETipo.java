package controle.matricula.util;

public enum ETipo {

    PROFESSOR("Professor"),
    ALUNO("Aluno");

    private final String descricao;

    public String getDescricao() {
        return descricao;
    }

    ETipo(String descricao) {
        this.descricao = descricao;
    }
}
