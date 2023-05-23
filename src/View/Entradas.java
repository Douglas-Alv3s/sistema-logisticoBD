package View;

import java.util.Scanner;

import DAO.DAOCliente;
import DAO.DAOCompra;
import DAO.DAOProduto;
import Model.Cliente;
import Model.Compra;
import Model.Produto;
import dataSource.MySQLDataSource;

public class Entradas{

    private Tela opcaoTela;

    
    public void opcaoInicial() {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("==========================================");
            System.out.print("Escolha o que deseja: ");
            String decisao = input.nextLine().toUpperCase();
            if (decisao.equals("1")){
                opcaoTela.telaCliente();
            }else if (decisao.equals("2")){
                opcaoTela.telaFuncionario();
            } else if (decisao.equals("3")){
                System.out.println("Saindo do programa!");
                break;
            }
        }
    }

    public void entradaCliente() {
        Scanner entrada = new Scanner(System.in);
        
        // Atributos de cliente
        String nome;
        Float dinheiro;

        System.out.println("\n============ Entrando cliente ============\n");
        System.out.print("Nome do cliente: ");
        nome = entrada.nextLine();

        System.out.print("Dinheiro do cliente: R$");
        dinheiro = entrada.nextFloat();
        
        // Criar um objeto na classe
        Cliente cliente = new Cliente(nome, dinheiro);
        
        // Ligação ao banco de dados
        try{
            // Chamando DAOCliente e passando o Banco de dados
            DAOCliente daoCliente = new DAOCliente(MySQLDataSource.getInstance());
            // Cadastrar no registro do mercadinho
            daoCliente.adicionar(cliente);
        } catch (Exception e) {
            System.out.println("O Erro aconteceu na função entradaCliente()!");
        }
    }

    public void opcaoCliente(){
        Scanner input = new Scanner(System.in);
        DAOCliente daoCliente = new DAOCliente(MySQLDataSource.getInstance());
        DAOProduto daoProduto = new DAOProduto(MySQLDataSource.getInstance());
        DAOCompra daoCompra = new DAOCompra(MySQLDataSource.getInstance());

        Boolean condicao = true;
        while(condicao){
            System.out.println("\n==========================================\n");
            System.out.print("Escolha o que deseja: ");
            String decisao = input.nextLine().toUpperCase();
            System.out.println("\n==========================================\n");
            
            if (decisao.equals("C")){
                
                Cliente clienteAtual = daoCliente.obterUltimoCliente();
                Produto produto = daoProduto.consultar("decisao");
                Compra compra = new Compra(clienteAtual, produto);
                
            // }else if (decisao.equals("R")){
            //     removerComprar();
                
            // }else if (decisao.equals("A")){
            //     adicionarProduto();
                
            }else if (decisao.equals("T")){
                verTodosProduto();
                
            }else if (decisao.equals("P")){
                pesquisarProtuto();
            // }else if(decisao.equals("M")){
            //     System.out.println("========== Relatorio do mercado ==========");
            //     System.out.println(CadastroCliente.getInstance().listarClientes());
            //     System.out.println(CadastroCliente.getInstance().gastoTotal());
            } else if (decisao.equals("V")){
                voltar();
            } else if(decisao.equals("3")){
                sair();
                break;
            }
        }
    }

    

}
