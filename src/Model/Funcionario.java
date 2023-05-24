package Model;

public class Funcionario{

    private int id_funcionario;
    private String login;
    private String senha;
    
    
    public Funcionario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Funcionario(int id_funcionario, String login, String senha) {
        this.id_funcionario = id_funcionario;
        this.login = login;
        this.senha = senha;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    

}
