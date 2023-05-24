package Controller.Funcionario.Funcionario_cliente;

import DAO.DAOCliente;
import dataSource.MySQLDataSource;

public class VisualizarGasto {
    private static VisualizarGasto instance = null;

    static public VisualizarGasto getInstance() {
        if (instance == null) {
            instance = new VisualizarGasto();
            return instance;
        }
        return instance;
    }

    public void relatorioMercado(){
        try{
            DAOCliente daoCliente = new DAOCliente(MySQLDataSource.getInstance());
            float lucro = daoCliente.consultarGastoTotal();
            
            System.out.println("-> Lucro do mercadinho: R$%.2f" + lucro);
        }catch(Exception e){
        
        }
    }
}
