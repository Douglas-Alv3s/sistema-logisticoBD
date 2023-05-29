package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.InterfaceDAO.IDAOGenerico;
import DAO.InterfaceDAO.IDAOProduto;
import DAO.dataSource.MySQLDataSource;
import Model.Produto;

public class DAOProduto implements IDAOGenerico<Produto>, IDAOProduto {
    private MySQLDataSource dataSource;
    
    public DAOProduto(MySQLDataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public Produto consultar(String nome) {
        try {
            String sql = "SELECT * FROM produto WHERE nome = '" + nome + "'";
            ResultSet resultado = dataSource.executarSelect(sql);
            
            if (resultado.next()) {
                int id_produto = resultado.getInt("id_produto");
                String produtoNome = resultado.getString("nome");
                float valor = resultado.getFloat("valor");
                int quantidade = resultado.getInt("quantidade");
                
                Produto produto = new Produto(id_produto, produtoNome, valor, quantidade);
                
                return produto;
            } else {
                // Produto não encontrado
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar produto no banco de dados");
            return null;
        }
    }

    @Override
    public void adicionar(Produto produto) {
        try {
            String nome = produto.getNome();
            
            // Verificar se o produto já existe
            Produto produtoExistente = consultar(nome);          
            if (produtoExistente != null) {
                System.out.println("O produto com nome '" + nome + "' já existe no banco de dados.");
                return ; // Encerra o método, não adicionando um novo produto
            }

            String sql = "INSERT INTO produto (nome, valor, quantidade, id_funcionarioFK) VALUES ('" + produto.getNome() + "', " + produto.getValor() + ", " + produto.getQuantidade() + ", '1')";
            dataSource.executarQueryGeral(sql);

        } catch (Exception e) {
            System.out.println("Erro ao adicionar produto no banco de dados");
        }
    }

    @Override
    public void remover(String nome) {
        try {
            // Verificar se o produto já existe
            Produto produtoExistente = consultar(nome);
                       
            if (produtoExistente == null) {
                System.out.println("O produto com nome '" + nome + "' não existe no banco de dados. Impossivel remover.");
                return; // Encerra o método, não adicionando um novo produto
            }

            String sql = "DELETE FROM produto WHERE nome = '" + nome + "'";
            dataSource.executarQueryGeral(sql);
        } catch (Exception e) {
            System.out.println("Erro ao remover produto do banco de dados");
        }
    }

    @Override
    public void alterar(Produto dadosAntigo, Produto dadosNovos) {
        try {
            String sql = "UPDATE produto SET nome = '" + dadosNovos.getNome() + "', valor = " + dadosNovos.getValor() + ", quantidade = " + dadosNovos.getQuantidade() + " WHERE nome = '" + dadosAntigo.getNome() + "'";
            dataSource.executarQueryGeral(sql);
        } catch (Exception e) {
            System.out.println("Erro ao alterar produto no banco de dados");
        }
    }

    @Override
    public ArrayList<Produto> obterTodos() {
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM produto";
            ResultSet resultado = dataSource.executarSelect(sql);
            while (resultado.next()) {
                int id_produto = resultado.getInt("id_produto");
                String nome = resultado.getString("nome");
                float valor = resultado.getFloat("valor");
                int quantidade = resultado.getInt("quantidade");
                Produto produto = new Produto(nome, valor, quantidade);
                produto.setId_produto(id_produto);
                produtos.add(produto);
            }
            resultado.close();
        } catch (Exception e) {
            System.out.println("Erro ao obter todos os produtos do banco de dados");
        }
        return produtos;
    }

    @Override
    public void inserirProdutosPadrao() {
        // Verificar se os produtos já foram inseridos
        try {
            ArrayList<Produto> produtos = obterTodos();
            if (!produtos.isEmpty()) {
                System.out.println("Os produtos iniciais já foram inseridos anteriormente.");
                return; // Não é necessário inserir novamente
            }else{
                
                // Inserir os produtos iniciais na tabela produto
                Produto arroz = new Produto("arroz", 5.49f, 15);
                Produto feijao = new Produto("feijao", 5f, 15);
                Produto cafe = new Produto("cafe", 8f, 15);
                Produto macarrao = new Produto("macarrao", 4f, 15);
                Produto cuscuz = new Produto("cuscuz", 2.5f, 15);
                Produto tomate = new Produto("tomate", 1.75f, 15);

                adicionar(arroz);
                adicionar(feijao);
                adicionar(cafe);
                adicionar(macarrao);
                adicionar(cuscuz);
                adicionar(tomate);

                System.out.println("Produtos iniciais inseridos com sucesso na tabela 'produto'.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao obter os produtos do banco de dados.");
            return;
        }
    }

}
