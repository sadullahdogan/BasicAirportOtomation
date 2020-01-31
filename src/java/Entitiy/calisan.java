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
public class calisan {

    private String calisanAdi;
    private int calisanId;
    private int calisanMaas;
    private int calisanYas;
  
    private havaYoluSirketi sirket;

    public havaYoluSirketi getSirket() {
        if(sirket==null)
            sirket=new havaYoluSirketi();
        return sirket;
    }

    public void setSirket(havaYoluSirketi sirket) {
        this.sirket = sirket;
    }
    public calisan() {
    }

   

    public calisan(String calisanAdi, int calisanId, int calisanMaas, int calisanYas,havaYoluSirketi sirket) {
        this.calisanAdi = calisanAdi;
        this.calisanId = calisanId;
        this.calisanMaas = calisanMaas;
        this.calisanYas = calisanYas;
       
        this.sirket = sirket;
    }

 

  

    public String getCalisanAdi() {
        return calisanAdi;
    }

    public void setCalisanAdi(String calisanAdi) {
        this.calisanAdi = calisanAdi;
    }

    public int getCalisanId() {
        return calisanId;
    }

    public void setCalisanId(int calisanId) {
        this.calisanId = calisanId;
    }

    public int getCalisanMaas() {
        return calisanMaas;
    }

    public void setCalisanMaas(int calisanMaas) {
        this.calisanMaas = calisanMaas;
    }

    public int getCalisanYas() {
        return calisanYas;
    }

    public void setCalisanYas(int calisanYas) {
        this.calisanYas = calisanYas;
    }

}
