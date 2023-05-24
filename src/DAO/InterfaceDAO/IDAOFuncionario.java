package DAO.InterfaceDAO;



import java.util.ArrayList;

import Model.Funcionario;


public interface IDAOFuncionario {
    
    public Funcionario consultarFuncionario(String login, String senha);
    public void adicionarFuncionario(Funcionario funcionario);
    public ArrayList<Funcionario> obterFuncionarioPadrao();
    // public Boolean autenticarFuncionario(Funcionario funcionario);
    public void inserirFuncionarioPadrao();    

}