package Model;

import java.util.ArrayList;

public class Cliente{
    // Atributos
    private String login;
    private float dinheiro;
    private float gasto;
    private ArrayList<Compra> compraTabel;
    private Funcionario funcionario;

    // Construtor Default
    public Cliente(){ }

    // Construtor com sobrecarga
    public Cliente(String login, float dinheiro) {
        this.login = login;
        this.dinheiro = dinheiro;
        this.gasto = 0;
    }

    // Metodos de acesso
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public float getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(float dinheiro) {
        this.dinheiro = dinheiro;
    }

    public float getGasto() {
        return gasto;
    }

    public void setGasto(float gasto) {
        this.gasto = gasto;
    }

    public ArrayList<Compra> getCompraTabel() {
        return compraTabel;
    }

    public void setCompraTabel(ArrayList<Compra> compraTabel) {
        this.compraTabel = compraTabel;
    }

    // Método de impressão do cliente
    public String imprimir(){
        return "login cliente: "+ this.login+"\n"+
                String.format("\nGasto: R$%.2f \n", this.gasto);
    }

}

