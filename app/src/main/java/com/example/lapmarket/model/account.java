package com.example.lapmarket.model;

public class account {
    private int id;
    private String hoten;
    private String matkhau;
    private String email;
    private String loaitaikhoan;


    public account() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public account(int id, String hoten, String matkhau, String email, String loaitaikhoan) {
        this.id = id;
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.email = email;
        this.loaitaikhoan = loaitaikhoan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }

}
