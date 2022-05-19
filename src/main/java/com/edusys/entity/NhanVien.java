/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.entity;

/**
 *
 * @author dell
 */
public class NhanVien {
    String maNV;
    String matKhau;
    String hoTen;
    boolean vaiTro =false;
    String Gioitinh ; 

    public String getMaNV() {
        return maNV;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.Gioitinh = gioitinh;
    }

  
    public String toString(){
        return this.hoTen;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public NhanVien() {
    }

    public NhanVien(String maNV, String matKhau, String hoTen, String gioitinh) {
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.Gioitinh = gioitinh;
    }

   
  

   
    
}
