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
public class HocVien {

    int MaHV;
    int MaKH;
    String MaNH;
    double Diem;

    public int getMaHV() {
        return MaHV;
    }

    public void setMaHV(int MaHV) {
        this.MaHV = MaHV;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaNH() {
        return MaNH;
    }

    public void setMaNH(String MaNH) {
        this.MaNH = MaNH;
    }

    public Double getDiem() {
        return Diem;
    }

    public void setDiem(double Diem) {
        this.Diem = Diem;
    }

    public HocVien() {
    }

    public HocVien(int MaHV, int MaKH, String MaNH, double Diem) {
        this.MaHV = MaHV;
        this.MaKH = MaKH;
        this.MaNH = MaNH;
        this.Diem = Diem;
    }

}

