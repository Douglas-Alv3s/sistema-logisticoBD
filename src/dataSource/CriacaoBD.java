package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.DAOProduto;

public class CriacaoBD {
    
    String url = "jdbc:mysql://localhost:3306";
    String username = "root";
    String password = "";
    String nomeBancoDados = "sistema_logistico";
    Connection sqlConexao = null; // Responsavel pela ligação ao banco de dados
    Statement sqlInterpretador = null; // Responsavel pela execução dos comandos SQL
     
    
    public CriacaoBD() {
        DAOCreateDB();
        DAOCreateTB("CREATE TABLE cliente (id_cliente int PRIMARY KEY AUTO_INCREMENT, nome varchar(30), dinheiro decimal(10,2), gasto decimal(10,2) DEFAULT '0')", "cliente");
        DAOCreateTB("CREATE TABLE produto (id_produto int PRIMARY KEY AUTO_INCREMENT, nome varchar(30), valor decimal(10,2), quantidade int)", "produto");
        inserirProdutosPadrao(); // Insere os produtos vindo por padrão na Tabela Produto
        DAOCreateTB("CREATE TABLE funcionario (id_funcionario int PRIMARY KEY AUTO_INCREMENT, usuario varchar(30), senha varchar(30))", "funcionario");
        DAOCreateTB("CREATE TABLE compra (id_compra int PRIMARY KEY AUTO_INCREMENT, id_clienteFK int, id_produtoFK int)", "compra");
        
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
        System.out.println(url);
        
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
