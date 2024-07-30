package com.example.lapmarket.dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.lapmarket.database.DbHelper;
import com.example.lapmarket.designPantter.AccountSingle;
import com.example.lapmarket.model.account;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountDAO {

    SharedPreferences sharedPreferences;

    DbHelper dbHelper;

    public AccountDAO(Context context) {
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", MODE_PRIVATE);

    }


    public ArrayList<account> selectALL_Account() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<account> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ACCOUNT", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new account(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean checkdn(String email, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ACCOUNT WHERE email = ? AND matkhau = ?",
                new String[]{email, matkhau});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", cursor.getString(3));
            editor.putString("loaitaikhoan", cursor.getString(4));
            editor.putString("hoten", cursor.getString(1));
            editor.commit();
            AccountSingle.getInstance().setAccount(new account(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)));
            return true;
        } else {
            return false;
        }
    }

    //singup
    public boolean signup(String hoten, String matkhau, String email) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("ACCOUNT", null, "email = ?",
                new String[]{email}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            sqLiteDatabase.close();
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten", hoten);
        contentValues.put("matkhau", matkhau);
        contentValues.put("email", email);
        try {
            long check = sqLiteDatabase.insertOrThrow("ACCOUNT", null, contentValues);
            dbHelper.resetLocalData();
            return check != -1;
        } catch (SQLiteConstraintException e) {
            return false;
        } finally {
            cursor.close();
            sqLiteDatabase.close();
        }
    }

    //quenmk
    public String fogotpass(String email) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT matkhau FROM ACCOUNT WHERE email = ? ", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        return "";
    }


    // doimk
    public boolean capNhatMatKhau(String email, String mathauCu, String matkhauMoi) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ACCOUNT WHERE email = ? AND matkhau = ? ",
                new String[]{email, mathauCu});
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", matkhauMoi);
            long check = sqLiteDatabase.update("ACCOUNT", contentValues, "email = ?", new String[]{email});
            if (check == -1) {
                return false;
            }
            return true;
        }
        return false;
    }



}


