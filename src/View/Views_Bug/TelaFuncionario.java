package View.Views_Bug;

import PRODUTO.Produtos;
import PRODUTO.RemoverProduto;


public class TelaFuncionario extends Base{

    @Override
    public void adicionarProduto() {
        
        Produtos.getInstance().VerProdutos();
        Produtos.getInstance().adicionarProduto();
    }

    @Override
    public void removerComprar() {
        
        Produtos.getInstance().removerProduto(new RemoverProduto()); 
    } 
}