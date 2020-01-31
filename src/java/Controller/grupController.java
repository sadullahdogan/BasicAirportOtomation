/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.grupDao;
import Entitiy.grup;
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
public class grupController implements Serializable {

    private List<grup> liste;
    private grupDao gDao;
    private grup grup;

    public grup getGrup() {
        if (this.grup == null) {
            this.grup = new grup();
        }

        return grup;
    }

    public void setGrup(grup grup) {
        this.grup = grup;
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
        this.pageCount = (int) Math.ceil(this.getgDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<grup> getListe() {
        if (liste == null) {
            this.liste = new ArrayList();
        }
        this.liste = getgDao().getGrup(page, pageSize);
        return liste;
    }

    public void setListe(List<grup> liste) {
        this.liste = liste;
    }

    public grupDao getgDao() {
        if (gDao == null) {
            this.gDao = new grupDao();
        }
        return gDao;
    }

    public void clearForm() {
        this.grup = new grup();

    }

    public void delete(grup gr) {
        getgDao().sil(gr);

    }

    public void create() {
        
        getgDao().insert(this.grup);

    }

    public void update() {
        getgDao().guncelle(this.grup);
        

    }

    public void updateForm(grup gr) {
        this.grup = gr;

    }

}
