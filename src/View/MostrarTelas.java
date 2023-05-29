package View;

public class MostrarTelas {


    public void mostrarTelaInicial(){
        System.out.println("\n\n==========================================");
        System.out.println("\t  Mercadinho do Patrick\n");
        System.out.println("   Você é um consumidor ou funcionario?");
        System.out.println("\n\t[1]   Consumidor   [1]");
        System.out.println("\t[2]  Funcionario   [2]");
        System.out.println("\t[3]      SAIR      [3]\n");
    }

    public void MostrarTelaCliente(){
        System.out.println("\n\n==========================================\n");
        System.out.println("\t     TELA DO CLIENTE\n");
        System.out.println("    [ C ]    Comprar Produto     [ C ]");
        System.out.println("    [ T ] Ver todos os produtos  [ T ]");
        System.out.println("    [ P ]  Pesquisar pelo nome   [ P ]");
        System.out.println("    [ V ] Voltar../ Tela Inicial [ V ]");
        System.out.println("    [ 3 ]        SAIR            [ 3 ]\n");
    }
    
    public void MostrarTelaFuncionario(){
        System.out.println("\n\n==========================================\n");
        System.out.println("\t  TELA DO FUNCIONARIO\n");
        System.out.println("    [ A ]   Adicionar um produto    [ A ]");
        System.out.println("    [ R ]    Remover um produto     [ R ]");
        System.out.println("    [ T ]   Ver todos os produtos   [ T ]");
        System.out.println("    [ P ]    Pesquisar pelo nome    [ P ]");
        System.out.println("    [ M ]   Mostrar o registro      [ M ]");
        System.out.println("    [ L ]     Limpar registro       [ L ]");
        System.out.println("    [ V ]   Voltar../ Tela Inicial  [ V ]");
        System.out.println("    [ 3 ]          SAIR             [ 3 ]\n");
    }
}
