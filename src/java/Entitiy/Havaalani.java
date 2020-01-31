/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitiy;

import java.util.List;

/**
 *
 * @author Sadullah
 */
public class Havaalani {

    private String havaalaniAdi;
    private String bulunduguSehir;
    private int havaalaniId;
    private List<havaYoluSirketi> sirketler;
    

    public Havaalani() {
    }

   
    

    public List<havaYoluSirketi> getSirketler() {
        return sirketler;
    }

    public void setSirketler(List<havaYoluSirketi> sirketler) {
        this.sirketler = sirketler;
    }
    

    public Havaalani(String havaalaniAdi, int havaalaniId, String bulunduguSehir) {
        this.havaalaniAdi = havaalaniAdi;
        this.havaalaniId = havaalaniId;
        this.bulunduguSehir = bulunduguSehir;

    }

    public String getHavaalaniAdi() {
        return havaalaniAdi;
    }

    public void setHavaalaniAdi(String havaalaniAdi) {
        this.havaalaniAdi = havaalaniAdi;
    }

    public int getHavaalaniId() {
        return havaalaniId;
    }

    public void setHavaalaniId(int havaalaniId) {
        this.havaalaniId = havaalaniId;
    }

    public String getBulunduguSehir() {
        return bulunduguSehir;
    }

    public void setBulunduguSehir(String bulunduguSehir) {
        this.bulunduguSehir = bulunduguSehir;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.havaalaniId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Havaalani other = (Havaalani) obj;
        if (this.havaalaniId != other.havaalaniId) {
            return false;
        }
        return true;
    }

}
