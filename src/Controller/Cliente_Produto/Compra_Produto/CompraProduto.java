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
            System.out.println("Esse cara aqui é o cliente inicial: " + clienteInicial);
            

            Produto produtoInicial = daoProduto.consultar(inform_produto.getNome());
            System.out.println("id produto antigo: "+produtoInicial.getId_produto());
            System.out.println("nome produto antigo: "+produtoInicial.getNome());

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
                        
                        System.out.println("Gasto ocorrido: " + aumentoGasto);
                        System.out.println("Dinheiro pós desconto: " + descontoDinheiro);

                    
                    
                    // Atualizando os gastos do cliente atual
                        Cliente cliente = new Cliente(clienteInicial.getId_cliente(), clienteInicial.getNome(), descontoDinheiro, aumentoGasto);
                        daoCliente.alterar(clienteInicial, cliente); // Atualizando registro do cliente no banco de dados

                        System.out.println("id antigo: "+clienteInicial.getId_cliente() + "/ id novo " + cliente.getId_cliente());
                        // Mostrando os Status do cliente
                        // System.out.println(cliente.umCliente());


                    //Realizando atualização nos produtos do mercado
                        int quantidadeAlterada = produtoInicial.getQuantidade() - inform_produto.getQuantidade();
                        Produto produto = new Produto(produtoInicial.getId_produto(), produtoInicial.getNome(), produtoInicial.getValor(), quantidadeAlterada);
                        daoProduto.alterar(produtoInicial, produto); // Atualização na tabela de produtos
                        
                        System.out.println("id produto antigo: "+produtoInicial.getId_produto() + "/ id produto novo " + produto.getId_produto());

                    // Realização do registro de cliente comprando produto.
                        Compra compra = new Compra(cliente, produto);
                        DAOCompra daoCompra = new DAOCompra(MySQLDataSource.getInstance());
                        daoCompra.realizarCompra(compra);

                        System.out.println("Compra Realizada pelo cliente "+ cliente.getNome() + " do produto "+ produto.getNome());


                    // Realização da remoção do produto caso zere o estoque
                        if (produto.getQuantidade() == 0){                        
                            daoProduto.remover(produto.getNome()); // Remoção caso a quantidade de produtos seja zerada
                        }
                    }else{
                        System.out.println("\n------------------------------------------\nDinheiro insuficiente para realizar a compra.");
                    }
                }else{
                    System.out.println("\n------------------------------------------\nQuantidade invalida");
                }
            }
        }catch(Exception e){
            System.out.println("O Erro ta acontecendo no momento de realizar a compra.");
        } 
    }
}
