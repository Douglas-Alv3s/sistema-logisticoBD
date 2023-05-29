package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.InterfaceDAO.IDAOCompra;
import DAO.dataSource.MySQLDataSource;
import Model.Cliente;
import Model.Compra;
import Model.Produto;


public class DAOCompra implements IDAOCompra{
    private MySQLDataSource dataSource;
    private DAOCliente daoCliente;
    private DAOProduto daoProduto;

    public DAOCompra(MySQLDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void realizarCompra(Compra compra){
        String sql = "INSERT INTO compra (id_clienteFK, id_produtoFK) VALUES ('" +
                compra.getCliente().getId_cliente() + "', " +
                compra.getProduto().getId_produto() + ")";

        try {
            dataSource.executarQueryGeral(sql);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar a compra.");
        }
    }

    @Override
    public ArrayList<Compra> mostrarCompras(){
        ArrayList<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra";

        try {
            ResultSet resultSet = dataSource.executarSelect(sql);

            while (resultSet.next()) {
                String clienteNome = resultSet.getString("nome");
                String produtoNome = resultSet.getString("nome");

                Cliente cliente = daoCliente.consultar(clienteNome);;
                Produto produto = daoProduto.consultar(produtoNome);

                Compra compra = new Compra(cliente, produto);
                compras.add(compra);
            }

            resultSet.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar as compras.");
        }

        return compras;
    }

    @Override
    public void obterNomeClienteEProduto(){
        try {
            String sql = "SELECT DISTINCT c.nome AS nomeCliente, p.nome AS nomeProduto FROM compra co INNER JOIN cliente c ON co.id_clienteFK = c.id_cliente INNER JOIN produto p ON co.id_produtoFK = p.id_produto";

            ResultSet resultado = dataSource.executarSelect(sql);

            while (resultado.next()) {
                String nomeCliente = resultado.getString("nomeCliente");
                String nomeProduto = resultado.getString("nomeProduto");
                System.out.println("O cliente " + nomeCliente + " comprou " + nomeProduto);
            }
            
            return ; // Caso a compra não seja encontrada ou não exista correspondência
            
        } catch (Exception e) {
           
        }
    }


}
