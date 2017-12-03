/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Veiculo;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author ROBSON
 */
@Stateful
public class VeiculoDAO<TIPO> extends DAOGenerico<Veiculo> implements Serializable {

    public VeiculoDAO() {
        super();
        classePersistente = Veiculo.class;
        ordem = "marca"; //ordem padrão do DAO

    }

}
