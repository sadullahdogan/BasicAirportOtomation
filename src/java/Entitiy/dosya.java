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
public class dosya {

    private int dosyaId;
    private String dosyaAdi;
    private String dosyaYolu;

    public dosya() {
    }

    public dosya(int dosyaId, String dosyaAdi, String dosyaYolu) {
        this.dosyaId = dosyaId;
        this.dosyaAdi = dosyaAdi;
        this.dosyaYolu = dosyaYolu;
    }

    public int getDosyaId() {
        return dosyaId;
    }

    public void setDosyaId(int dosyaId) {
        this.dosyaId = dosyaId;
    }

    public String getDosyaAdi() {
        return dosyaAdi;
    }

    public void setDosyaAdi(String dosyaAdi) {
        this.dosyaAdi = dosyaAdi;
    }

    public String getDosyaYolu() {
        return dosyaYolu;
    }

    public void setDosyaYolu(String dosyaYolu) {
        this.dosyaYolu = dosyaYolu;
    }

}
