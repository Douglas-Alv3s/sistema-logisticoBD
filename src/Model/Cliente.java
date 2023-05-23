package Model;

import java.util.ArrayList;

public class Cliente{
    // Atributos
    private int id_cliente;
    private String nome;
    private float dinheiro;
    private float gasto;
    private ArrayList<Compra> comprasRelacao;
    private Funcionario funcionario;


    // Construtor Default
    public Cliente(){ }


    // Construtor com sobrecarga
    public Cliente(int id_cliente, String nome, float dinheiro) {
        this.nome = nome;
        this.dinheiro = dinheiro;
        this.gasto = 0;
        this.comprasRelacao = new ArrayList<>();
    }

    // Metodos de acesso
    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public ArrayList<Compra> getComprasRelacao() {
        return comprasRelacao;
    }

    public void setComprasRelacao(ArrayList<Compra> comprasRelacao) {
        this.comprasRelacao = comprasRelacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void adicionarCompra(Compra compra) {
        comprasRelacao.add(compra);
    }

    public void removerCompra(Compra compra) {
        comprasRelacao.remove(compra);
    }



    // Método de impressão do cliente
    public String imprimir(){
        return "nome cliente: "+ this.nome+"\n"+
                String.format("\nGasto: R$%.2f \n", this.gasto);
    }

}

