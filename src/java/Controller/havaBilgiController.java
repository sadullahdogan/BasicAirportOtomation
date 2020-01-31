/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.HavaalaniDao;
import Entitiy.Havaalani;
import Entitiy.havaalaniVeri;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ABRA
 */
@Named
@SessionScoped
public class havaBilgiController implements Serializable {

    private Havaalani h;
    private havaalaniVeri bilgi;
    private HavaalaniDao hDao;
    List<Havaalani> liste;

    public List<Havaalani> getListe() {
        liste=gethDao().getHavaalani(1, 1000);
        return liste;
    }

    public void setListe(List<Havaalani> liste) {
        this.liste = liste;
    }

    public havaBilgiController() {
    }

    public Havaalani getH() {
        if(h==null){
            h= new Havaalani();
            h.setHavaalaniId(13);
        }
        return h;
    }

    public void setH(Havaalani h) {
        this.h = h;
    }

    public havaalaniVeri getBilgi() {
        this.listele();
        
        return bilgi;
    }

    public void setBilgi(havaalaniVeri bilgi) {
        this.bilgi = bilgi;
    }

    public HavaalaniDao gethDao() {
        if(hDao==null)
            hDao= new HavaalaniDao();
        return hDao;
    }
    public void listele(){
    this.bilgi=gethDao().getBilgi(h.getHavaalaniId());
    
    }

    

}
