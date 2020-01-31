/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.dosyaDao;
import Dao.userDao;
import Entitiy.dosya;
import Entitiy.grup;
import Entitiy.user;
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
public class userController implements Serializable {

    private List<user> liste;
    private userDao uDao;
    private user user;
    private List<grup> grupList;
    private grupController gController;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    private dosyaDao dDao;
    private List<dosya> dosyaList;

    public dosyaDao getdDao() {
        if(dDao==null)
            dDao=new dosyaDao();
        return dDao;
    }

    public void setdDao(dosyaDao dDao) {
        this.dDao = dDao;
    }

    public List<dosya> getDosyaList() {
       this.dosyaList= this.getdDao().getDosya(1,1000);
        return dosyaList;
    }

    public void setDosyaList(List<dosya> dosyaList) {
        this.dosyaList = dosyaList;
    }
    

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
        this.pageCount = (int) Math.ceil(this.getuDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<grup> getGrupList() {
        this.grupList = getgController().getListe();
        return grupList;
    }

    public void setGrupList(List<grup> grupList) {
        this.grupList = grupList;
    }

    public grupController getgController() {
        if (gController == null) {
            gController = new grupController();
        }
        return gController;
    }

    public void setgController(grupController gController) {
        this.gController = gController;
    }

    public userDao getuDao() {
        if(uDao==null)
            uDao=new userDao();
        return uDao;
    }

    public void setuDao(userDao uDao) {
        this.uDao = uDao;
    }

    public user getUser() {
        if (user == null) {
            user = new user();
        }
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public userController() {
    }

    public List<user> getListe() {
        this.liste = getUDao().getUser(page, pageSize);
        return liste;
    }

    public void setListe(List<user> liste) {
        this.liste = liste;
    }

    public userDao getUDao() {
        if (uDao == null) {
            uDao = new userDao();
        }
        return uDao;
    }

    public void setUcDao(userDao ucDao) {
        this.uDao = ucDao;
    }

    public String clearForm() {
        this.user = new user();
        return "/secret/user?faces-redirect=true";

    }

    public String delete(user user) {
        getUDao().sil(user);
        return "/secret/user?faces-redirect=true";
    }

    public String create() {
        getUDao().insert(this.user);
        return "/secret/user?faces-redirect=true";
    }

    public String update() {
        getUDao().guncelle(this.user);
        user = new user();
        return "/secret/user?faces-redirect=true";
    }

    public String updateForm(user user) {
        this.user = user;
        return "/secret/user?faces-redirect=true";
    }

}
