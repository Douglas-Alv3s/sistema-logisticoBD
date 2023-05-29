package Model;

import java.util.ArrayList;

public class Produto {
    private int id_produto;
    private String nome;
    private float valor;
    private int quantidade;
    private ArrayList<Compra> compraTabel;
    private Funcionario funcionario;
    

    public Produto(){}

    public Produto(String nome, float valor, int quantidade){
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Produto(int id_produto, String nome, float valor, int quantidade){
        this.id_produto = id_produto;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }
    
    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public ArrayList<Compra> getCompraTabel() {
        return compraTabel;
    }

    public void setCompraTabel(ArrayList<Compra> compraTabel) {
        this.compraTabel = compraTabel;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    
}
