package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.InterfaceDAO.IDAOCliente;
import DAO.InterfaceDAO.IDAOGenerico;
import Model.Cliente;
import Model.Compra;
import dataSource.MySQLDataSource;


public class DAOCliente implements IDAOGenerico<Cliente>, IDAOCliente{
    private MySQLDataSource dataSource;
    
    
    public DAOCliente(MySQLDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Cliente consultar(String nome)  {
        try {
            String sql = "SELECT * FROM cliente WHERE nome = '" + nome + "'";
            ResultSet resultado = dataSource.executarSelect(sql);
    
            if (resultado.next()) {
                // Extrair os dados do ResultSet e criar um objeto Cliente
                
                String nome_cliente = resultado.getString("nome");
                float dinheiro = resultado.getFloat("dinheiro");
    
                Cliente cliente = new Cliente(nome_cliente, dinheiro);
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

    @Override
    public void adicionar(Cliente cliente)  {
        try {
            String nome = cliente.getNome();
            
            // Verificar se o cliente já existe
            Cliente clienteExistente = consultar(nome);          
            if (clienteExistente != null) {
                System.out.println("O cliente com nome '" + nome + "' já existe no banco de dados.");
                return; // Encerra o método, não adicionando um novo cliente
            }
            
            String sql = "INSERT INTO cliente (nome, dinheiro, gasto) VALUES ('" + nome + "', " + cliente.getDinheiro() + ", " + cliente.getGasto() + ")";
            dataSource.executarQueryGeral(sql);
            System.out.println("Cliente adicionado com sucesso: " + nome);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar cliente no banco de dados: " + e.getMessage());
        }
    }

    @Override
    public Cliente obterUltimoCliente()  {
        Cliente cliente = null;
        ResultSet resultSet = null;

        try {
            // Cria a consulta SQL para obter o último cliente adicionado
            String sql = "SELECT * FROM cliente ORDER BY id_cliente DESC LIMIT 1";

            // Executa a consulta
            resultSet  = dataSource.executarSelect(sql);

            // Verifica se há um resultado
            if (resultSet.next()) {
                // Extrai os dados do resultado e cria um objeto Cliente
                int id_cliente = resultSet.getInt("id_cliente") ;
                String nome = resultSet.getString("nome");
                Float dinheiro = resultSet.getFloat("dinheiro");
                
                cliente = new Cliente(id_cliente, nome, dinheiro);
                
            }
        } catch (Exception e) {
            
        }
        return cliente;
    }

    @Override
    public void remover(String nome)  {
        try {
            // Verificar se o cliente já existe
            Cliente clienteExistente = consultar(nome);
                       
            if (clienteExistente == null) {
                System.out.println("O cliente com nome '" + nome + "' não existe no banco de dados. Impossivel remover.");
                return; // Encerra o método, não adicionando um novo cliente
            }
            String sql = "DELETE FROM cliente WHERE nome = '" + nome + "'";
            System.out.println("Cliente com nome: '" + nome+"' removido." );
            dataSource.executarQueryGeral(sql);
        } catch (Exception e) {
            System.out.println("Erro ao remover cliente do banco de dados");
        }
    }

    @Override
    public void alterar(Cliente dadosAntigo, Cliente dadosNovos){
        try {
            String sql = "UPDATE cliente SET id_cliente = '" + dadosNovos.getId_cliente() + "', nome = '"+ dadosNovos.getNome()+"', dinheiro = " + dadosNovos.getDinheiro() + ", gasto = " + dadosNovos.getGasto() + " WHERE id_cliente = '" + dadosAntigo.getId_cliente() + "'";
            System.out.println("Comando SQL: "+sql);
            dataSource.executarQueryGeral(sql);
        } catch (Exception e) {
            System.out.println("Erro ao alterar cliente no banco de dados");
        }
    }

    // @Override
    // public ArrayList<Cliente> consultarNome(String nome){
        
    //     throw new UnsupportedOperationException("Unimplemented method 'consultarNome'");
    // }

   
    @Override
    public Float consultarGastoTotal(){
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
            System.out.println("Erro ao consultar gasto total no banco de dados");
        }
        return 0f; // Se não houver resultados, retorna 0
    }
    
    @Override
    public ArrayList<Cliente> obterTodos(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cliente";
            ResultSet resultado = dataSource.executarSelect(sql);
            while (resultado.next()) {
                
                String nome = resultado.getString("nome");
                float dinheiro = resultado.getFloat("dinheiro");
                float gasto = resultado.getFloat("gasto");
                Cliente cliente = new Cliente(nome, dinheiro);
                cliente.setGasto(gasto);
                clientes.add(cliente);
            }
            resultado.close();
        } catch (Exception e) {
            System.out.println("Erro ao obter todos os clientes do banco de dados");
        }
        return clientes;
    }

    @Override
    public ArrayList<Compra> obterLocacoesCliente(Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterLocacoesCliente'");
    }

    
}
