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
public class ucus {

    private int ucusNo;
    private String kalkisSaati;
    private Havaalani kalkisHavaAlani;
    private Havaalani varisHavaAlani;
    private havaYoluSirketi sirket;

    public ucus() {
    }

    public ucus(String kalkisSaati, Havaalani kalkisHavaAlani, Havaalani varisHavaAlani, havaYoluSirketi sirket, int ucusNo) {
        this.ucusNo = ucusNo;
        this.kalkisSaati = kalkisSaati;
        this.kalkisHavaAlani = kalkisHavaAlani;
        this.varisHavaAlani = varisHavaAlani;
        this.sirket = sirket;
    }

    public Havaalani getKalkisHavaAlani() {
        if (kalkisHavaAlani == null) {
            kalkisHavaAlani = new Havaalani();
        }
        return kalkisHavaAlani;
    }

    public void setKalkisHavaAlani(Havaalani kalkisHavaAlani) {
        this.kalkisHavaAlani = kalkisHavaAlani;
    }

    public Havaalani getVarisHavaAlani() {
        if (varisHavaAlani == null) {
            varisHavaAlani = new Havaalani();
        }
        return varisHavaAlani;
    }

    public void setVarisHavaAlani(Havaalani varisHavaAlani) {
        this.varisHavaAlani = varisHavaAlani;
    }

    public havaYoluSirketi getSirket() {
        if (sirket == null) {
            sirket = new havaYoluSirketi();
        }
        return sirket;
    }

    public void setSirket(havaYoluSirketi sirket) {

        this.sirket = sirket;
    }

    public int getUcusNo() {
        return ucusNo;
    }

    public void setUcusNo(int ucusNo) {
        this.ucusNo = ucusNo;
    }

    public String getKalkisSaati() {
        

        return kalkisSaati;
    }

    public void setKalkisSaati(String kalkisSaati) {
        this.kalkisSaati = kalkisSaati;
    }

}
