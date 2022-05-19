/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NhanVien;
import com.edusys.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String>{

    @Override
    public void insert(NhanVien entity) {
         String sql="INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro, GioiTinh) VALUES (?, ?, ?, ?, ?)";
        XJdbc.update(sql, 
                entity.getMaNV(), 
                entity.getMatKhau(), 
                entity.getHoTen(), 
                entity.getVaiTro(),
                entity.getGioitinh());
    }

    @Override
    public void update(NhanVien entity) {
       String sql="UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=? GioiTinh=? WHERE MaNV=?";
        XJdbc.update(sql, 
                entity.getMatKhau(), 
                entity.getHoTen(), 
                entity.getVaiTro(),
                entity.getGioitinh(),
                entity.getMaNV());
    }

    @Override
    public void delete(String key) {
         String sql="DELETE FROM NhanVien WHERE MaNV=?";
        XJdbc.update(sql, key);
    }

    @Override
    public List<NhanVien> selectAll() {
        String sql="SELECT * FROM NhanVien";
        return this.selectBySql(sql);
    }

    @Override
    public NhanVien selectById(String key) {
       String sql="SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = this.selectBySql(sql, key);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    NhanVien entity=new NhanVien();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setMatKhau(rs.getString("MatKhau"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setVaiTro(rs.getBoolean("VaiTro"));
                    entity.setGioitinh(rs.getString("GioiTinh"));
                    list.add(entity);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
}
