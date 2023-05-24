package DAO.InterfaceDAO;

import Model.Cliente;

public interface IDAOCliente {

    public Float consultarGastoTotal() ;
    public Cliente obterUltimoCliente() ;
    public void limparRegistro();

}
