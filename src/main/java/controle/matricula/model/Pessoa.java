package controle.matricula.model;

/**
 * Representa uma pessoa no sistema de controle de matrículas.
 */
public class Pessoa {

    private int idPessoa;
    private String nomePessoa;
    private String endereco;
    private String uf;
    private String telefone;
    private String cpf;
    private String email;
    private String tipo;

    /**
     * Construtor padrão da classe Pessoa.
     */
    public Pessoa() {

    }

    /**
     * Construtor parametrizado da classe Pessoa.
     *
     * @param idPessoa    O identificador da pessoa.
     * @param nomePessoa  O nome da pessoa.
     * @param endereco    O endereço da pessoa.
     * @param uf          A Unidade Federativa (UF) da pessoa.
     * @param telefone    O telefone da pessoa.
     * @param cpf         O CPF da pessoa.
     * @param email       O email da pessoa.
     * @param tipo        O tipo de pessoa.
     */
    public Pessoa(int idPessoa, String nomePessoa, String endereco, String uf, String telefone, String cpf,
                  String email, String tipo) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        this.endereco = endereco;
        this.uf = uf;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.tipo = tipo;
    }

    /**
     * Obtém o identificador da pessoa.
     *
     * @return O identificador da pessoa.
     */
    public int getIdPessoa() {
        return idPessoa;
    }

    /**
     * Define o identificador da pessoa.
     *
     * @param idPessoa O identificador a ser definido.
     */
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * Obtém o nome da pessoa.
     *
     * @return O nome da pessoa.
     */
    public String getNomePessoa() {
        return nomePessoa;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nomePessoa O nome a ser definido.
     */
    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    /**
     * Obtém o endereço da pessoa.
     *
     * @return O endereço da pessoa.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço da pessoa.
     *
     * @param endereco O endereço a ser definido.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Obtém a Unidade Federativa (UF) da pessoa.
     *
     * @return A UF da pessoa.
     */
    public String getUf() {
        return uf;
    }

    /**
     * Define a Unidade Federativa (UF) da pessoa.
     *
     * @param uf A UF a ser definida.
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * Obtém o telefone da pessoa.
     *
     * @return O telefone da pessoa.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone da pessoa.
     *
     * @param telefone O telefone a ser definido.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém o CPF da pessoa.
     *
     * @return O CPF da pessoa.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF da pessoa.
     *
     * @param cpf O CPF a ser definido.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o email da pessoa.
     *
     * @return O email da pessoa.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email da pessoa.
     *
     * @param email O email a ser definido.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o tipo de pessoa.
     *
     * @return O tipo de pessoa.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo de pessoa.
     *
     * @param tipo O tipo a ser definido.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Sobrescrita do método toString para retornar o nome da pessoa.
     *
     * @return O nome da pessoa.
     */
    @Override
    public String toString() {
        return nomePessoa;
    }
}
