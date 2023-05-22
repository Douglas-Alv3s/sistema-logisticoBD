package DAO;

import java.util.ArrayList;

import Model.Cliente;
import exceptions.ErroBDException;

public interface IDAOCliente {

    // public ArrayList<Cliente> consultarNome(String nome) throws ErroBDException;
    public Float consultarGastoTotal() throws ErroBDException;


}
