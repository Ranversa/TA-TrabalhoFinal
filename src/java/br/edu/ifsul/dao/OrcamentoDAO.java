/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Orcamento;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author ROBSON
 */
@Stateful
public class OrcamentoDAO<TIPO> extends DAOGenerico<Orcamento> implements Serializable {

    public OrcamentoDAO() {
        super();
        classePersistente = Orcamento.class;
    }
     public Orcamento getObjectById(Integer id) throws Exception {
        Orcamento obj = (Orcamento) em.find(classePersistente, id);
        /*
        A linha abaixo realiza a inicialização da coleção para que 
        quando ela for editada na interface já esteja carregada, evitando assim 
        um erro de LazyInitializationException
        */
        obj.getItens().size();
        return obj;
    }      

}
