package Controller.Pesquisa_Produto;

import java.util.Scanner;

public class InformNome {
    Scanner input = new Scanner(System.in);
    protected String nome;

    public InformNome(){
        System.out.print("Nome do produto: ");
        this.nome = input.nextLine().toLowerCase();
    }

    public String getNome() {
        return nome;
    }
}
