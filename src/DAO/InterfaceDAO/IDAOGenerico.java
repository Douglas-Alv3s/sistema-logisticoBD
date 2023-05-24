package DAO.InterfaceDAO;

import java.util.ArrayList;


public interface IDAOGenerico<Tipo> {
	    public Tipo consultar(String login);
        public void adicionar(Tipo objeto);
        public void remover(String chave);
        public void alterar(Tipo chaveAntiga, Tipo dadosNovos);
        public ArrayList<Tipo> obterTodos();

    }

