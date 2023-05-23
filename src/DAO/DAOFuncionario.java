package DAO;

import java.util.ArrayList;

import DAO.InterfaceDAO.IDAOFuncionario;
import Model.Funcionario;
import exceptions.ErroBDException;


public class DAOFuncionario implements IDAOFuncionario {
    
    public DAOFuncionario(){

    }

    @Override
    public Funcionario consultar(String usuario) throws ErroBDException {
        
        throw new UnsupportedOperationException("Unimplemented method 'consultar'");
    }

    @Override
    public ArrayList<Funcionario> consultarSenha(String senha) throws ErroBDException {
        
        throw new UnsupportedOperationException("Unimplemented method 'consultarSenha'");
    }

    
}
