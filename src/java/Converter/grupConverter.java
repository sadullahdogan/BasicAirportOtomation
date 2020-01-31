/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Dao.grupDao;
import Entitiy.grup;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ABRA
 */
@FacesConverter(value="grupConverter")
public class grupConverter implements Converter{

    grupDao gDao;

    public grupDao getgDao() {
        if(this.gDao==null)
            this.gDao=new grupDao();
        return gDao;
    }

    public void setgDao(grupDao gDao) {
        this.gDao = gDao;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return getgDao().find(Integer.parseInt(value));
        
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        grup g=(grup)arg2;
        return g.getGrupId()+"";
    }
    
}
