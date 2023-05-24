package View;

import DAO.dataSource.CriacaoBD;

public class InicializadorMain {
    public static void main(String[] args) {
        CriacaoBD.getInstance();
        Tela.getInstance().telaInicial();
    }
}
