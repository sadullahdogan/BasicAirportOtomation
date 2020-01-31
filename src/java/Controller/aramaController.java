/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.HavaalaniDao;
import Dao.ucusDao;
import Entitiy.Havaalani;
import Entitiy.ucus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ABRA
 */
@Named
@SessionScoped
public class aramaController implements Serializable {

    private ucusDao uDao;
    private List<ucus> liste;
    private Havaalani kalkisHavaalani;
    private Havaalani varisHavaalani;
    private List<Havaalani> listeHavaalani;
     private HavaalaniDao hDao;

    public List<Havaalani> getListeHavaalani() {
        this.listeHavaalani=this.gethDao().getHavaalani(1, 1000);
        return listeHavaalani;
    }

    public void setListeHavaalani(List<Havaalani> listeHavaalani) {
        this.listeHavaalani = listeHavaalani;
    }

    public HavaalaniDao gethDao() {
        if(this.hDao==null){
        this.hDao=new HavaalaniDao();
        }
        return hDao;
    }

   

    public ucusDao getuDao() {
        if (this.uDao == null) {
            this.uDao = new ucusDao();
        }
        return uDao;
    }

    public void listele() {
        this.liste = this.getuDao().find(this.getKalkisHavaalani().getHavaalaniId(), this.getVarisHavaalani().getHavaalaniId());

    }

    public List<ucus> getListe() {
        if (this.liste == null) {
            this.liste = new ArrayList();
        }

        return liste;
    }

    public void setListe(List<ucus> liste) {
        this.liste = liste;
    }

    public Havaalani getKalkisHavaalani() {
        if(this.kalkisHavaalani==null)
            this.kalkisHavaalani=new Havaalani();
        return kalkisHavaalani;
    }

    public void setKalkisHavaalani(Havaalani kalkisHavaalani) {
        this.kalkisHavaalani = kalkisHavaalani;
    }

    public Havaalani getVarisHavaalani() {
         if(this.varisHavaalani==null)
            this.varisHavaalani=new Havaalani();
        return varisHavaalani;
    }

    public void setVarisHavaalani(Havaalani varisHavaalani) {
        this.varisHavaalani = varisHavaalani;
    }

    

}
