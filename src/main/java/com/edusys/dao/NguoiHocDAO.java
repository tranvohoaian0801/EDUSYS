/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NguoiHoc;
import com.edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc,String>{

    @Override
    public void insert(NguoiHoc entity) {

        String sql="INSERT INTO NguoiHoc(MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql, 
                entity.getMaNH(), 
                entity.getHoTen(), 
                entity.getNgaySinh(), 
                entity.getGioiTinh(), 
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV());
                
    }

    @Override
    public void update(NguoiHoc entity) {
         String sql="UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV=?, NgayDK =? WHERE MaNH=?";
        XJdbc.update(sql, 
                entity.getHoTen(), 
                entity.getNgaySinh(), 
                entity.getGioiTinh(), 
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayDK(),
                entity.getMaNH());
    }

    @Override
    public void delete(String key) {
          String sql="DELETE FROM NguoiHoc WHERE MaNH=?";
        XJdbc.update(sql, key);
    }

    @Override
    public List<NguoiHoc> selectAll() {
         String sql="SELECT * FROM NguoiHoc";
        return selectBySql(sql);
    }

    @Override
    public NguoiHoc selectById(String key) {
         String sql="SELECT * FROM NguoiHoc WHERE MaNH=?";
        List<NguoiHoc> list = selectBySql(sql, key);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
          List<NguoiHoc> list = new ArrayList<NguoiHoc>();
        try {
           ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {                
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNH(rs.getString("MaNH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayDK(rs.getDate("NgayDK"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<NguoiHoc> selectByKeyword(String keyword){
        String sql="SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }

    public List<NguoiHoc> selectNotInCourse(int makh, String keyword) {
        String sql="SELECT * FROM NguoiHoc "
                + " WHERE HoTen LIKE ? AND "
                + " MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return this.selectBySql(sql, "%"+keyword+"%", makh);
    }
    
    
}
