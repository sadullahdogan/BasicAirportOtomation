/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entitiy.Havaalani;
import Entitiy.havaYoluSirketi;
import Entitiy.havaalaniVeri;

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
 * @author Sadullah
 */
public class HavaalaniDao {

    public HavaalaniDao() {
    }
public havaalaniVeri getBilgi(int hId){
    havaalaniVeri hVeri= new havaalaniVeri();
    dosyaDao dDao=new dosyaDao();
    

        DBConnection db = new DBConnection();

        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from havaalanibilgi where h_id=?");
            st.setInt(1,hId);
           
            ResultSet rs = st.executeQuery();
            rs.next();
            hVeri.setbId(rs.getInt("b_id"));
            hVeri.setBilgi(rs.getString("h_bilgi"));
            hVeri.setDosya(dDao.find(rs.getInt("d_id")));
            hVeri.sethId(rs.getInt("h_id"));
            System.out.println(hVeri.getBilgi());
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
return hVeri;

}
    public List<String> getSirketler(int hId) {
        havaYoluSirketiDao hdao = new havaYoluSirketiDao();

        List<String> sirketList = new ArrayList();
        DBConnection db = new DBConnection();
        Connection c = null;
        PreparedStatement st = null;

        try {
            c = db.Connect();
            st = c.prepareStatement("select * from havaalanısirket where havaAlanı_id=" + hId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                sirketList.add(hdao.getSirketAdi(rs.getInt("sirket_id")));

            }
            c.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return sirketList;

    }

    public String getSirketAdi(int hId) {
        String sirketAdi = null;
        DBConnection db = new DBConnection();

        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from havaalanı where h_id=?" );
            st.setInt(1, hId);
            ResultSet rs = st.executeQuery();
            rs.next();
            sirketAdi = rs.getString("h_adi");
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sirketAdi;

    }

     public int count() {
        int count = 0;
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        Statement st;
        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("select count(h_id) as h_count from havaalanı");
            rs.next();
            count = rs.getInt("h_count");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public List<Havaalani> getHavaalani(int page, int pageSize) {
        List<Havaalani> havaalaniList = new ArrayList();
        int start = (page - 1) * pageSize;
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        Statement st;
        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from havaalanı order by h_id asc limit " + start + ',' + pageSize);
            while (rs.next()) {

                Havaalani cs = new Havaalani(rs.getString("h_adi"), rs.getInt("h_id"), rs.getString("bulunduğu_sehir"));
                havaalaniList.add(cs);

            }
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return havaalaniList;
    }
    public Havaalani find(int hId){
    
    Havaalani havaalani = new Havaalani();

        DBConnection db = new DBConnection();

        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from havaalanı where h_id=?");
            st.setInt(1,hId);
           
            ResultSet rs = st.executeQuery();
            rs.next();
            havaalani.setHavaalaniAdi(rs.getString("h_adi"));
            havaalani.setHavaalaniId(rs.getInt("h_id"));
            havaalani.setBulunduguSehir(rs.getString("bulunduğu_sehir"));
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return havaalani;
    }

    public void insert(Havaalani havaalani) {

        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("insert into havaalanı(h_adi,bulunduğu_sehir) values(?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, havaalani.getHavaalaniAdi());
            pst.setString(2, havaalani.getBulunduguSehir());
            pst.execute();

            int havaalaniId = 0;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                havaalaniId = gk.getInt(1);
            }
            for (havaYoluSirketi i: havaalani.getSirketler()) {
                pst = c.prepareStatement("insert into havaalanısirket (havaAlanı_id,sirket_id) values(?,?) ");
                pst.setLong(1, havaalaniId);
                pst.setLong(2, i.getSirketId());
                pst.execute();

            }
            c.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Havaalani hav) {

        DBConnection db = new DBConnection();
        Connection c = db.Connect();

        try {
            PreparedStatement pst = c.prepareStatement("delete from havaalanı where h_id=?");
            pst.setLong(1, hav.getHavaalaniId());
            pst.executeUpdate();
            
            c.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(Havaalani havaalani) {

        DBConnection db = new DBConnection();
        Connection c = db.Connect();

        try {
            PreparedStatement pst = c.prepareStatement("update havaalanı set h_adi=?, bulunduğu_sehir=? where h_id=?");
            pst.setString(1, havaalani.getHavaalaniAdi());
            pst.setString(2, havaalani.getBulunduguSehir());
            pst.setLong(3, havaalani.getHavaalaniId());

            pst.executeUpdate();
            
            pst=c.prepareStatement("delete from havaalanısirket where havaAlanı_id=?");
            pst.setInt(1, havaalani.getHavaalaniId());
            pst.executeUpdate();
            
            for (havaYoluSirketi i: havaalani.getSirketler()) {
                pst = c.prepareStatement("insert into havaalanısirket (havaAlanı_id,sirket_id) values(?,?) ");
                pst.setLong(1, havaalani.getHavaalaniId());
                pst.setLong(2, i.getSirketId());
                pst.execute();

            }
            
            c.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
