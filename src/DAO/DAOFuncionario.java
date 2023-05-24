package DAO;

import java.util.ArrayList;

import DAO.InterfaceDAO.IDAOFuncionario;
import Model.Funcionario;



public class DAOFuncionario implements IDAOFuncionario {
    
    public DAOFuncionario(){

    }

    @Override
    public Funcionario consultar(String usuario) {
        
        throw new UnsupportedOperationException("Unimplemented method 'consultar'");
    }

    @Override
    public ArrayList<Funcionario> consultarSenha(String senha) {
        
        throw new UnsupportedOperationException("Unimplemented method 'consultarSenha'");
    }

    
}
