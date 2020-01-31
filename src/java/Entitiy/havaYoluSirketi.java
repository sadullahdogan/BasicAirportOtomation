/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitiy;

/**
 *
 * @author
 */
public class havaYoluSirketi {

    private int sirketId;
    private String sirketAdi;

    public havaYoluSirketi() {
    }

    public havaYoluSirketi(int sirketId, String sirketAdi) {
        this.sirketId = sirketId;
        this.sirketAdi = sirketAdi;
    }

    public int getSirketId() {
        return sirketId;
    }

    public void setSirketId(int sirketId) {
        this.sirketId = sirketId;
    }

    public String getSirketAdi() {
        return sirketAdi;
    }

    public void setSirketAdi(String sirketAdi) {
        this.sirketAdi = sirketAdi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.sirketId;
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
        final havaYoluSirketi other = (havaYoluSirketi) obj;
        if (this.sirketId != other.sirketId) {
            return false;
        }
        return true;
    }

}
