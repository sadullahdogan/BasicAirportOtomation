/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.HavaalaniDao;
import Dao.ucusDao;
import Entitiy.Havaalani;
import Entitiy.havaYoluSirketi;
import Entitiy.ucus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author BUSE
 */
@Named
@SessionScoped
public class ucusController implements Serializable {

    private List<ucus> liste;
    private ucusDao ucDao;
    private HavaalaniDao hDao;
    private List<havaYoluSirketi> listeHs;
    private havaYoluSirketiController hsController;
    private List<Havaalani> listeHavaalani;
    private ucus ucus;
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
        this.pageCount = (int) Math.ceil(this.getUcDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public ucusController() {

    }

    public List<Havaalani> getListeHavaalani() {
        this.listeHavaalani = gethDao().getHavaalani(1, 1000);
        return listeHavaalani;
    }

    public void setListeHavaalani(List<Havaalani> listeHavaalani) {
        this.listeHavaalani = listeHavaalani;
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

    public HavaalaniDao gethDao() {
        if (this.hDao == null) {
            this.hDao = new HavaalaniDao();
        }
        return hDao;
    }

    public void sethDao(HavaalaniDao hDao) {
        this.hDao = hDao;
    }

    public void update() {
        if (this.ucus.getKalkisHavaAlani().getHavaalaniId() != this.ucus.getVarisHavaAlani().getHavaalaniId()) {
            this.getUcDao().guncelle(this.ucus);
        }
        else{
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hatalı Giriş"));
        }

    }

    public void updateForm(ucus ucus) {
        this.ucus = ucus;

    }

    public void delete(ucus ucus) {
        this.getUcDao().sil(ucus);

    }

    public void create() {
        if (this.ucus.getKalkisHavaAlani().getHavaalaniId() != this.ucus.getVarisHavaAlani().getHavaalaniId()) {
            this.getUcDao().insert(this.ucus);
        }
        else{
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hatalı Giriş"));
        }

    }

    public ucus getUcus() {
        if (ucus == null) {
            this.ucus = new ucus();
        }
        return ucus;
    }

    public void setUcus(ucus ucus) {
        if (ucus == null) {
            this.ucus = new ucus();
        }
        this.ucus = ucus;
    }

    public void clearForm() {
        this.ucus = new ucus();

    }

    public List<ucus> getListe() {

        this.liste = getUcDao().getUcus(page, pageSize);
        return liste;
    }

    public void setListe(List<ucus> liste) {
        this.liste = liste;
    }

    public ucusDao getUcDao() {
        if (ucDao == null) {
            this.ucDao = new ucusDao();
        }
        return ucDao;
    }

    public void setUcDao(ucusDao ucDao) {
        this.ucDao = ucDao;
    }

}
