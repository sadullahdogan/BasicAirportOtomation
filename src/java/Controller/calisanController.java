/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.calisanDao;
import Dao.havaYoluSirketiDao;
import Entitiy.calisan;
import Entitiy.havaYoluSirketi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Buse
 */
@Named
@SessionScoped
public class calisanController implements Serializable {

    private List<calisan> liste;
    private calisanDao cDao;
    private calisan calisan;
    private List<havaYoluSirketi> listeHs;
    havaYoluSirketiDao hDao;

    public havaYoluSirketiDao gethDao() {
        if(hDao==null)
            hDao=new havaYoluSirketiDao();
        return hDao;
    }

    public void sethDao(havaYoluSirketiDao hDao) {
        this.hDao = hDao;
    }

   

    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }

    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
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
        this.pageCount = (int) Math.ceil(this.getcDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<havaYoluSirketi> getListeHs() {
        this.listeHs = this.gethDao().gethavaYoluSirketi(1,1000);
        return listeHs;
    }

    public void setListeHs(List<havaYoluSirketi> listeHs) {

        this.listeHs = listeHs;
    }

 

    public void clearForm() {
        this.calisan = new calisan();
        

    }

    public void delete(calisan cl) {
        
        getcDao().sil(cl);
        
    }

    public void create() {
        getcDao().insert(this.calisan);
        
    }

    public void update() {
        getcDao().guncelle(this.calisan);
        
    }

    public void updateForm(calisan cl) {
        this.calisan = cl;
        
    }

    public calisan getCalisan() {
        if (this.calisan == null) {
            this.calisan = new calisan();
        }
        return calisan;
    }

    public void setCalisan(calisan calisan) {
        this.calisan = calisan;
    }

    public calisanController() {
        //cDao=new calisanDao();

    }

    public List<calisan> getListe() {
        if (liste == null) {
            this.liste = new ArrayList();
        }
        this.liste = getcDao().getCalisan(page, pageSize);
        return liste;
    }

    public void setListe(List<calisan> liste) {
        this.liste = liste;
    }

    public calisanDao getcDao() {
        if (cDao == null) {
            this.cDao = new calisanDao();
        }
        return cDao;
    }

    public void setcDao(calisanDao cDao) {
        this.cDao = cDao;
    }

}
