package Model;

public class Produto {
    private int id;
    private String nome;
    private float valor;
    private int quantidade;
    
    public Produto(){}

    public Produto(String nome, float valor, int quantidade){
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String trazerNome() {
        return nome;
    }


    public float trazerValor() {
        return valor;
    }

    public int trazerQuantidade(){
        return quantidade;
    }
    public void alterarQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Produto: " + nome + String.format(", valor: R$%.2f", valor) + ", quantidade: " + quantidade;
    }
}
