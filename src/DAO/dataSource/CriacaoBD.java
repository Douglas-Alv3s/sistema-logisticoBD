package DAO.dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.DAOFuncionario;
import DAO.DAOProduto;

public class CriacaoBD{
    
    String url = "jdbc:mysql://localhost:3306";
    String username = "root";
    String password = "";
    String nomeBancoDados = "sistema_logistico";
    Connection sqlConexao = null; // Responsavel pela ligação ao banco de dados
    Statement sqlInterpretador = null; // Responsavel pela execução dos comandos SQL
     
    
    public CriacaoBD() {
        DAOCreateDB();

        // Cria a tabela funcionario
        DAOCreateTB("CREATE TABLE funcionario (id_funcionario int PRIMARY KEY AUTO_INCREMENT, login varchar(30), senha varchar(30))", "funcionario");
        inserirFuncionarioPadrao(); // Insere o funcionario padrão.

        // Cria a tabela cliente
        DAOCreateTB("CREATE TABLE cliente (id_cliente int PRIMARY KEY AUTO_INCREMENT, nome varchar(30), dinheiro decimal(10,2), gasto decimal(10,2) DEFAULT '0', id_funcionarioFK int, FOREIGN KEY (id_funcionarioFK) REFERENCES funcionario (id_funcionario))", "cliente");
        
        // Cria a tabela produto
        DAOCreateTB("CREATE TABLE produto (id_produto int PRIMARY KEY AUTO_INCREMENT, nome varchar(30), valor decimal(10,2), quantidade int, id_funcionarioFK int, FOREIGN KEY (id_funcionarioFK) REFERENCES funcionario (id_funcionario))", "produto");
        inserirProdutosPadrao(); // Insere os produtos vindo por padrão na Tabela Produto        

        // Cria a tabela do relacionamento muitos para muitos de cliente e produto.
        DAOCreateTB("CREATE TABLE compra (id_compra int PRIMARY KEY AUTO_INCREMENT, id_clienteFK int, id_produtoFK int, FOREIGN KEY (id_clienteFK) REFERENCES cliente (id_cliente), FOREIGN KEY (id_produtoFK) REFERENCES produto (id_produto))", "compra");

        
    }

    // Padrão de projeto Singleton pattern
    private static CriacaoBD istance = null;
    static public CriacaoBD getInstance(){
        if (istance == null){
            istance = new CriacaoBD();
        }
        return istance;
    }

    // Criação do banco de dados
    public void DAOCreateDB(){
        try {
            Connection sqlConexao = DriverManager.getConnection(url, username, password);
            Statement sqlInterpretador = sqlConexao.createStatement();
            // Verifica se o banco de dados já existe
            String checkDatabaseQuery = "SHOW DATABASES LIKE '" + nomeBancoDados + "'";
            ResultSet resultSet = sqlInterpretador.executeQuery(checkDatabaseQuery);
            if (resultSet.next()) {
                // System.out.println("O banco de dados " + nomeBancoDados + " já existe.");
            } else {
                // Cria o banco de dados apenas se ele não existir
                String createDatabaseQuery = "CREATE DATABASE " + nomeBancoDados;
                sqlInterpretador.executeUpdate(createDatabaseQuery);
                System.out.println("Banco de dados " + nomeBancoDados + " criado com sucesso.");
            }
            url = "jdbc:mysql://localhost:3306/"+nomeBancoDados;
            
            resultSet.close();
            sqlInterpretador.close();
            sqlConexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Criação das tabelas
    public void DAOCreateTB(String comandoTable, String nomeTable){
        String createTableQuery = comandoTable;
        
        try (Connection sqlConexao = DriverManager.getConnection(url, username, password);
            Statement sqlInterpretador = sqlConexao.createStatement()) {
            
            // Verifica se a tabela já existe
            String checkTableQuery = "SHOW TABLES LIKE '" + nomeTable + "'";
            ResultSet resultSet = sqlInterpretador.executeQuery(checkTableQuery);

            if (resultSet.next()) {
                // System.out.println("A tabela '"+nomeTable+"' já existe. Não é necessário criar.");
            } else {
                sqlInterpretador.executeUpdate(createTableQuery);
                System.out.println("Tabela '"+nomeTable+"' criada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao criar ou verificar a tabela '"+nomeTable+"'.");
            e.printStackTrace();
        }
    }

    private void inserirProdutosPadrao() {
        MySQLDataSource dataSource = MySQLDataSource.getInstance(); // Responsavel pela ligação com o banco de dados
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            DAOProduto daoProduto = new DAOProduto(dataSource); // Substitua 'connection' pelo seu objeto real
            daoProduto.inserirProdutosPadrao();
            connection.close();
        } catch (Exception e) {
            System.out.println("Erro ao inserir os produtos iniciais na tabela 'produto'.");
            e.printStackTrace();
        }
    }

    private void inserirFuncionarioPadrao() {
        MySQLDataSource dataSource = MySQLDataSource.getInstance(); // Responsavel pela ligação com o banco de dados
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            DAOFuncionario daoFuncionario = new DAOFuncionario(dataSource); // Substitua 'connection' pelo seu objeto real
            daoFuncionario.inserirFuncionarioPadrao();
            connection.close();
        } catch (Exception e) {
            System.out.println("Erro o funcionario padrão na tabela 'funcionario'.");
            e.printStackTrace();
        }
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomeBancoDados() {
        return nomeBancoDados;
    }

    public void setNomeBancoDados(String nomeBancoDados) {
        this.nomeBancoDados = nomeBancoDados;
    }
}
