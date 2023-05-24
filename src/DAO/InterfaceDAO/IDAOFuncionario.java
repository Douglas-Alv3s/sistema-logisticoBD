package DAO.InterfaceDAO;

import java.util.ArrayList;

import Model.Funcionario;


public interface IDAOFuncionario {
    
	public Funcionario consultar(String usuario);
    public ArrayList<Funcionario> consultarSenha(String senha);

}