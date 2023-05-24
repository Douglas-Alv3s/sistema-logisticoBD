package Controller.Funcionario.Funcionario_cliente;

import java.util.Scanner;

import DAO.DAOCliente;
import DAO.dataSource.MySQLDataSource;

public class LimparRelatorio {
    
    // Padrão singleton
    private static LimparRelatorio instance = null;
    static public LimparRelatorio getInstance() {
        if (instance == null) {
            instance = new LimparRelatorio();
            return instance;
        }
        return instance;
    }

    public void limparRegistro(){
        Scanner input = new Scanner(System.in);
        System.out.print("Tem certeza que deseja limpar o registro? [S]/[N] -> ");
        String resposta = input.next().toUpperCase();
        try{
            if(resposta.equals("S")){
                DAOCliente daoCliente = new DAOCliente(MySQLDataSource.getInstance());
                daoCliente.limparRegistro();
            }else{
                
            }
            
        }catch(Exception e){
        }
    }

}
