/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Entitiy.ucak;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BUSE
 */
public class ucakDao {
    havaYoluSirketiDao hdao=new havaYoluSirketiDao();

    public ucakDao() {

    }

    public void sil(ucak uc) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("delete from ucak where ucak_id=?");
            pst.setLong(1, uc.getUcakId());
            pst.executeUpdate();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void insert(ucak uc) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("insert into ucak(ucak_kapasite,ucak_modeli,sirket_id)values(?,?,?)");
            pst.setLong(1, uc.getUcakKapasitesi());
            pst.setString(2, uc.getUcakModeli());
            pst.setLong(3, uc.getSirket().getSirketId());

            pst.execute();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

     public List<ucak> getUcak(int page,int pageSize) {
        List<ucak> liste = new ArrayList();
         int start=(page-1)*pageSize;
        DBConnection db = new DBConnection();

        Connection c = null;

        try {

            c = db.Connect();
            PreparedStatement pst = c.prepareStatement("select * from ucak order by ucak_id asc limit "+start+','+pageSize);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                ucak us = new ucak(hdao.find( rs.getInt("sirket_id")), rs.getInt("ucak_id"), rs.getInt("ucak_kapasite"), rs.getString("ucak_modeli"));
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
            PreparedStatement pst = c.prepareStatement("select count(ucak_id) as ucak_count from ucak");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("ucak_count");
        }
        
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

      
            return count;
      }



    public void guncelle(ucak uc) {

        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("update ucak set sirket_id=?,ucak_kapasite=?,ucak_modeli=?  where ucak_id=?");
            pst.setLong(1, uc.getSirket().getSirketId());
            pst.setLong(2, uc.getUcakKapasitesi());
            pst.setString(3, uc.getUcakModeli());
            pst.setLong(4, uc.getUcakId());
            pst.executeUpdate();

            c.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
