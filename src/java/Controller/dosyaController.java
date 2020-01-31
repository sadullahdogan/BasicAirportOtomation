/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.dosyaDao;
import Entitiy.dosya;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author BUSE
 */
@Named
@SessionScoped
public class dosyaController implements Serializable {

    private List<dosya> liste;
    private dosyaDao dDao;
    private dosya dosya;
    private Part doc;
    private final String uploadTo = "/Users/ABRA/Downloads/deneme/";

    private int page = 1;
    private int pageSize = 5;
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
        this.pageCount = (int) Math.ceil(this.getdDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Part getDoc() {
        return doc;
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1)
                        .substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    public String getUploadTo() {
        return uploadTo;
    }

    public void upload() {

        try {
            InputStream input = doc.getInputStream();
            File f = new File(uploadTo + getFilename(doc));
            Files.copy(input, f.toPath());

            dosya = this.getDosya();
            dosya.setDosyaAdi(f.getName());
            dosya.setDosyaYolu(f.getParent());
            this.dDao.insert(dosya);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setDoc(Part doc) {
        this.doc = doc;
    }

    public List<dosya> getListe() {
        if (liste == null) {
            this.liste = new ArrayList();
        }
        this.liste = getdDao().getDosya(page, pageSize);
        return liste;
    }

    public void setListe(List<dosya> liste) {
        this.liste = liste;
    }

    public dosyaDao getdDao() {
        if (dDao == null) {
            this.dDao = new dosyaDao();
        }
        return dDao;
    }

    public void setdDao(dosyaDao dDao) {
        this.dDao = dDao;
    }

    public dosya getDosya() {

        if (this.dosya == null) {
            this.dosya = new dosya();
        }
        return dosya;
    }

    public void setDosya(dosya dosya) {
        this.dosya = dosya;
    }

    public String clearForm() {
        this.dosya = new dosya();
        return "/secret/dosya?faces-redirect=true";

    }

    public String delete(dosya ds) {
        getdDao().sil(ds);
        try {
            File f = new File(getUploadTo() + ds.getDosyaAdi());
            Files.delete(f.toPath());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return "/secret/dosya?faces-redirect=true";
    }

    public String create() {
        getdDao().insert(this.dosya);
        return "/secret/dosya?faces-redirect=true";
    }

    public String update() {
        getdDao().guncelle(this.dosya);
        dosya = new dosya();
        return "/secret/dosya?faces-redirect=true";
    }

    public String updateForm(dosya ds) {
        this.dosya = ds;
        return "/secret/dosya?faces-redirect=true";
    }

}
