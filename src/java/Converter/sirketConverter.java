/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Dao.havaYoluSirketiDao;
import Entitiy.havaYoluSirketi;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ABRA
 */
@FacesConverter(value="sirketConverter")
public class sirketConverter implements Converter{
    private havaYoluSirketiDao hDao;

    public havaYoluSirketiDao gethDao() {
        if(hDao==null)
            hDao= new havaYoluSirketiDao();
        return hDao;
    }

    public void sethDao(havaYoluSirketiDao hDao) {
        this.hDao = hDao;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.gethDao().find(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        havaYoluSirketi hs=(havaYoluSirketi)arg2;
        return hs.getSirketId()+"";
        
    
    }
    
}
