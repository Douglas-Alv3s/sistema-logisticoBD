package DAO;

import java.util.List;

import exceptions.ErroBDException;

public interface IDAOGenerico<Tipo> {
        public void adicionar(Tipo objeto) throws ErroBDException;
        public void remover(String chave) throws ErroBDException;
        public void alterar(String chaveAntiga, Tipo dadosNovos) throws ErroBDException;
        public List<Tipo> obterTodos() throws ErroBDException;

    }

