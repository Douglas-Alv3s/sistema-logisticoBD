package Controller.Autenticar_Funcionario;

import DAO.DAOFuncionario;
import Model.Funcionario;
import View.Entradas;
import dataSource.MySQLDataSource;

public class AutenticadorFuncionario {
    
    public Boolean autenticarFuncionario(){
       // Tentativa de login.
        Funcionario funcionario = Entradas.getInstance().entradaFuncionario(); // Pega as opções de funcionario

        String loginVerificar = funcionario.getLogin();
        String senhaVerificar = funcionario.getSenha();
       
        DAOFuncionario daoFuncionario = new DAOFuncionario(MySQLDataSource.getInstance());
        Funcionario funcionarioPadrao = daoFuncionario.consultarFuncionario("admin", "1234");
        String login = funcionarioPadrao.getLogin();
        String senha = funcionarioPadrao.getSenha();

        // Verifica as informações passadas com as informações recebidas do banco de dados.
        if(loginVerificar.equals(login) && senhaVerificar.equals(senha)){
            System.out.println("Login aceito.");
            return true;
        }else {
            System.out.println("Login recusado.");
            return false;
        }
    }
}
