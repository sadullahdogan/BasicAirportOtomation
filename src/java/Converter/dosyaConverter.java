/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Dao.dosyaDao;
import Entitiy.dosya;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ABRA
 */
@FacesConverter(value="dosyaConverter")
public class dosyaConverter implements Converter{
private dosyaDao dDao;

    public dosyaDao getdDao() {
        if(dDao==null)
            dDao= new dosyaDao();
        return dDao;
    }

    public void setdDao(dosyaDao dDao) {
        this.dDao = dDao;
    }
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getdDao().find(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        dosya d=(dosya)arg2;
        return d.getDosyaId()+"";
    }
    
}
