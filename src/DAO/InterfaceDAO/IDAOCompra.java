package DAO.InterfaceDAO;

import java.util.ArrayList;

import Model.Cliente;
import Model.Compra;


public interface IDAOCompra {
    
    public ArrayList<Compra> mostrarCompras() ;
    public void realizarCompra(Compra compra) ;
    public void obterNomeClienteEProduto() ;
}
