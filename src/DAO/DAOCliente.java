package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;


import Model.Cliente;
import dataSource.IDataSource;
import exceptions.ErroBDException;

public class DAOCliente implements IDAOGenerico<Cliente>, IDAOCliente{
    private IDataSource dataSource;
    
    
    public DAOCliente(IDataSource dataSource) throws ErroBDException{
        this.dataSource = dataSource;
    }

    @Override
    public Cliente consultar(String login) throws ErroBDException {
        try {
            String sql = "SELECT * FROM cliente WHERE login = '" + login + "'";
            ResultSet resultado = dataSource.executarSelect(sql);
    
            if (resultado.next()) {
                // Extrair os dados do ResultSet e criar um objeto Cliente
                String clienteLogin = resultado.getString("login");
                float clienteDinheiro = resultado.getFloat("dinheiro");
    
                Cliente cliente = new Cliente(clienteLogin, clienteDinheiro);
                return cliente;
            } else {
                // Cliente não encontrado
                return null;
            }
        } catch (Exception e) {
            // Tratar a exceção e retornar um valor padrão (pode ser null) em caso de erro
            System.err.println("Erro ao consultar cliente no banco de dados: " + e.getMessage());
            return null;
        }
    }


    public void adicionar(Cliente cliente) throws ErroBDException {
        try {
            String login = cliente.getLogin();
            
            // Verificar se o cliente já existe
            Cliente clienteExistente = consultar(login);          
            if (clienteExistente != null) {
                System.out.println("O cliente com login '" + login + "' já existe no banco de dados.");
                return; // Encerra o método, não adicionando um novo cliente
            }
            
            String sql = "INSERT INTO cliente (login, dinheiro, gasto) VALUES ('" + login + "', " + cliente.getDinheiro() + ", " + cliente.getGasto() + ")";
            dataSource.executarQueryGeral(sql);
            System.out.println("Cliente adicionado com sucesso: " + login);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar cliente no banco de dados: " + e.getMessage());
        }
    }

    @Override
    public void remover(String login) throws ErroBDException {
        try {
            // Verificar se o cliente já existe
            Cliente clienteExistente = consultar(login);
                       
            if (clienteExistente == null) {
                System.out.println("O cliente com login '" + login + "' não existe no banco de dados. Impossivel remover.");
                return; // Encerra o método, não adicionando um novo cliente
            }
            String sql = "DELETE FROM cliente WHERE login = '" + login + "'";
            System.out.println("Cliente com login: '" + login+"' removido." );
            dataSource.executarQueryGeral(sql);
        } catch (Exception e) {
            throw new ErroBDException("Erro ao remover cliente do banco de dados", e);
        }
    }

    @Override
    public void alterar(String chaveAntiga, Cliente dadosNovos) throws ErroBDException {
        try {
            String sql = "UPDATE cliente SET login = '" + dadosNovos.getLogin() + "', dinheiro = " + dadosNovos.getDinheiro() + ", gasto = " + dadosNovos.getGasto() + " WHERE login = '" + chaveAntiga + "'";
            dataSource.executarQueryGeral(sql);
        } catch (Exception e) {
            throw new ErroBDException("Erro ao alterar cliente no banco de dados", e);
        }
    }

    // @Override
    // public ArrayList<Cliente> consultarNome(String nome) throws ErroBDException {
        
    //     throw new UnsupportedOperationException("Unimplemented method 'consultarNome'");
    // }

   
    @Override
    public Float consultarGastoTotal() throws ErroBDException {
        try {
            String sql = "SELECT SUM(gasto) AS gasto_total FROM cliente";
            ResultSet resultado = dataSource.executarSelect(sql);
            if (resultado.next()) {
                float gastoTotal = resultado.getFloat("gasto_total");
                resultado.close();
                return gastoTotal;
            }
            resultado.close();
        } catch (Exception e) {
            throw new ErroBDException("Erro ao consultar gasto total no banco de dados", e);
        }
        return 0f; // Se não houver resultados, retorna 0
    }


    
    @Override
    public ArrayList<Cliente> obterTodos() throws ErroBDException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cliente";
            ResultSet resultado = dataSource.executarSelect(sql);
            while (resultado.next()) {
                String login = resultado.getString("login");
                float dinheiro = resultado.getFloat("dinheiro");
                float gasto = resultado.getFloat("gasto");
                Cliente cliente = new Cliente(login, dinheiro);
                cliente.setGasto(gasto);
                clientes.add(cliente);
            }
            resultado.close();
        } catch (Exception e) {
            throw new ErroBDException("Erro ao obter todos os clientes do banco de dados", e);
        }
        return clientes;
    }

    
}
