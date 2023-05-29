package Controller.Funcionario.Funcionario_cliente;

import DAO.DAOCliente;
import DAO.DAOCompra;
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

    public void relatorioMercado(){
        try{
            DAOCliente daoCliente = new DAOCliente(MySQLDataSource.getInstance());
            DAOCompra daoCompra = new DAOCompra(MySQLDataSource.getInstance());
            float lucro = daoCliente.consultarGastoTotal();
            
            String retornoGasto = String.format("\n-> Lucro do mercadinho: R$%.2f", lucro);
            daoCompra.obterNomeClienteEProduto(); // Traz o relacionamento muito pra muitos
            System.out.println(retornoGasto);
        }catch(Exception e){

        }
    }
}
