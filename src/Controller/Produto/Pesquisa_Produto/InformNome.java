package Controller.Produto.Pesquisa_Produto;

import java.util.Scanner;

public class InformNome {
    Scanner input = new Scanner(System.in);
    private String nome;

    public InformNome(){
        System.out.print("Nome do produto: ");
        this.nome = input.next().toLowerCase();
    }

    public String getNome() {
        return nome;
    }
}
