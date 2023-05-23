package DAO.InterfaceDAO;

import java.util.ArrayList;


import exceptions.ErroBDException;

public interface IDAOGenerico<Tipo> {
	    public Tipo consultar(String login) throws ErroBDException;
        public void adicionar(Tipo objeto) throws ErroBDException;
        public void remover(String chave) throws ErroBDException;
        public void alterar(Tipo chaveAntiga, Tipo dadosNovos) throws ErroBDException;
        public ArrayList<Tipo> obterTodos() throws ErroBDException;

    }

