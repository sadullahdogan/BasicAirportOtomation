/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ABRA
 */
@Named
@SessionScoped
public class navigationController implements Serializable{
    public String adminPage(String p){
    return "/secret/"+p+"?faces-redirect=true"; 
    
    }
    public String page(String p){
         return "/front/"+p+"?faces-redirect=true"; 
    
    
    }
    
}
