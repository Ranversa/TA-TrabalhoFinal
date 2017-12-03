/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cliente;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author ROBSON
 */
@Stateful
public class ClienteDAO<TIPO> extends DAOGenerico<Cliente> implements Serializable {

    public ClienteDAO() {
        super();
        classePersistente = Cliente.class;
        ordem = "nome";
    }

}
