package DAO.InterfaceDAO;

import java.util.ArrayList;

import Model.Cliente;
import Model.Compra;
import exceptions.ErroBDException;

public interface IDAOCompra {
    
    public ArrayList<Compra> mostrarCompras() throws ErroBDException ;
    public void realizarCompra(Compra compra) throws ErroBDException ;
    public ArrayList<Compra> mostrarComprasPorCliente(Cliente cliente) throws ErroBDException ;
}
