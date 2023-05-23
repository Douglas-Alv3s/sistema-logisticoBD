package View.Compra_Produto;

import DAO.DAOCliente;
import DAO.DAOProduto;
import Model.Cliente;
import Model.Produto;
import dataSource.MySQLDataSource;

public class CompraProduto {

    public void realizar_Compra(Inform_produto inform_produto){
        try{
            DAOCliente daoCliente = new DAOCliente(MySQLDataSource.getInstance());
        DAOProduto daoProduto = new DAOProduto(MySQLDataSource.getInstance());
        
        Cliente clienteInicial = daoCliente.obterUltimoCliente();
        Cliente cliente = clienteInicial;

        Produto produtoInicial = daoProduto.consultar(inform_produto.getNome());
        Produto produto = produtoInicial;

        if(produto.getQuantidade() > 0){
            if(produto.getQuantidade() >= inform_produto.getQuantidade()){
                // Fazer o desconto das compras no dinheiro do cliente
                    //Criado uma variavel que ira receber o valor dinheiro do cliente atual que está na lista
                Float dinheiro = cliente.getDinheiro();
                    //Criado a variavel que recebera o custo do produto
                Float custoProduto = produto.getValor() * inform_produto.getQuantidade();
                
                // Verificando se o cliente tem dinheiro suficiente para realizar a compra
                if (dinheiro >= custoProduto){
                    
                    //Realizando o calculo para o desconto no dinheiro atual do cliente
                    Float descontoDinheiro = dinheiro - custoProduto;

                // Fazendo atualização no dinheiro do cliente atual, após as compras realizadas
                    cliente.setDinheiro(descontoDinheiro);
                
                // Registando o gasto do Cliente
                    // Criado a variavel gasto, que ira pegar o quanto o cliente atual já gastou
                    Float gasto = cliente.getGasto();
                    // Criado variavel aumentGasto para facilitar na leitura do codigo
                    Float aumentoGasto = gasto;
                    // Fazendo os calculos do gasto atual
                    aumentoGasto += custoProduto;
                // Atualizando os gastos do cliente atual
                    cliente.setGasto(aumentoGasto);
                    
                // Atualizando registro do cliente no banco de dados
                    daoCliente.alterar(clienteInicial, cliente);

                    // Mostrando os Status do cliente
                    // System.out.println(cliente.umCliente());

                //Realizando atualização nos produtos do mercado
                    produto.setQuantidade(produto.getQuantidade() - inform_produto.getQuantidade());
                    daoProduto.alterar(produtoInicial, produto); // Atualização na tabela de produtos
                    
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
