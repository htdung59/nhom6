package com.example.lapmarket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lapmarket.database.DbHelper;
import com.example.lapmarket.designPantter.AccountSingle;
import com.example.lapmarket.model.giohang;
import com.example.lapmarket.model.sanpham;

import java.util.ArrayList;

public class SanPhamDAO {
    DbHelper dbHelper;
    Context context;


    public SanPhamDAO(Context context) {
        dbHelper = new DbHelper(context);
        this.context = context;
    }

    public ArrayList<sanpham> selectSANPHAM(){
        ArrayList<sanpham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                    list.add(new sanpham(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getString(9),
                            cursor.getString(10),
                            cursor.getString(11),
                            cursor.getString(12),
                            cursor.getString(13),
                            cursor.getString(14),
                            cursor.getString(15),
                            cursor.getString(16)));
            }while (cursor.moveToNext());
        }
        return list;


    }



    public ArrayList<sanpham> selectGAMING(){
        ArrayList<sanpham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM GAMING",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new sanpham(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8), cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12),
                        cursor.getString(13),
                        cursor.getString(14),
                        cursor.getString(15),
                        cursor.getString(16)));

            }while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<sanpham> selectMACBOOK(){
        ArrayList<sanpham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM VANPHONG",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new sanpham(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12),
                        cursor.getString(13),
                        cursor.getString(14),
                        cursor.getString(15),
                        cursor.getString(16)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    //delete
    public boolean deleteSP(int masp){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long row = sqLiteDatabase.delete("SANPHAM","masp = ?",
                new String[]{String.valueOf(masp)});
        if (row <= 0){
            return false;
        }
        return true;
    }
    public boolean deleteGAM(int masp){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long row = sqLiteDatabase.delete("GAMING","masp = ?",new String[]{String.valueOf(masp)});
        if (row <= 0){
            return false;
        }
        return true;
    }
    public boolean deleteVP(int masp){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long row = sqLiteDatabase.delete("VANPHONG","masp = ?",new String[]{String.valueOf(masp)});
        if (row <= 0){
            return false;
        }
        return true;
    }

    //update
    public boolean updateSP(int masp, String tensp, int gia , String thuonghieu, String xuatxu, String kichthuocmanhinh, String mausac, String trongluong, String chatlieu, String cpu,String ocung, String ram,String rom,String card, String tocdocpu, String congusb, String vantay){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);

        long check = sqLiteDatabase.update("SANPHAM", contentValues, "masp = ?", new String[]{String.valueOf(masp)});
        if (check == -1){
            return false;
        }
        return true;

    }
    public boolean updateGAM(int masp, String tensp, int gia , String thuonghieu, String xuatxu, String kichthuocmanhinh, String mausac, String trongluong, String chatlieu, String cpu,String ocung, String ram,String rom,String card, String tocdocpu, String congusb, String vantay){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);

        long check = sqLiteDatabase.update("GAMING", contentValues, "masp = ?", new String[]{String.valueOf(masp)});
        if (check == -1){
            return false;
        }
        return true;

    }
    public boolean updateVP(int masp, String tensp, int gia , String thuonghieu, String xuatxu, String kichthuocmanhinh, String mausac, String trongluong, String chatlieu, String cpu,String ocung, String ram,String rom,String card, String tocdocpu, String congusb, String vantay){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);

        long check = sqLiteDatabase.update("VANPHONG", contentValues, "masp = ?", new String[]{String.valueOf(masp)});
        if (check == -1){
            return false;
        }
        return true;

    }



    //add
    public boolean addGAM(String tensp, int gia , String thuonghieu, String xuatxu, String kichthuocmanhinh, String mausac, String trongluong, String chatlieu, String cpu,String ocung, String ram,String rom,String card, String tocdocpu, String congusb, String vantay){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);

        long check = sqLiteDatabase.insert("GAMING", null, contentValues);
        if (check == -1){
            return false;
        }
        return true;
    }
    public boolean addVP(String tensp, int gia , String thuonghieu, String xuatxu, String kichthuocmanhinh, String mausac, String trongluong, String chatlieu, String cpu,String ocung, String ram,String rom,String card, String tocdocpu, String congusb, String vantay){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);

        long check = sqLiteDatabase.insert("VANPHONG", null, contentValues);
        if (check == -1){
            return false;
        }
        return true;
    }
    public boolean addSP(String tensp, int gia , String thuonghieu, String xuatxu, String kichthuocmanhinh,
                         String mausac, String trongluong, String chatlieu, String cpu,String ocung,
                         String ram,String rom,String card,
                         String tocdocpu, String congusb, String vantay){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", tensp);
        contentValues.put("gia", gia);
        contentValues.put("thuonghieu", thuonghieu);
        contentValues.put("xuatxu", xuatxu);
        contentValues.put("kichthuocmanhinh", kichthuocmanhinh);
        contentValues.put("mausac", mausac);
        contentValues.put("trongluong", trongluong);
        contentValues.put("chatlieu", chatlieu);
        contentValues.put("cpu", cpu);
        contentValues.put("ocung", ocung);
        contentValues.put("ram", ram);
        contentValues.put("rom", rom);
        contentValues.put("card", card);
        contentValues.put("tocdocpu", tocdocpu);
        contentValues.put("congusb", congusb);
        contentValues.put("vantay", vantay);
        long check = sqLiteDatabase.insert("SANPHAM", null, contentValues);
        if (check == -1){
            return false;
        }
        return true;
    }
    public Boolean addToCart(sanpham sanPham) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        ArrayList<giohang> list = new GioHangDAO(context).listGH();
        for (int i = 0; i < list.size(); i++){

            if (list.get(i).getMasp() == sanPham.getMasp() && list.get(i).getTensp().equals(sanPham.getTensp())){
                values.put("TENSP", sanPham.getTensp());
                values.put("GIA", sanPham.getGia());
                values.put("SOLUONG", list.get(i).getSoluong() + 1);
                values.put("id_ac", AccountSingle.getInstance().getAccount().getId());
                values.put("masp", sanPham.getMasp());
                sqLiteDatabase.update("GIOHANG", values, "tensp =?",  new String[]{list.get(i).getTensp()});

                sqLiteDatabase.close();
                return true;
            }
        }
        values.put("tensp", sanPham.getTensp());
        values.put("gia", sanPham.getGia());
        values.put("soluong", 1);
        values.put("id_ac", AccountSingle.getInstance().getAccount().getId());
        values.put("masp", sanPham.getMasp());
        sqLiteDatabase.insert("GIOHANG", null, values);
        sqLiteDatabase.close();
        return true;
    }

}