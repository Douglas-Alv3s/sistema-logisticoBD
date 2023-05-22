package DAO;

import java.util.ArrayList;

import Model.Cliente;
import exceptions.ErroBDException;

public interface IDAOCliente {

    /**
	 * Instancia um cliente a partir do BD
	 * @param id - id do cliente a ser recuperado do BD
	 * @return - a instancia do cliente desejado ou NULL, caso nao haja cliente com o id informado
	 */
	public Cliente consultar(String id) throws ErroBDException;
    public ArrayList<Cliente> consultarNome(String nome) throws ErroBDException;
    public Float consultarGastoTotal() throws ErroBDException;


}
