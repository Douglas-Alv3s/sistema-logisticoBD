package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.InterfaceDAO.IDAOCompra;
import Model.Cliente;
import Model.Compra;
import Model.Produto;
import dataSource.MySQLDataSource;


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
    public ArrayList<Compra> mostrarComprasPorCliente(Cliente cliente){
        ArrayList<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra WHERE id_clienteFK = '" + cliente.getId_cliente() + "'";

        try {
            ResultSet resultSet = dataSource.executarSelect(sql);

            while (resultSet.next()) {
                String produtoNome = resultSet.getString("nome");

                Produto produto = daoProduto.consultar(produtoNome);

                Compra compra = new Compra(cliente, produto);
                compras.add(compra);
            }

            resultSet.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar as compras do cliente " + cliente.getNome());
        }

        return compras;
    }


}
