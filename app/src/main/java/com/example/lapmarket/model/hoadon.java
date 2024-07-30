package com.example.lapmarket.model;

public class hoadon {
    private int mahd;
    private String hoten;
    private String tensp;
    private int soluong;
    private int gia;
    private String ngaymua;
    private int trangthai;

    public hoadon() {
    }    public hoadon(int mahd, String hoten, String tensp,int soluong, int gia, String ngaymua, int trangthai) {
        this.mahd = mahd;
        this.hoten = hoten;
        this.tensp = tensp;
        this.soluong = soluong;
        this.gia = gia;
        this.ngaymua = ngaymua;
        this.trangthai = trangthai;

    }



    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
