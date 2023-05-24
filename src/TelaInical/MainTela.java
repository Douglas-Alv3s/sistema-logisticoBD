package TelaInical;

import View.Tela;
import dataSource.CriacaoBD;

public class MainTela {
    public static void main(String[] args) {
        System.out.println("Helloy problemas!!!");
        CriacaoBD.getInstance();
        
        Tela.getInstance().telaInicial();


    }
}