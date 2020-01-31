/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entitiy.grup;
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
public class grupDao {

    public grupDao() {
        
    }
public grup find(int gId){
    
    grup grup = new grup();

        DBConnection db = new DBConnection();

        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from grup where g_id= ?");
            st.setInt(1,gId);
           
            ResultSet rs = st.executeQuery();
            rs.next();
            grup.setGrupAdi(rs.getString("g_adi"));
            grup.setGrupId(rs.getInt("g_id"));
           
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return grup;
    }

 public List<grup> getGrup(int page,int pageSize) {
        List<grup> liste = new ArrayList();
         int start=(page-1)*pageSize;
        DBConnection db = new DBConnection();

        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from grup order by g_id asc limit "+start+','+pageSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                //System.out.println(rs.getString("kitap_adi"));
                grup gr = new grup(rs.getInt("g_id"), rs.getString("g_adi"));
                liste.add(gr);

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
            PreparedStatement pst = c.prepareStatement("select count(g_id) as grup_count from grup");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("grup_count");
        }
        
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

      
            return count;
      }


    public void sil(grup gr) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("delete from grup where g_id=?");
            pst.setLong(1, gr.getGrupId());
            pst.executeUpdate();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void insert(grup gr) {
        DBConnection db = new DBConnection();
        
        try {
            Connection c = db.Connect();
            PreparedStatement pst = c.prepareStatement("insert into grup (g_adi) values(?)");

            
            pst.setString(1, gr.getGrupAdi());

            pst.execute();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void guncelle(grup gr) {

        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("update grup set g_adi =?  where g_id=?");
            pst.setString(1, gr.getGrupAdi());
            pst.setLong(2, gr.getGrupId());

            pst.executeUpdate();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
