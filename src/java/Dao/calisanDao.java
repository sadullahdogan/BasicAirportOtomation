/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entitiy.calisan;
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
public class calisanDao {

    public calisanDao() {

    }
private havaYoluSirketiDao hDao;
private grupDao gDao=new grupDao();

    public havaYoluSirketiDao gethDao() {
        if(this.hDao==null)
            hDao=new havaYoluSirketiDao();
        return hDao;
    }

    public void sethDao(havaYoluSirketiDao hDao) {
        this.hDao = hDao;
    }

    public grupDao getgDao() {
          if(this.gDao==null)
            gDao=new grupDao();
        return gDao;
    }

    public void setgDao(grupDao gDao) {
        this.gDao = gDao;
    }


    public List<calisan> getCalisan(int page, int pageSize) {
        List<calisan> liste = new ArrayList();
        DBConnection db = new DBConnection();
        int start = (page - 1) * pageSize;
        Connection c = null;
        PreparedStatement st = null;
        try {

            c = db.Connect();
            st = c.prepareStatement("select * from calisan order by c_id asc limit " + start + ',' + pageSize);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("kitap_adi"));
                calisan cs = new calisan(rs.getString("c_adi"), rs.getInt("c_id"), rs.getInt("c_maas"), rs.getInt("c_yasi"), this.gethDao().find(rs.getInt("sirket_id")));
                liste.add(cs);

            }
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return liste;
    }

    public int count() {
        int count = 0;
        DBConnection db = new DBConnection();
        Connection c = null;

        try {

            c = db.Connect();
            PreparedStatement pst = c.prepareStatement("select count(c_id) as calisan_count from calisan");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("calisan_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public void sil(calisan cl) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("delete from calisan where c_id=?");
            pst.setLong(1, cl.getCalisanId());
            pst.executeUpdate();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void insert(calisan cl) {
        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("insert into calisan(c_adi,c_maas,c_yasi,sirket_id)values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, cl.getCalisanAdi());
            pst.setLong(2, cl.getCalisanMaas());
            pst.setLong(3, cl.getCalisanYas());
            pst.setLong(4, cl.getSirket().getSirketId());
            pst.execute();

            int calisanId = 0;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                calisanId = gk.getInt(1);
            }
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void guncelle(calisan cl) {

        DBConnection db = new DBConnection();
        Connection c = db.Connect();
        try {
            PreparedStatement pst = c.prepareStatement("update calisan set c_adi=?, c_maas =?, c_yasi=?,sirket_id=? where c_id=?");
            pst.setString(1, cl.getCalisanAdi());
            pst.setLong(2, cl.getCalisanMaas());
            pst.setLong(3, cl.getCalisanYas());
            pst.setLong(4, cl.getSirket().getSirketId());
            pst.setLong(5, cl.getCalisanId());

            pst.executeUpdate();
            c.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
