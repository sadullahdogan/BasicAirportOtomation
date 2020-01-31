/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.ucakDao;
import Entitiy.havaYoluSirketi;
import Entitiy.ucak;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author BUSE
 */
@Named
@SessionScoped
public class ucakController implements Serializable {

    private ucak ucak;
    private List<ucak> liste;
    private ucakDao uDao;
    private List<havaYoluSirketi> listeHs;

    havaYoluSirketiController hsController;
     private int page=1;
    private int pageSize=10;
    private int pageCount;
    
    public void next(){
        if(this.page==this.getPageCount())
            this.page=1;
        else
    this.page++;
            
            }
    public void previous(){
        if(this.page==1)
            this.page=this.getPageCount();
        else
    this.page--;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount=(int)Math.ceil(this.getuDao().count()/(double)pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    public List<havaYoluSirketi> getListeHs() {
        this.listeHs = getHsController().getListe();
        return listeHs;
    }

    public void setListeHs(List<havaYoluSirketi> listeHs) {

        this.listeHs = listeHs;
    }

    public havaYoluSirketiController getHsController() {
        if (hsController == null) {
            this.hsController = new havaYoluSirketiController();
        }
        return hsController;
    }

    public void setHsController(havaYoluSirketiController hsController) {
        this.hsController = hsController;
    }

    public void update() {
        this.getuDao().guncelle(this.ucak);
      
    }

    public ucakController() {

    }

    public void updateForm(ucak uc) {
        this.ucak = uc;

       
    }

    public void delete(ucak uc) {
        this.getuDao().sil(uc);
        
    }

    public void create() {
        this.getuDao().insert(this.ucak);

       
    }

    public ucak getUcak() {
        if (ucak == null) {
            this.ucak = new ucak();
        }
        return ucak;
    }

    public void setUcak(ucak ucak) {
        this.ucak = ucak;
    }

    public List<ucak> getListe() {
        if (liste == null) {
            this.liste = new ArrayList();
        }
        this.liste = getuDao().getUcak(page,pageSize);
        return liste;
    }
    public void clearForm(){
    this.ucak=new ucak();
       
    }

    public void setListe(List<ucak> liste) {
        this.liste = liste;
    }

    public ucakDao getuDao() {
        if (uDao == null) {
            this.uDao = new ucakDao();
        }
        return uDao;
    }

    public void setuDao(ucakDao uDao) {
        this.uDao = uDao;
    }

}
