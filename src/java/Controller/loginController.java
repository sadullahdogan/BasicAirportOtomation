/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.userDao;
import Entitiy.user;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author BUSE
 */
@Named
@SessionScoped
public class loginController implements Serializable {

    private user su;
    private List<user> liste;
    private userDao uDao;

    public loginController() {
    }

    public user getSu() {
        if (this.su == null) {
            this.su = new user();
        }
        return su;
    }

    public void setSu(user su) {
        this.su = su;
    }

    public List<user> getListe() {
        this.liste = getuDao().getUser(1, 1000);
        return liste;
    }

    public void setListe(List<user> liste) {
        this.liste = liste;
    }

    public userDao getuDao() {
        if (this.uDao == null) {
            this.uDao = new userDao();
        }

        return uDao;
    }

    public void setuDao(userDao uDao) {
        this.uDao = uDao;
    }

    public String girisKontrol() {
        getListe();

        for (user user : this.liste) {
            if (user.getUserName().equals(su.getUserName()) && user.getPassword().equals(su.getPassword())) {
                this.su = user;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", user);

                return "secret/ucak?faces-redirect=true";
            } else {

            }
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hatalı Giriş"));

        return "adminGirisi";
    }

}
