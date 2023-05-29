package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.InterfaceDAO.IDAOFuncionario;
import DAO.dataSource.MySQLDataSource;
import Model.Funcionario;



public class DAOFuncionario implements IDAOFuncionario {
    private MySQLDataSource dataSource;
    
    public DAOFuncionario(MySQLDataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public void inserirFuncionarioPadrao() {
       // Verificar se os funcionarioPadrao já foram inseridos
       try {
            ArrayList<Funcionario> funcionarioPadrao = obterFuncionarioPadrao();
            if (!funcionarioPadrao.isEmpty()) {
                System.out.println("Os funcionarioPadrao iniciais já foram inseridos anteriormente.");
                return; // Não é necessário inserir novamente
            }else{

                // Inserir os funcionario Padrao na tabela funcionario
                Funcionario funcionario = new Funcionario("admin","1234");
                adicionarFuncionario(funcionario);

                System.out.println("Funcionario padrao inserido com sucesso na tabela'funcionario'.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter os funcionarioPadrao do banco de dados.");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public Funcionario consultarFuncionario(String login, String senha)  {
        try {
            String sql = "SELECT * FROM funcionario WHERE login = '" + login + "'AND senha = '" + senha + "'";
            ResultSet resultado = dataSource.executarSelect(sql);
    
            if (resultado.next()) {
                // Extrair os dados do ResultSet e criar um objeto funcionario
                int id_funcionario = resultado.getInt("id_funcionario");
                String login_funcionario = resultado.getString("login");
                String senha_funcionario = resultado.getString("senha");
    
                Funcionario funcionario = new Funcionario(id_funcionario, login_funcionario, senha_funcionario);
                return funcionario;
            } else {
                // funcionario não encontrado
                return null;
            }
        } catch (Exception e) {
            // Tratar a exceção e retornar um valor padrão (pode ser null) em caso de erro
            System.err.println("Erro ao consultar funcionario no banco de dados: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void adicionarFuncionario(Funcionario funcionario)  {
        try {
            String login = funcionario.getLogin();
            String senha = funcionario.getSenha();
            
            // Verificar se o Funcionario já existe
            Funcionario FuncionarioExistente = consultarFuncionario(login, senha);          
            if (FuncionarioExistente != null) {
                System.out.println("O Funcionario com nome '" + login + "' já existe no banco de dados.");
                return; // Encerra o método, não adicionando um novo Funcionario
            }
            
            String sql = "INSERT INTO funcionario (login, senha) VALUES ('" + login + "', '" + senha + "')";
            dataSource.executarQueryGeral(sql);
            System.out.println("Funcionario adicionado com sucesso: " + login);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar Funcionario no banco de dados: " + e.getMessage());
        }
    }

    
    @Override
    public ArrayList<Funcionario> obterFuncionarioPadrao() {
        ArrayList<Funcionario> funcionarioPadrao = new ArrayList<>();
        try {
            String sql = "SELECT * FROM funcionario";
            ResultSet resultado = dataSource.executarSelect(sql);
            while (resultado.next()) {
                int id_funcionario = resultado.getInt("id_funcionario");
                String nome = resultado.getString("login");
                String senha = resultado.getString("senha");
                Funcionario funcionario = new Funcionario(id_funcionario, nome, senha);
                funcionarioPadrao.add(funcionario);
            }
            resultado.close();
        } catch (Exception e) {
            System.out.println("Erro ao obter o funcionario do banco de dados");
        }
        return funcionarioPadrao;
    }  
}
