/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converter;

import br.edu.ifsul.modelo.PecasMaoDeObra;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ROBSON
 */
@FacesConverter(value = "converterPeca")
public class ConverterPeca implements Converter, Serializable{

    @PersistenceContext(unitName = "Oficina-WebPU")
    private EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equals("selecione um registro")){
            return null;
        }
        return em.find(PecasMaoDeObra.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o == null){
            return  null;
        }
        PecasMaoDeObra obj = (PecasMaoDeObra) o;
        return obj.getId().toString();
    }
    
}
