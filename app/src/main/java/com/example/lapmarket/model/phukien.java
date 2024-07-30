package com.example.lapmarket.model;

public class phukien {

    private int mapk;
    private String tenpk;
    private int gia;
    private String dungluong;
    private String loairam;
    private String hotro;
    private String voltage;
    private String busram;
    private String hangsanxuat;

    public phukien() {
    }

    public phukien(int mapk, String tenpk, int gia, String dungluong, String loairam, String hotro, String voltage, String busram, String hangsanxuat) {
        this.mapk = mapk;
        this.tenpk = tenpk;
        this.gia = gia;
        this.dungluong = dungluong;
        this.loairam = loairam;
        this.hotro = hotro;
        this.voltage = voltage;
        this.busram = busram;
        this.hangsanxuat = hangsanxuat;
    }

    public int getMapk() {
        return mapk;
    }

    public void setMapk(int mapk) {
        this.mapk = mapk;
    }

    public String getTenpk() {
        return tenpk;
    }

    public void setTenpk(String tenpk) {
        this.tenpk = tenpk;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getDungluong() {
        return dungluong;
    }

    public void setDungluong(String dungluong) {
        this.dungluong = dungluong;
    }

    public String getLoairam() {
        return loairam;
    }

    public void setLoairam(String loairam) {
        this.loairam = loairam;
    }

    public String getHotro() {
        return hotro;
    }

    public void setHotro(String hotro) {
        this.hotro = hotro;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getBusram() {
        return busram;
    }

    public void setBusram(String busram) {
        this.busram = busram;
    }

    public String getHangsanxuat() {
        return hangsanxuat;
    }

    public void setHangsanxuat(String hangsanxuat) {
        this.hangsanxuat = hangsanxuat;
    }
}
