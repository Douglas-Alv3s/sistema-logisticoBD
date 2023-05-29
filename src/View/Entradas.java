package View;

import java.util.Scanner;

import Controller.Cliente_Produto.Compra_Produto.CompraProduto;
import Controller.Cliente_Produto.Compra_Produto.Inform_produto;
import Controller.Funcionario.Autenticar_Funcionario.ResultadoAutenticacao;
import Controller.Funcionario.Funcionario_cliente.LimparRelatorio;
import Controller.Funcionario.Funcionario_cliente.VisualizarGasto;
import Controller.Funcionario.Funcionario_produto.AdicionarProduto;
import Controller.Funcionario.Funcionario_produto.RemoverProduto;
import Controller.Produto.Mostrar_Produto.VisualizarProduto;
import Controller.Produto.Pesquisa_Produto.InformNome;
import Controller.Produto.Pesquisa_Produto.PesquisarProduto;
import DAO.DAOCliente;
import DAO.dataSource.MySQLDataSource;
import Model.Cliente;
import Model.Funcionario;

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
                ResultadoAutenticacao.getInstance().autenticarFuncionario();
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
               
            }else if (decisao.equals("T")){
                VisualizarProduto.getInstance().verProdutos(); 

            }else if (decisao.equals("P")){
                PesquisarProduto.getInstance().pesquisarProduto(new InformNome());

            } else if (decisao.equals("V")){
                Tela.getInstance().telaInicial();
            } else if(decisao.equals("3")){

                System.out.println("Saindo do programa!");
                break;
            }
        }
    }

    public Funcionario entradaFuncionario(){
        Scanner input = new Scanner(System.in);
        
        System.out.print("\nLogin: ");
        String login = input.next();
        System.out.print("Senha: ");
        String senha = input.next();

        Funcionario funcionario = new Funcionario(login, senha);
        return funcionario;
    }

    public void opcaoFuncionario(){
        Scanner input = new Scanner(System.in);

        while(true){
            Tela.getInstance().MostrarTelaFuncionario();
            System.out.println("\n==========================================\n");
            System.out.print("Escolha o que deseja: ");
            String decisao = input.nextLine().toUpperCase();
            System.out.println("\n==========================================\n");
            
            if (decisao.equals("A")){
                AdicionarProduto.getInstance().adicionarProdutos();
                
            }else if (decisao.equals("R")){
                RemoverProduto.getInstance().removerProduto(new InformNome());
                
            }else if (decisao.equals("T")){
                VisualizarProduto.getInstance().verProdutos();                
   
            }else if (decisao.equals("P")){
                PesquisarProduto.getInstance().pesquisarProduto(new InformNome());
            }else if(decisao.equals("M")){
                System.out.println("========== Relatorio do mercado ==========");
                VisualizarGasto.getInstance().relatorioMercado();
            }else if(decisao.equals("L")){
                System.out.println("========== Limpar registro do mercado ==========");
                LimparRelatorio.getInstance().limparRegistro();
            } else if (decisao.equals("V")){
                Tela.getInstance().telaInicial();
            } else if(decisao.equals("3")){
                System.out.println("Saindo do programa!");
                break;
            }
        }
        
    }

    

}
