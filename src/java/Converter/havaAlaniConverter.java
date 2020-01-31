/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Dao.HavaalaniDao;
import Entitiy.Havaalani;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ABRA
 */
@FacesConverter(value="havaAlaniConverter")
public class havaAlaniConverter implements Converter{

    HavaalaniDao hDao;

    public HavaalaniDao gethDao() {
        if(hDao==null)
            hDao= new HavaalaniDao();
        return hDao;
    }

    public void sethDao(HavaalaniDao hDao) {
        this.hDao = hDao;
    }
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
      return  this.gethDao().find(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
      
                Havaalani h=(Havaalani)arg2;
                return h.getHavaalaniId()+"";
    }
    
}
