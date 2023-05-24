package Controller.Funcionario.Autenticar_Funcionario;

import View.Tela;

public class ResultadoAutenticacao extends AutenticadorFuncionario{
    
    // Padrão de projeto Singleton pattern
    private static ResultadoAutenticacao instance = null;
    static public ResultadoAutenticacao getInstance(){
        if(instance == null){
            instance = new ResultadoAutenticacao();
            return instance;
        }
        return instance;
    }

    public ResultadoAutenticacao() {
        if (autenticarFuncionario()){
            System.out.println("\n--------------------\nEntrando no sistema");
            Tela.getInstance().telaFuncionario();
        }else {
            System.out.println("Login Invalido... Voltando a tela inicial.");
            Tela.getInstance().telaInicial();
            
        }  
    }             
}
