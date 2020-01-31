/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Entitiy.user;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BUSE
 */
@WebFilter("/*")
public class loginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();
        user u = (user) req.getSession().getAttribute("valid_user");
        if (u == null) {
            if (url.contains("secret") || url.contains("logout")) {
               
                
                res.sendRedirect(req.getContextPath() + "/faces/adminGirisi.xhtml?faces-redirect=true");
                
                } else {
                chain.doFilter(request, response);
            }
        } else {
            
             if(url.contains("user")&&u.getGrup().getGrupId()!=3){
                res.sendRedirect(req.getContextPath() + "/faces/secret/ucak.xhtml?faces-redirect=true");
             }
            if (url.contains("admin")) {
                res.sendRedirect(req.getContextPath() + "/faces/secret/ucak.xhtml?faces-redirect=true");

            } else if (url.contains("logout")) {
                req.getSession().invalidate();
                res.sendRedirect(req.getContextPath() + "/faces/adminGirisi.xhtml?faces-redirect=true");

            } else {

                chain.doFilter(request, response);
            }
        }

    }

}
