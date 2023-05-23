package TelaInical;

import java.util.ArrayList;

import DAO.DAOCliente;
import Model.Cliente;
import dataSource.CriacaoBD;
import dataSource.MySQLDataSource;
import exceptions.ErroBDException;

public class MainTesteBD {
    public static void main(String[] args) {
        CriacaoBD.getInstance();
        
        try {
            // Criar uma instância do seu objeto de implementação de MySQLDataSource
            // MySQLDataSource dataSource = new MySQLDataSource(); -> como não seria instânciado mais de um tipo de objeto do tipo MySQLDataSource
            
            // Criar uma instância do DAOCliente passando o objeto de IDataSource
            DAOCliente daoCliente = new DAOCliente(MySQLDataSource.getInstance()); //-> Foi usado o Padrão Sigleton
            
            // Testar o método consultar
            String login = "joao123";
            Cliente cliente = daoCliente.consultar(login);
            if (cliente != null) {
                System.out.println("Cliente encontrado: " + cliente.getLogin());
            } else {
                System.out.println("Cliente não encontrado.");
            }
            
            // Testar o método adicionar
            Cliente novoCliente = new Cliente("maria456", 1000f);
            daoCliente.adicionar(novoCliente);
            System.out.println("Novo cliente adicionado: " + novoCliente.getLogin()+"\n");
            
            // Testar o método remover
            String loginRemover = "joao123";
            daoCliente.remover(loginRemover);
            
            loginRemover = "maria789";
            daoCliente.remover(loginRemover);
            
            // Testar o método alterar
            String loginAntigo = "maria456";
            String novoLogin = "maria789";
            Cliente clienteAlterado = new Cliente(novoLogin, 1500f);
            daoCliente.alterar(loginAntigo, clienteAlterado);
            System.out.println("Cliente alterado: " + novoLogin);
            
            // Testar o método obterTodos
            ArrayList<Cliente> clientes = daoCliente.obterTodos();
            System.out.println("Lista de clientes:");
            for (Cliente c : clientes) {
                System.out.println(c.getLogin());
            }
            
            // Testar o método consultarGastoTotal
            float gastoTotal = daoCliente.consultarGastoTotal();
            System.out.println("Gasto total: R$" + gastoTotal);
            
        } catch (ErroBDException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
            
    }
        
    


}
