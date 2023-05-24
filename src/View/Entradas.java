package View;

import java.util.Scanner;

import Controller.Compra_Produto.CompraProduto;
import Controller.Compra_Produto.Inform_produto;
import Controller.Mostrar_Produto.VisualizarProduto;
import Controller.Pesquisa_Produto.InformNome;
import Controller.Pesquisa_Produto.PesquisarProduto;
import DAO.DAOCliente;
import Model.Cliente;
import dataSource.MySQLDataSource;

public class Entradas{
    
    // Padrão de projeto Singleton pattern
    private static Entradas istance = null;
    static public Entradas getInstance(){
        if (istance == null){
            istance = new Entradas();
        }
        return istance;
    }

    public void opcaoInicial() {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("==========================================");
            System.out.print("Escolha o que deseja: ");
            String decisao = input.next();
            if (decisao.equals("1")){
                Tela.getInstance().telaCliente();
            }else if (decisao.equals("2")){
                Tela.getInstance().telaFuncionario();
            } else if (decisao.equals("3")){
                System.out.println("Saindo do programa!");
                break;
            }
        }
    }

    public void entradaCliente() {
        Scanner input = new Scanner(System.in);
        
        // Atributos de cliente
        String nome;
        Float dinheiro;

        System.out.println("\n============ Entrando cliente ============\n");
        System.out.print("Nome do cliente: ");
        nome = input.nextLine();
        System.out.print("Dinheiro do cliente: R$");
        dinheiro = input.nextFloat();
        
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

        while(true){
            Tela.getInstance().MostrarTelaCliente();
            System.out.println("\n==========================================\n");
            System.out.print("Escolha o que deseja: ");
            String decisao = input.nextLine().toUpperCase();
            System.out.println("\n==========================================\n");
            
            if (decisao.equals("C")){
                CompraProduto.getInstance().realizarCompra(new Inform_produto());
                
            // }else if (decisao.equals("R")){
            //     removerComprar();
                
            // }else if (decisao.equals("A")){
            //     adicionarProduto();
                
            }else if (decisao.equals("T")){
                VisualizarProduto.getInstance().verProdutos();                
   
            }else if (decisao.equals("P")){
                PesquisarProduto.getInstance().pesquisarProduto(new InformNome());
            // }else if(decisao.equals("M")){
            //     System.out.println("========== Relatorio do mercado ==========");
            //     System.out.println(CadastroCliente.getInstance().listarClientes());
            //     System.out.println(CadastroCliente.getInstance().gastoTotal());
            } else if (decisao.equals("V")){
                Tela.getInstance().telaInicial();
            } else if(decisao.equals("3")){
                System.out.println("Saindo do programa!");
                break;
            }
        }
    }

    public void opcaoFuncionario(){
        Scanner input = new Scanner(System.in);

        while(true){
            Tela.getInstance().MostrarTelaFuncionario();
            System.out.println("\n==========================================\n");
            System.out.print("Escolha o que deseja: ");
            String decisao = input.nextLine().toUpperCase();
            System.out.println("\n==========================================\n");
            
            if (decisao.equals("C")){
                CompraProduto.getInstance().realizarCompra(new Inform_produto());
                
            }else if (decisao.equals("R")){
            //     removerComprar();
                
            }else if (decisao.equals("A")){
            //     adicionarProduto();
                
            }else if (decisao.equals("T")){
                VisualizarProduto.getInstance().verProdutos();                
   
            }else if (decisao.equals("P")){
                PesquisarProduto.getInstance().pesquisarProduto(new InformNome());
            }else if(decisao.equals("M")){
                System.out.println("========== Relatorio do mercado ==========");
            //     System.out.println(CadastroCliente.getInstance().listarClientes());
            //     System.out.println(CadastroCliente.getInstance().gastoTotal());
            } else if (decisao.equals("V")){
                Tela.getInstance().telaInicial();
            } else if(decisao.equals("3")){
                System.out.println("Saindo do programa!");
                break;
            }
        }
        
    }

    

}
