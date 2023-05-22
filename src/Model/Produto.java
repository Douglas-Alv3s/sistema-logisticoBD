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

    @Override
    public String toString() {
        return "Produto: " + nome + String.format(", valor: R$%.2f", valor) + ", quantidade: " + quantidade;
    }
}
