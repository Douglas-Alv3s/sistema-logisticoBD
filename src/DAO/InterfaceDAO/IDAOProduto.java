package DAO.InterfaceDAO;

import java.util.ArrayList;


import Model.Compra;
import Model.Produto;


public interface IDAOProduto {

    public void inserirProdutosPadrao();
    public ArrayList<Compra> obterCompraProduto(Produto produto);

    
}
