/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.ItemOrcamento;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author ROBSON
 */
@Stateful
public class ItemOrcamentoDAO<TIPO> extends DAOGenerico<ItemOrcamento> implements Serializable {

    public ItemOrcamentoDAO() {
        super();
        classePersistente = ItemOrcamento.class;
    }

}
