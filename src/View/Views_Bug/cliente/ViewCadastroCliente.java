package View.Views_Bug.cliente;

import java.util.Scanner;


public class ViewCadastroCliente{
    // Atributos
    private String nome;
     
    private float dinheiro;
    

    // Referencia para classe Cliente
    private Cliente objCliente;

    public void cadastroCliente(){
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("\n============ Entrando cliente ============\n");
        System.out.print("Nome do cliente: ");
        this.nome = entrada.nextLine();
        

        
        System.out.print("Dinheiro do cliente: R$");
        this.dinheiro = entrada.nextFloat();
        
        // Criar um objeto na classe
        objCliente = new Cliente(this.nome, this.dinheiro);
        

        //Cadastrar no registro do mercadinho
        CadastroCliente.getInstance().adicionarCliente(objCliente);
        
        
        
    }
}
