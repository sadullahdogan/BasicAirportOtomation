/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitiy;

/**
 *
 * @author Sadullah
 */
public class ucak {

    
    private int ucakId;
    private int ucakKapasitesi;
    private String ucakModeli;
     private havaYoluSirketi sirket;

    public havaYoluSirketi getSirket() {
        if(sirket==null)
            sirket=new havaYoluSirketi();
        return sirket;
    }

    public void setSirket(havaYoluSirketi sirket) {
        this.sirket = sirket;
    }
    public ucak() {
    }

    public ucak(havaYoluSirketi sirket, int ucakId, int ucakKapasitesi, String ucakModeli) {
        this.sirket = sirket;
        this.ucakId = ucakId;
        this.ucakKapasitesi = ucakKapasitesi;
        this.ucakModeli = ucakModeli;
    }

  

    public int getUcakId() {
        return ucakId;
    }

    public void setUcakId(int ucakId) {
        this.ucakId = ucakId;
    }

    public int getUcakKapasitesi() {
        return ucakKapasitesi;
    }

    public void setUcakKapasitesi(int ucakKapasitesi) {
        this.ucakKapasitesi = ucakKapasitesi;
    }

    public String getUcakModeli() {
        return ucakModeli;
    }

    public void setUcakModeli(String ucakModeli) {
        this.ucakModeli = ucakModeli;
    }

}
