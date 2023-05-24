package Controller.Funcionario.Funcionario_produto;

import Controller.Produto.Pesquisa_Produto.InformNome;
import DAO.DAOProduto;
import DAO.dataSource.MySQLDataSource;

public class RemoverProduto {
    
    private static RemoverProduto instance = null;

    static public RemoverProduto getInstance() {
        if (instance == null) {
            instance = new RemoverProduto();
            return instance;
        }
        return instance;
    }

    public void removerProduto(InformNome informNomeProduto){
        String nomeProduto = informNomeProduto.getNome();

        try{
            DAOProduto daoProduto = new DAOProduto(MySQLDataSource.getInstance());
            System.out.println("--------------------------------");
            daoProduto.remover(nomeProduto);
            System.out.println("Produto Removido  " + nomeProduto);
        }catch(Exception e){
            System.out.println("Erro aconteceu no arquivo RemoverProduto na pasta Funcionario_produto");
        }
    }

}
