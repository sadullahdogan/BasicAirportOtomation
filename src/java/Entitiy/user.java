/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitiy;

import java.io.Serializable;

/**
 *
 * @author Sadullah
 */
public class user implements Serializable {

    private int userId;
    private String userName;
    private String password;
    private grup grup;
    private dosya profilResmi;

    public user() {
    }

    public user(int userId, String userName, String password, grup grup, dosya profilResmi) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.grup = grup;
        this.profilResmi = profilResmi;
    }

    public dosya getProfilResmi() {
        if (profilResmi == null) {
            profilResmi = new dosya();
        }
        return profilResmi;
    }

    public void setProfilResmi(dosya profilResmi) {
        this.profilResmi = profilResmi;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public grup getGrup() {
        if (grup == null) {
            grup = new grup();
        }
        return grup;
    }

    public void setGrup(grup grup) {
        this.grup = grup;
    }

}
