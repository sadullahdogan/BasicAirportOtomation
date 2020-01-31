/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.havaYoluSirketiDao;
import Entitiy.havaYoluSirketi;
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
public class havaYoluSirketiController implements Serializable {

    private List<havaYoluSirketi> liste;
    private havaYoluSirketiDao sDao;
    private havaYoluSirketi havaYoluSirketi;
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
        this.pageCount=(int)Math.ceil(this.getsDao().count()/(double)pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    public havaYoluSirketi getHavaYoluSirketi() {

        if (havaYoluSirketi == null) {
            this.havaYoluSirketi = new havaYoluSirketi();
        }
        return havaYoluSirketi;

    }

    public void setHavaYoluSirketi(havaYoluSirketi havaYoluSirketi) {
        this.havaYoluSirketi = havaYoluSirketi;
    }

    public havaYoluSirketiController() {
    }

    public void update() {
        this.getsDao().guncelle(this.havaYoluSirketi);
        

    }

    public void updateForm(havaYoluSirketi havaYoluSirketi) {
        this.havaYoluSirketi = havaYoluSirketi;
       

    }

    public void delete(havaYoluSirketi havaYoluSirketi) {
        this.getsDao().sil(havaYoluSirketi);
        

    }

    public String create() {
        this.getsDao().insert(this.havaYoluSirketi);
        return "/secret/havayolusirketi?faces-redirect=true";

    }

    public List<havaYoluSirketi> getListe() {

        if (liste == null) {
            this.liste = new ArrayList();
        }
        this.liste = getsDao().gethavaYoluSirketi(page,pageSize);
        return liste;
    }

    public void setListe(List<havaYoluSirketi> liste) {
        this.liste = liste;
    }

    public havaYoluSirketiDao getsDao() {
        if (sDao == null) {
            this.sDao = new havaYoluSirketiDao();
        }
        return sDao;
    }
    public String clearForm(){
    this.havaYoluSirketi=new havaYoluSirketi();
    
       return "/secret/havayolusirketi?faces-redirect=true";
    }

    public void setsDao(havaYoluSirketiDao sDao) {
        this.sDao = sDao;
    }

}
