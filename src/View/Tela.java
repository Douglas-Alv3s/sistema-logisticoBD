package View;

public class Tela extends MostrarTelas{

    // Padrão de projeto Singleton pattern
    private static Tela istance = null;
    static public Tela getInstance(){
        if (istance == null){
            istance = new Tela();
        }
        return istance;
    }
    
    public void telaInicial(){
        mostrarTelaInicial();
        Entradas.getInstance().opcaoInicial();
    }

    public void telaCliente(){
        Entradas.getInstance().entradaCliente();
        // MostrarTelaCliente();
        Entradas.getInstance().opcaoCliente();
        

    }

    public void telaFuncionario(){
        MostrarTelaFuncionario();
        // funcionario.opcao();       
    }

}
