package Controller.Funcionario.Funcionario_produto;

import java.util.Scanner;

import DAO.DAOProduto;
import DAO.dataSource.MySQLDataSource;
import Model.Produto;

public class AdicionarProduto {
    private static AdicionarProduto instance = null;

    static public AdicionarProduto getInstance() {
        if (instance == null) {
            instance = new AdicionarProduto();
            return instance;
        }
        return instance;
    }
    

    public void adicionarProdutos() {
        
        Scanner input = new Scanner(System.in);
        
        System.out.print("Nome do produto: ");
        String nome = input.next();
        System.out.print("Preço do produto: ");
        Float preco = input.nextFloat();
        System.out.print("Quantidade do produto: ");
        int quantidade = input.nextInt();


        try{
            DAOProduto daoProduto = new DAOProduto(MySQLDataSource.getInstance());
            Produto produtoConsultado = daoProduto.consultar(nome);

            if(produtoConsultado == null){ 
                Produto produto = new Produto(nome, preco, quantidade);
                daoProduto.adicionar(produto);
            }else{
                Produto produto = new Produto(produtoConsultado.getId_produto(), nome, preco, quantidade);
                daoProduto.alterar(produtoConsultado, produto);

            }

        }catch(Exception e){

        }
    }
}
