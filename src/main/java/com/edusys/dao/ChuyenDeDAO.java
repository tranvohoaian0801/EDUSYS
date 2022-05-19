/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
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
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe,String>{

    @Override
    public void insert(ChuyenDe entity) {
        String sql="INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                entity.getMaCD(),
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
       String sql="UPDATE ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? WHERE MaCD=?";
        XJdbc.update(sql,
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa(),
                entity.getMaCD());
    }

    @Override
    public void delete(String key) {
       String sql="DELETE FROM ChuyenDe WHERE MaCD=?";
        XJdbc.update(sql, key);
    }

    @Override
    public List<ChuyenDe> selectAll() {
         String sql="SELECT * FROM ChuyenDe";
        return selectBySql(sql);
    }

    @Override
    public ChuyenDe selectById(String key) {
         String sql="SELECT * FROM ChuyenDe WHERE MaCD=?";
        List<ChuyenDe> list=selectBySql(sql,key);
        return list.size()>0?list.get(0):null;
    }

    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
       List<ChuyenDe> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    ChuyenDe entity=new ChuyenDe();
                    entity.setMaCD(rs.getString("MaCD"));
                    entity.setTenCD(rs.getString("TenCD"));
                    entity.setHocPhi(rs.getFloat("HocPhi"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setMoTa(rs.getString("MoTa"));
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
