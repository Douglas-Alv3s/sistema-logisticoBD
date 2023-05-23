package TelaInical;

import DAO.DAOCliente;
import dataSource.CriacaoBD;
import dataSource.MySQLDataSource;


public class MainTela {
    public static void main(String[] args) {
        System.out.println("Helloy problemas!!!");
        CriacaoBD.getInstance();
        
        // // Criar uma instância do seu objeto de implementação de IDataSource
        // IDataSource dataSource = new MySQLDataSource();
            
        // // Criar uma instância do DAOCliente passando o objeto de IDataSource
        // DAOCliente daoCliente = new DAOCliente(dataSource);


    }
}