package Controller.Funcionario.Funcionario_cliente;

import DAO.DAOCliente;
import DAO.dataSource.MySQLDataSource;

public class VisualizarGasto {
    private static VisualizarGasto instance = null;

    static public VisualizarGasto getInstance() {
        if (instance == null) {
            instance = new VisualizarGasto();
            return instance;
        }
        return instance;
    }

    public String relatorioMercado(){
        try{
            DAOCliente daoCliente = new DAOCliente(MySQLDataSource.getInstance());
            float lucro = daoCliente.consultarGastoTotal();
            
            String retornoGasto = String.format("-> Lucro do mercadinho: R$%.2f", lucro);
            return retornoGasto;
        }catch(Exception e){
            return null;
        }
    }
}
