/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.HavaalaniDao;
import Entitiy.Havaalani;
import Entitiy.havaYoluSirketi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Sadullah
 */
@Named
@SessionScoped
public class HavaalaniController implements Serializable {

    private List<Havaalani> havaalaniList;
    private List<havaYoluSirketi> sirketList;
    private HavaalaniDao havaalaniDao;
    private Havaalani havaalani;
    private havaYoluSirketiController sirketController;
    private List<String> sirketIsim;
    private int seciliHavaAlanı;
    
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
        this.pageCount=(int)Math.ceil(this.getHavaalaniDao().count()/(double)pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    public int getSeciliHavaAlanı() {
        return seciliHavaAlanı;
    }

    public void setSeciliHavaAlanı(int seciliHavaAlanı) {
        this.seciliHavaAlanı = seciliHavaAlanı;
    }

    public List<String> getSirketIsim() {
        this.sirketIsim = getHavaalaniDao().getSirketler(seciliHavaAlanı);
        return sirketIsim;
    }

    public void seciliHavaalanı(int havaalanıId) {
        this.seciliHavaAlanı = havaalanıId;
    }

    public void setSirketIsim(List<String> sirketIsim) {
        this.sirketIsim = sirketIsim;
    }

    public havaYoluSirketiController getSirketController() {
        if (this.sirketController == null) {
            this.sirketController = new havaYoluSirketiController();
        }
        return sirketController;
    }

    public List<Havaalani> getHavaalaniList() {
        this.havaalaniList = getHavaalaniDao().getHavaalani(page,pageSize);
        return havaalaniList;
    }

    public void setHavaalaniList(List<Havaalani> havaalaniList) {
        this.havaalaniList = havaalaniList;
    }

    public List<havaYoluSirketi> getSirketList() {
        this.sirketList = getSirketController().getsDao().gethavaYoluSirketi(1,1000);
        return sirketList;
    }

    public void setSirketList(List<havaYoluSirketi> sirketList) {
        this.sirketList = sirketList;
    }

  
    public void clearForm() {
        this.havaalani = new Havaalani();

     
    }

    public void updateForm(Havaalani hav) {

        this.havaalani = hav;
        
    }

    public void delete(Havaalani hav) {

        this.getHavaalaniDao().delete(hav);
       
    }

    public void create() {

        this.getHavaalaniDao().insert(this.havaalani);
        
    }

    public void update() {

        this.getHavaalaniDao().update(this.havaalani);
       
    }

    public HavaalaniController() {

    }

 
    

    public HavaalaniDao getHavaalaniDao() {
        if (this.havaalaniDao == null) {
            this.havaalaniDao = new HavaalaniDao();
        }
        return havaalaniDao;
    }

    public void setHavaalaniDao(HavaalaniDao havaalaniDao) {
        this.havaalaniDao = havaalaniDao;
    }

    public Havaalani getHavaalani() {
        if (this.havaalani == null) {
            this.havaalani = new Havaalani();
        }
        return havaalani;
    }

    public void setHavaalani(Havaalani havaalani) {
        this.havaalani = havaalani;
    }

}
