package DAO.InterfaceDAO;

import java.util.ArrayList;

import Model.Funcionario;
import exceptions.ErroBDException;

public interface IDAOFuncionario {
    
	public Funcionario consultar(String usuario) throws ErroBDException;
    public ArrayList<Funcionario> consultarSenha(String senha) throws ErroBDException;

}