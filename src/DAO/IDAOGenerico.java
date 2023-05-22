package DAO;

import java.util.List;

public interface IDAOGenerico<Tipo> {
        public void adicionar(Tipo objeto);
        public void remover(String chave);
        public void alterar(String chaveAntiga, Tipo dadosNovos);
        public List<Tipo> obterTodos();
    }

