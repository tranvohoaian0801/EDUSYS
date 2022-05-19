/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
import com.edusys.entity.KhoaHoc;
import com.edusys.utils.XJdbc;
import com.edusys.utils.xDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc,Integer>{

    @Override
    public void insert(KhoaHoc entity) {
        String sql="INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayTao());
               
    }

    @Override
    public void update(KhoaHoc entity) {
       String sql="UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=?, NgayTao=? WHERE MaKH=?";
        XJdbc.update(sql,
                entity.getMaCD(),
                 entity.getHocPhi(),
                 entity.getThoiLuong(),
                 entity.getNgayKG(),
                 entity.getGhiChu(),
                 entity.getMaNV(),
                 entity.getNgayTao(),
                 entity.getMaKH());
    }

    @Override
    public void delete(Integer key) {
       String sql="DELETE FROM KhoaHoc WHERE MaKH=?";
       XJdbc.update(sql, key);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        String sql="SELECT * FROM KhoaHoc";
        return selectBySql(sql);     
    }

    @Override
    public KhoaHoc selectById(Integer id) {
         String sql="SELECT * FROM KhoaHoc WHERE MaKH=?";
         List<KhoaHoc> list = selectBySql(sql, id);
         if(list.isEmpty()){
            return null;
         }
         return list.get(0);
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
          List<KhoaHoc> list = new ArrayList<KhoaHoc>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaCD(rs.getString("MaCD"));
                entity.setHocPhi(rs.getString("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));               
                entity.setNgayKG(rs.getDate("NgayKG"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<KhoaHoc> selectByChuyenDe(String macd){
        String sql = "SELECT * FROM KHOAHOC WHERE MaCD=?";
        return this.selectBySql(sql, macd);
    }
    public List<Integer> selecYears(){
        String sql = "SELECT DISTINCT year(NgayKG) FROM KHOAHOC ORDER BY year(NgayKG) DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<KhoaHoc> selectByKeyword(String Keyword){
        String sql = "SELECT * FROM KhoaHoc WHERE makh LIKE ? ";
        return this.selectBySql(sql, "%" + Keyword + "%");
    }
    
    public List<KhoaHoc> selectNotInCourse(int makh, String keyword){
        String sql = "SELECT * FROM  NGUOIHOC "
                + "WHERE HoTen LIKE ?  AND "
                + "MaNH NOT IN (SELECT MANH FROM HOCVIEN WHERE MaKH=?)";
        return this.selectBySql(sql, "%"+ keyword + "%",makh);
    }
    
}
