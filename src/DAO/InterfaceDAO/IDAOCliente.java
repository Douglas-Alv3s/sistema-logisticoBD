package DAO.InterfaceDAO;


import java.util.ArrayList;

import Model.Cliente;
import Model.Compra;

public interface IDAOCliente {

    // public ArrayList<Cliente> consultarNome(String nome) throws ErroBDException;
    public Float consultarGastoTotal() ;
    public Cliente obterUltimoCliente() ;
	public ArrayList<Compra> obterLocacoesCliente(Cliente cliente);



}
