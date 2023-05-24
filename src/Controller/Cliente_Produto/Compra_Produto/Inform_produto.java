package Controller.Cliente_Produto.Compra_Produto;

import java.util.Scanner;

public class Inform_produto {
    Scanner input = new Scanner(System.in);
    protected String nome;
    protected int quantidade;
    
    public Inform_produto(){
        System.out.print("Nome do produto: ");
        this.nome = input.nextLine().toLowerCase();
        System.out.print("Deseja comprar quantos desse produto? ");
        this.quantidade = input.nextInt();
    }

    public String getNome() {
        return nome;
    }
    public int getQuantidade(){
        return quantidade;
    }
}
