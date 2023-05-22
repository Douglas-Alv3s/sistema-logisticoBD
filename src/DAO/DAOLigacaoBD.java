package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOLigacaoBD {
    
    String url = "jdbc:mysql://localhost:3306";
    String username = "root";
    String password = "";
    String nomeBancoDados = "sistema_logistico";
    String createTableQuery;
    

    // Padrão de projeto Singleton pattern
    private static DAOLigacaoBD istance = null;
    
    public DAOLigacaoBD() {
        DAOCreateDB();
        DAOCreateTB("CREATE TABLE cliente (id int PRIMARY KEY AUTO_INCREMENT, nome varchar(30), dinheiro decimal(10,2), gasto decimal(10,2) DEFAULT '0')", "cliente");
        DAOCreateTB("CREATE TABLE produto (id int PRIMARY KEY AUTO_INCREMENT, nome varchar(30), valor decimal(10,2), quantidade int)", "produto");
        DAOCreateTB("CREATE TABLE funcionario (id int PRIMARY KEY AUTO_INCREMENT, usuario varchar(30), senha varchar(30))", "funcionario");
    }

    static public DAOLigacaoBD getInstance(){
        if (istance == null){
            istance = new DAOLigacaoBD();
        }
        return istance;
    }

    // Criação do banco de dados
    public void DAOCreateDB(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            // Verifica se o banco de dados já existe
            String checkDatabaseQuery = "SHOW DATABASES LIKE '" + nomeBancoDados + "'";
            ResultSet resultSet = statement.executeQuery(checkDatabaseQuery);
            if (resultSet.next()) {
                System.out.println("O banco de dados " + nomeBancoDados + " já existe.");
            } else {
                // Cria o banco de dados apenas se ele não existir
                String createDatabaseQuery = "CREATE DATABASE " + nomeBancoDados;
                statement.executeUpdate(createDatabaseQuery);
                System.out.println("Banco de dados " + nomeBancoDados + " criado com sucesso.");
            }
            url = "jdbc:mysql://localhost:3306/"+nomeBancoDados;
            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Criação da tabela clientes
    public void DAOCreateTB(String comandoTable, String nomeTable){
        String createTableQuery = comandoTable;
        System.out.println(url);
        
        try (Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement()) {
            
            // Verifica se a tabela já existe
            String checkTableQuery = "SHOW TABLES LIKE '" + nomeTable + "'";
            ResultSet resultSet = statement.executeQuery(checkTableQuery);

            if (resultSet.next()) {
                System.out.println("A tabela '"+nomeTable+"' já existe. Não é necessário criar.");
            } else {
                statement.executeUpdate(createTableQuery);
                System.out.println("Tabela '"+nomeTable+"' criada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao criar ou verificar a tabela '"+nomeTable+"'.");
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
