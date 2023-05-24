package Controller.Funcionario.Funcionario_produto;

public class AdicionarProduto {
    private static RemoverProduto instance = null;

    static public RemoverProduto getInstance() {
        if (instance == null) {
            instance = new RemoverProduto();
            return instance;
        }
        return instance;
    }
    
}
