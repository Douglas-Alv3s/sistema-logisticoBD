package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Compra;
import Model.Produto;
import dataSource.IDataSource;
import exceptions.ErroBDException;

public class DAOProduto implements IDAOGenerico<Produto>, IDAOProduto {
    private IDataSource dataSource;
    
    public DAOProduto(IDataSource dataSource) throws ErroBDException {
        this.dataSource = dataSource;

    }

    @Override
    public Produto consultar(String nome) throws ErroBDException {
        try {
            String sql = "SELECT * FROM produto WHERE nome = '" + nome + "'";
            ResultSet resultado = dataSource.executarSelect(sql);
            
            if (resultado.next()) {
                int id = resultado.getInt("id");
                String produtoNome = resultado.getString("nome");
                float valor = resultado.getFloat("valor");
                int quantidade = resultado.getInt("quantidade");
                
                Produto produto = new Produto(produtoNome, valor, quantidade);
                produto.setId(id);
                
                return produto;
            } else {
                // Produto não encontrado
                return null;
            }
        } catch (Exception e) {
            throw new ErroBDException("Erro ao consultar produto no banco de dados", e);
        }
    }

    @Override
    public void adicionar(Produto produto) throws ErroBDException {
        try {
            String nome = produto.getNome();
            
            // Verificar se o produto já existe
            Produto produtoExistente = consultar(nome);          
            if (produtoExistente != null) {
                System.out.println("O produto com nome '" + nome + "' já existe no banco de dados.");
                return; // Encerra o método, não adicionando um novo produto
            }

            String sql = "INSERT INTO produto (nome, valor, quantidade) VALUES ('" + produto.getNome() + "', " + produto.getValor() + ", " + produto.getQuantidade() + ")";
            dataSource.executarQueryGeral(sql);
        } catch (Exception e) {
            throw new ErroBDException("Erro ao adicionar produto no banco de dados", e);
        }
    }

    @Override
    public void remover(String nome) throws ErroBDException {
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
            throw new ErroBDException("Erro ao remover produto do banco de dados", e);
        }
    }

    @Override
    public void alterar(String chaveAntiga, Produto dadosNovos) throws ErroBDException {
        try {
            String sql = "UPDATE produto SET nome = '" + dadosNovos.getNome() + "', valor = " + dadosNovos.getValor() + ", quantidade = " + dadosNovos.getQuantidade() + " WHERE nome = '" + chaveAntiga + "'";
            dataSource.executarQueryGeral(sql);
        } catch (Exception e) {
            throw new ErroBDException("Erro ao alterar produto no banco de dados", e);
        }
    }

    @Override
    public ArrayList<Produto> obterTodos() throws ErroBDException {
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM produto";
            ResultSet resultado = dataSource.executarSelect(sql);
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                float valor = resultado.getFloat("valor");
                int quantidade = resultado.getInt("quantidade");
                Produto produto = new Produto(nome, valor, quantidade);
                produto.setId(id);
                produtos.add(produto);
            }
            resultado.close();
        } catch (Exception e) {
            throw new ErroBDException("Erro ao obter todos os produtos do banco de dados", e);
        }
        return produtos;
    }

    @Override
    public void inserirProdutosPadrao() throws ErroBDException {
        // Verificar se os produtos já foram inseridos
        try {
            ArrayList<Produto> produtos = obterTodos();
            if (!produtos.isEmpty()) {
                System.out.println("Os produtos iniciais já foram inseridos anteriormente.");
                return; // Não é necessário inserir novamente
            }
        } catch (ErroBDException e) {
            System.out.println("Erro ao obter os produtos do banco de dados.");
            e.printStackTrace();
            return;
        }

        // Inserir os produtos iniciais na tabela produto
        try {
            Produto arroz = new Produto("arroz", 2.5f, 3);
            Produto feijao = new Produto("feijao", 2.5f, 5);
            Produto cafe = new Produto("cafe", 2.5f, 7);
            Produto macarrao = new Produto("macarrao", 2.5f, 7);
            Produto cuscuz = new Produto("cuscuz", 2.5f, 7);
            Produto tomate = new Produto("tomate", 2.5f, 7);

            adicionar(arroz);
            adicionar(feijao);
            adicionar(cafe);
            adicionar(macarrao);
            adicionar(cuscuz);
            adicionar(tomate);

            System.out.println("Produtos iniciais inseridos com sucesso na tabela 'produto'.");
        } catch (ErroBDException e) {
            System.out.println("Erro ao inserir os produtos iniciais na tabela 'produto'.");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Compra> obterCompraProduto(Produto produto) throws ErroBDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterCompraProduto'");
    }
}
