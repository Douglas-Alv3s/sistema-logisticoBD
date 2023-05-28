package Controller.Produto.Pesquisa_Produto;

import DAO.DAOProduto;
import DAO.dataSource.MySQLDataSource;
import Model.Produto;

public class PesquisarProduto{
    
    // Padrão de projeto Singleton pattern
    private static PesquisarProduto istance = null;
    static public PesquisarProduto getInstance(){
        if (istance == null){
            istance = new PesquisarProduto();
        }
        return istance;
    }

    public void pesquisarProduto(InformNome informNomeProduto){
        //Chamada Enhanced for-loop para percorrer toda a lista.
        String nomeProduto = informNomeProduto.getNome();

        try{
            DAOProduto daoProduto = new DAOProduto(MySQLDataSource.getInstance());
            System.out.println("--------------------------------");
            Produto produto = daoProduto.consultar(nomeProduto);
            if(produto != null){
                System.out.println("Dados do  " + produto);
            }else{
                System.out.println("O produto " + produto + " não esta disponivel.");
            }
        }catch(Exception e){
            System.out.println("Erro aconteceu no arquivo PesquisarProduto na pasta Pesquisa_Produto");
        }
    }
}
