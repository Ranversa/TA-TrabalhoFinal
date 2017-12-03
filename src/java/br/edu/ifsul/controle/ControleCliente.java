/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ROBSON
 */
@Named(value = "controleCliente")
@SessionScoped
public class ControleCliente implements Serializable{
    
    @EJB
    private ClienteDAO dao;
    private Cliente objeto;
    private Boolean editando;

    public ControleCliente() {
        editando = false;
    }
    

    public String listar(){
        editando = false;
        return "/privado/cliente/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Cliente();
        editando = true;
    }
    
    public void alterar(Object id){
        try{
            objeto = (Cliente) dao.getObjectById(id);
            editando = true;
        }catch (Exception e){
            Util.mensagemErro("Error ao carregar objeto: "+
                    Util.getMensagemErro(e));
        }
    }
    public void excluir(Object id){
        try{
            objeto = (Cliente) dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        }catch (Exception e){
            Util.mensagemErro("Error ao remover objeto: "+
                    Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try{
            if (objeto.getId() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando=false;
        }catch(Exception e){
            Util.mensagemErro("Error ao persistir objeto: "+
                    Util.getMensagemErro(e));
        }
    }

    public ClienteDAO getDao() {
        return dao;
    }

    public void setDao(ClienteDAO dao) {
        this.dao = dao;
    }

    public Cliente getObjeto() {
        return objeto;
    }

    public void setObjeto(Cliente objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    
    
}
