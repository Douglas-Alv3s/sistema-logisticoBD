package DAO.InterfaceDAO;

import java.util.ArrayList;


import Model.Compra;
import Model.Produto;
import exceptions.ErroBDException;

public interface IDAOProduto {

    public void inserirProdutosPadrao() throws ErroBDException;
    public ArrayList<Compra> obterCompraProduto(Produto produto)throws ErroBDException;

    
}
