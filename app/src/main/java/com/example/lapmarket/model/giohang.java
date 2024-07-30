package com.example.lapmarket.model;

public class giohang {
    private int id;
    private String tensp;
    private int gia;
    private int soluong;
    private int masp;

    public giohang(int soluong) {
        this.soluong = soluong;
    }


    public giohang(int id, String tensp, int gia, int soluong, int masp) {
        this.id = id;
        this.tensp = tensp;
        this.gia = gia;
        this.soluong = soluong;
        this.masp = masp;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong ;
    }

    public giohang(int id, String tensp, int gia) {
        this.id = id;
        this.tensp = tensp;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
