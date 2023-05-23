package DAO.InterfaceDAO;


import java.util.ArrayList;

import Model.Cliente;
import Model.Compra;
import exceptions.ErroBDException;

public interface IDAOCliente {

    // public ArrayList<Cliente> consultarNome(String nome) throws ErroBDException;
    public Float consultarGastoTotal() throws ErroBDException;
    public Cliente obterUltimoCliente() throws ErroBDException;
	public ArrayList<Compra> obterLocacoesCliente(Cliente cliente)throws ErroBDException;



}
