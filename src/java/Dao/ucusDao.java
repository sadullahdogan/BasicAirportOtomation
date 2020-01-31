/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entitiy.ucus;
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
public class ucusDao {
    HavaalaniDao hDao= new HavaalaniDao();
    havaYoluSirketiDao sDao= new havaYoluSirketiDao();

    public ucusDao() {
    }

    public void sil(ucus ucus) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("delete from ucus where ucus_no=?");
            pst.setLong(1, ucus.getUcusNo());
            pst.executeUpdate();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void insert(ucus ucus) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("insert into ucus(kalkis_tarihi,kalkis_havaalanı,varis_havaalanı,ucus_no,sirket_id)values(?,?,?,?,?)");
            pst.setString(1, ucus.getKalkisSaati());
            pst.setLong(2, ucus.getKalkisHavaAlani().getHavaalaniId());
            pst.setLong(3, ucus.getVarisHavaAlani().getHavaalaniId());
            pst.setLong(4, ucus.getUcusNo());
            pst.setLong(5, ucus.getSirket().getSirketId());
            pst.execute();
          
            c.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public List<ucus> find(int kalkis,int varis){
    List<ucus> liste =new ArrayList();
    
    DBConnection db = new DBConnection();

        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from ucus where kalkis_havaalanı= ? and varis_havaalanı= ?" );
            st.setInt(1,kalkis);
            st.setInt(2, varis);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                ucus ucs = new ucus(rs.getString("kalkis_Tarihi"), hDao.find(rs.getInt("kalkis_havaalanı")),hDao.find( rs.getInt("varis_havaalanı")), sDao.find(rs.getInt("sirket_id")), rs.getInt("ucus_no"));
                liste.add(ucs);
            }
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    
    return liste;
    
    }

   public List<ucus> getUcus(int page,int pageSize) {
        List<ucus> liste = new ArrayList();
        int start=(page-1)*pageSize;
        DBConnection db = new DBConnection();

        Connection c = null;
       
        try {

            c = db.Connect();
            PreparedStatement pst = c.prepareStatement("select * from ucus order by ucus_no asc limit "+start+','+pageSize);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                ucus ucs = new ucus(rs.getString("kalkis_Tarihi"), hDao.find(rs.getInt("kalkis_havaalanı")),hDao.find( rs.getInt("varis_havaalanı")), sDao.find(rs.getInt("sirket_id")), rs.getInt("ucus_no"));
                liste.add(ucs);
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
            PreparedStatement pst = c.prepareStatement("select count(ucus_no) as ucus_count from ucus");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count=rs.getInt("ucus_count");
        }
        
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

      
            return count;
      }

    public void guncelle(ucus ucus) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("update ucus set kalkis_tarihi=?,kalkis_havaalanı=?, varis_havaalanı=? ,sirket_id=? where ucus_no=? ");
            pst.setString(1, ucus.getKalkisSaati());
            pst.setLong(2, ucus.getKalkisHavaAlani().getHavaalaniId());
            pst.setLong(3, ucus.getVarisHavaAlani().getHavaalaniId());
            pst.setLong(4, ucus.getSirket().getSirketId());
            pst.setLong(5, ucus.getUcusNo());
            pst.executeUpdate();
          
            c.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
