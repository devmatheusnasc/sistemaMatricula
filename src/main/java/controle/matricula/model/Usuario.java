package controle.matricula.model;

/**
 * Representa um usuário no sistema de controle de matrículas.
 */
public class Usuario {

    private int id;
    private String nome;
    private String cargo;
    private String login;
    private String senha;
    private String email;

    /**
     * Construtor padrão da classe Usuario.
     */
    public Usuario() {}

    /**
     * Construtor parametrizado da classe Usuario.
     *
     * @param id    O identificador do usuário.
     * @param nome  O nome do usuário.
     * @param cargo O cargo do usuário.
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @param email O email do usuário.
     */
    public Usuario(Integer id, String nome, String cargo, String login, String senha, String email) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.email = email;
    }

    /**
     * Obtém o nome do usuário.
     *
     * @return O nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do usuário.
     *
     * @param nome O nome a ser definido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o cargo do usuário.
     *
     * @return O cargo do usuário.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Define o cargo do usuário.
     *
     * @param cargo O cargo a ser definido.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Obtém o login do usuário.
     *
     * @return O login do usuário.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Define o login do usuário.
     *
     * @param login O login a ser definido.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Obtém a senha do usuário.
     *
     * @return A senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do usuário.
     *
     * @param senha A senha a ser definida.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Obtém o email do usuário.
     *
     * @return O email do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do usuário.
     *
     * @param email O email a ser definido.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o identificador do usuário.
     *
     * @return O identificador do usuário.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do usuário.
     *
     * @param id O identificador a ser definido.
     */
    public void setId(int id) {
        this.id = id;
    }
}
