package Controller.Cliente_Produto.Compra_Produto;

import DAO.DAOCliente;
import DAO.DAOCompra;
import DAO.DAOProduto;
import DAO.dataSource.MySQLDataSource;
import Model.Cliente;
import Model.Compra;
import Model.Produto;

public class CompraProduto {

    // Padrão de projeto Singleton pattern
    private static CompraProduto istance = null;
    static public CompraProduto getInstance(){
        if (istance == null){
            istance = new CompraProduto();
        }
        return istance;
    }
    
    public void realizarCompra(Inform_produto inform_produto){
        try{
            DAOCliente daoCliente = new DAOCliente(MySQLDataSource.getInstance());
            DAOProduto daoProduto = new DAOProduto(MySQLDataSource.getInstance());
            
            Cliente clienteInicial = daoCliente.obterUltimoCliente();
            // System.out.println("Esse cara aqui é o cliente inicial: " + clienteInicial);
            

            Produto produtoInicial = daoProduto.consultar(inform_produto.getNome());
            // System.out.println("id produto antigo: "+produtoInicial.getId_produto());
            // System.out.println("nome produto antigo: "+produtoInicial.getNome());

            if(produtoInicial.getQuantidade() > 0){
                if(produtoInicial.getQuantidade() >= inform_produto.getQuantidade()){
                    // Fazer o desconto das compras no dinheiro do cliente
                        
                    Float dinheiro = clienteInicial.getDinheiro(); //Criado uma variavel que ira receber o dinheiro do cliente atual 
                        
                    Float custoProduto = produtoInicial.getValor() * inform_produto.getQuantidade(); //Criado a variavel que recebera o custo do produto
                    
                    // Verificando se o cliente tem dinheiro suficiente para realizar a compra
                    if (dinheiro >= custoProduto){
                        
                    //Realizando o calculo para o desconto no dinheiro atual do cliente
                    
                    // Registando o gasto do Cliente
                        Float descontoDinheiro = dinheiro - custoProduto; // Dinheiro após o desconto do produto 
                        Float gasto = clienteInicial.getGasto(); // Criado a variavel gasto, que ira pegar o quanto o cliente atual já gastou
                        Float aumentoGasto = gasto; // Criado variavel aumentGasto para facilitar na leitura do codigo
                        aumentoGasto += custoProduto; // Fazendo os calculos do gasto atual
                        
                        

                    
                    
                    // Atualizando os gastos do cliente atual
                        Cliente cliente = new Cliente(clienteInicial.getId_cliente(), clienteInicial.getNome(), descontoDinheiro, aumentoGasto);
                        daoCliente.alterar(clienteInicial, cliente); // Atualizando registro do cliente no banco de dados


                    //Realizando atualização nos produtos do mercado
                        int quantidadeAlterada = produtoInicial.getQuantidade() - inform_produto.getQuantidade();
                        Produto produto = new Produto(produtoInicial.getId_produto(), produtoInicial.getNome(), produtoInicial.getValor(), quantidadeAlterada);
                        daoProduto.alterar(produtoInicial, produto); // Atualização na tabela de produtos
                        

                    // Realização do registro de cliente comprando produto.
                        Compra compra = new Compra(cliente, produto);
                        DAOCompra daoCompra = new DAOCompra(MySQLDataSource.getInstance());
                        daoCompra.realizarCompra(compra);

                        System.out.println("Compra Realizada pelo cliente "+ cliente.getNome() + " do produto "+ produto.getNome());
                        System.out.println("Dinheiro atual: " + descontoDinheiro);
                        System.out.println("Gasto na compra: " + aumentoGasto);

                    }else{
                        System.out.println("\n------------------------------------------\nDinheiro insuficiente para realizar a compra.");
                    }
                }else{
                    System.out.println("\n------------------------------------------\nQuantidade invalida");
                }
            }else{
                System.out.println("\n------------------------------------------\nSem estoque desse produto. Compra não realizada.");
            }

        }catch(Exception e){
            System.out.println("O Erro ta acontecendo no momento de realizar a compra.");
        } 
    }
}
