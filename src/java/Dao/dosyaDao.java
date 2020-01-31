/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entitiy.dosya;

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
public class dosyaDao {

    public dosyaDao() {
    }
     public dosya find(int dosyaId){
    
    dosya resim = new dosya();

        DBConnection db = new DBConnection();

        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from dosya where d_id=?" );
            st.setInt(1,dosyaId);
            ResultSet rs = st.executeQuery();
            rs.next();
            resim.setDosyaAdi(rs.getString("d_adi"));
            resim.setDosyaId(rs.getInt("d_id"));
            resim.setDosyaYolu(rs.getString("d_yolu"));
           
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resim ;
    }

     public List<dosya> getDosya(int page,int pageSize) {
        List<dosya> liste = new ArrayList();
        int start=(page-1)*pageSize;
        DBConnection db = new DBConnection();

        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from dosya order by d_id asc limit "+start+','+pageSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                //System.out.println(rs.getString("kitap_adi"));
                dosya ds = new dosya(rs.getInt("d_id"), rs.getString("d_adi"), rs.getString("d_yolu"));
                liste.add(ds);

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
            PreparedStatement pst = c.prepareStatement("select count(d_id) as dosya_count from dosya");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("dosya_count");
        }
        
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

      
            return count;
      }

    public void sil(dosya ds) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("delete from dosya where d_id = ?");
            pst.setLong(1, ds.getDosyaId());
            pst.executeUpdate();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void insert(dosya ds) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("insert into dosya(d_adi,d_yolu)values(?,?)", Statement.RETURN_GENERATED_KEYS);

            
            pst.setString(1, ds.getDosyaAdi());
            pst.setString(2, ds.getDosyaYolu());

            pst.execute();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void guncelle(dosya ds) {

        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("update dosya set d_adi =? ,d_yolu=?  where d_id=?");

            pst.setString(1, ds.getDosyaAdi());
            pst.setString(2, ds.getDosyaYolu());
            pst.setLong(3, ds.getDosyaId());

            pst.executeUpdate();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
