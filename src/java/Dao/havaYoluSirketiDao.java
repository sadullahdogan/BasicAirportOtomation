/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entitiy.havaYoluSirketi;

import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BUSE
 */
public class havaYoluSirketiDao {

    public havaYoluSirketiDao() {

    }

   public List<havaYoluSirketi> gethavaYoluSirketi(int page,int pageSize) {
        List<havaYoluSirketi> liste = new ArrayList();
        int start=(page-1)*pageSize;
        DBConnection db = new DBConnection();

        Connection c = null;
        PreparedStatement pst = null;
        try {

            c = db.Connect();
            pst = c.prepareStatement("select * from havayolusirketi order by sirket_id asc limit "+start+','+pageSize);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                havaYoluSirketi sr = new havaYoluSirketi(rs.getInt("sirket_id"), rs.getString("sirket_adi"));
                liste.add(sr);

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
            PreparedStatement pst = c.prepareStatement("select count(sirket_id) as sirket_count from havayolusirketi");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("sirket_count");
        }
        
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

      
            return count;
      }

    public void guncelle(havaYoluSirketi sc) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("update havayolusirketi set sirket_adi=? where sirket_id=?");
            pst.setString(1, sc.getSirketAdi());
            pst.setLong(2, sc.getSirketId());
            pst.executeUpdate();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sil(havaYoluSirketi havaYoluSirketi) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("delete from havayolusirketi where sirket_id=?");
            pst.setLong(1, havaYoluSirketi.getSirketId());
            pst.executeUpdate();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getSirketAdi(int sirketId) {
        String sirketAdi = null;

        DBConnection db = new DBConnection();

        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from havayolusirketi where sirket_id=" + sirketId);
            ResultSet rs = st.executeQuery();
            rs.next();
            sirketAdi = rs.getString("sirket_adi");
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sirketAdi;

    }
    public havaYoluSirketi find(int sirketId){
    
    havaYoluSirketi sirket = new havaYoluSirketi();

        DBConnection db = new DBConnection();

        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from havayolusirketi where sirket_id= ?" );
            st.setInt(1,sirketId);
            ResultSet rs = st.executeQuery();
            rs.next();
            sirket.setSirketAdi(rs.getString("sirket_adi"));
            sirket.setSirketId(rs.getInt("sirket_id"));
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sirket;
    }

    public void insert(havaYoluSirketi sc) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
           PreparedStatement pst=c.prepareStatement("insert into havayolusirketi(sirket_adi)values(?)", Statement.RETURN_GENERATED_KEYS);
           pst.setString(1,sc.getSirketAdi());
           pst.execute();
           c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
