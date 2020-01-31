/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entitiy.user;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sadullah
 */
public class userDao {
private dosyaDao dDao;

    public dosyaDao getdDao() {
        if(dDao==null)
            dDao= new dosyaDao();
        return dDao;
    }

    public void setdDao(dosyaDao dDao) {
        this.dDao = dDao;
    }

   public List<user> getUser(int page,int pageSize) {
        List<user> liste = new ArrayList();
         int start=(page-1)*pageSize;
        DBConnection db = new DBConnection();
        grupDao gDao= new grupDao();

        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from user order by user_id asc limit "+start+','+pageSize);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("kitap_adi"));
                user us = new user(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"), gDao.find(rs.getInt("g_id")),getdDao().find(rs.getInt("dosya_id")));
                liste.add(us);

            }
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return liste;
    }
    
     public int count () {
        int count=0;
        DBConnection db = new DBConnection();
        Connection c = null;
       
        try {

            c = db.Connect();
            PreparedStatement pst = c.prepareStatement("select count(user_id) as user_count from user");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("user_count");
        }
        
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

      
            return count;
      }
    public void insert(user user) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("insert into user(user_name,user_password,g_id,dosya_id)values(?,?,?,?)");
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getPassword());
            pst.setLong(3, user.getGrup().getGrupId());
            pst.setLong(4, user.getProfilResmi().getDosyaId());
            
            pst.execute();
          
            c.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
      public void guncelle(user user) {

        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("update user set user_name=?,user_password=?,g_id=?,dosya_id=?  where user_id=?");
            
           
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getPassword());
             pst.setInt(3, user.getGrup().getGrupId());
             pst.setInt(4, user.getUserId());
             pst.setInt(5, user.getProfilResmi().getDosyaId());
            pst.executeUpdate();

            c.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
         public void sil(user user) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("delete from user where user_id=?");
            pst.setLong(1,user.getUserId());
            pst.executeUpdate();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
