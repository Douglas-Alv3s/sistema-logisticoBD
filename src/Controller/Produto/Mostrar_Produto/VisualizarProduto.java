package Controller.Produto.Mostrar_Produto;

import java.util.ArrayList;
import java.util.Iterator;

import DAO.DAOProduto;
import DAO.dataSource.MySQLDataSource;
import Model.Produto;

public class VisualizarProduto {
    
    // Padrão de projeto Singleton pattern
    private static VisualizarProduto istance = null;
    static public VisualizarProduto getInstance(){
        if (istance == null){
            istance = new VisualizarProduto();
        }
        return istance;
    }

    public void verProdutos(){
        try{
            DAOProduto daoProduto = new DAOProduto(MySQLDataSource.getInstance());
            ArrayList<Produto> todosProdutos= daoProduto.obterTodos();
            
            Iterator<Produto> it = todosProdutos.iterator();
            while(it.hasNext()){
                System.out.println(it.next());
            }
        }catch(Exception e){} 
    }
}
