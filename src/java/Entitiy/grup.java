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
public class grup {

    private int grupId;
   private String grupAdi;

    public String getGrupAdi() {
        return grupAdi;
    }

    public void setGrupAdi(String grupAdi) {
        this.grupAdi = grupAdi;
    }

    public grup() {
    }

    public grup(int grupId, String grupAdi) {
        this.grupId = grupId;
        this.grupAdi = grupAdi;
    }

    public int getGrupId() {
        return grupId;
    }

    public void setGrupId(int grupId) {
        this.grupId = grupId;
    }

   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.grupId;
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
        final grup other = (grup) obj;
        if (this.grupId != other.grupId) {
            return false;
        }
        return true;
    }

}
