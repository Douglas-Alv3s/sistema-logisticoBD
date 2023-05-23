package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.InterfaceDAO.IDAOCompra;
import Model.Cliente;
import Model.Compra;
import Model.Produto;
import dataSource.MySQLDataSource;
import exceptions.ErroBDException;

public class DAOCompra implements IDAOCompra{
    private MySQLDataSource dataSource;
    private DAOCliente daoCliente;
    private DAOProduto daoProduto;

    public DAOCompra(MySQLDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void realizarCompra(Compra compra) throws ErroBDException {
        String sql = "INSERT INTO compra (id_clienteFK, id_produtoFK) VALUES ('" +
                compra.getCliente().getId_cliente() + "', " +
                compra.getProduto().getId_produto() + ")";

        try {
            dataSource.executarQueryGeral(sql);
        } catch (Exception e) {
            throw new ErroBDException("Erro ao adicionar a compra.", e);
        }
    }

    @Override
    public ArrayList<Compra> mostrarCompras() throws ErroBDException {
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
            throw new ErroBDException("Erro ao listar as compras.", e);
        }

        return compras;
    }

    @Override
    public ArrayList<Compra> mostrarComprasPorCliente(Cliente cliente) throws ErroBDException {
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
            throw new ErroBDException("Erro ao listar as compras do cliente " + cliente.getNome(), e);
        }

        return compras;
    }


}
