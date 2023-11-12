package controle.matricula.model;

import controle.matricula.util.ETipo;

public class Pessoa {

    private int idPessoa;
    private String nomePessoa;
    private String endereco;
    private String uf;
    private String telefone;
    private String cpf;
    private String email;
    private ETipo tipo;

    public Pessoa() {

    }

    public Pessoa(int idPessoa, String nomePessoa, String endereco, String uf, String telefone, String cpf,
                  String email, ETipo tipo) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        this.endereco = endereco;
        this.uf = uf;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.tipo = tipo;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ETipo getTipo() {
        return tipo;
    }

    public void setTipo(ETipo tipo) {
        this.tipo = tipo;
    }
}
