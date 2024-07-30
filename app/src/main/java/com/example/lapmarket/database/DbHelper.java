package com.example.lapmarket.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "LapMarket", null, 42);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String account = "CREATE TABLE ACCOUNT(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " hoten TEXT, " +
                "matkhau TEXT," +
                "email TEXT," +
                "loaitaikhoan TEXT)";
        db.execSQL(account);

        db.execSQL("INSERT INTO ACCOUNT VALUES (1,'admin','admin','anhlabachu2004@gmail.com', 'admin')," +
                "(2,'admin','admin','admin', 'admin')," +
                "(3,'user1','user1','user1', 'user')," +
                "(4,'user2','user2','user2', 'user')");


        String sanpham_home = "CREATE TABLE SANPHAM(masp INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tensp TEXT ," +
                "gia INTEGER, " +
                "thuonghieu TEXT, " +
                "xuatxu TEXT, " +
                "kichthuocmanhinh TEXT, " +
                "mausac TEXT, " +
                "trongluong TEXT, " +
                "chatlieu TEXT, " +
                "cpu TEXT, " +
                "ocung TEXT, " +
                "ram TEXT, " +
                "rom TEXT, " +
                "card TEXT, " +
                "tocdocpu TEXT, " +
                "congusb TEXT, " +
                "vantay TEXT  )";
        db.execSQL(sanpham_home);

        db.execSQL("INSERT INTO SANPHAM VALUES (1,'Acer Nitro 5',15900000, 'Acer', 'Trung Quốc', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Không')," +
                "(2,'Asus Vivo Book',16900000, 'Asus', 'Trung Quốc', '15.6 inch', 'Đen', '1.7 kg', 'Nhôm', 'Ryzen 5 7530U', 'SSD', '8GB', '256GB', 'Iris Xe', '4.3GHz', '3 cổng', 'Không'),"  +
                "(3,'Lenovo Thinkpad',18900000, 'Lenovo', 'Nga', '15.6 inch', 'Trắng', '2.8 kg', 'Nhựa', 'Intel i3 13400H', 'HDD', '12GB', '512GB', 'GTX 1680', '2.7GHz', '3 cổng', 'Có'),"  +
                "(4,'Acer Aspire 3',8690000, 'Acer', 'Triều Tiên', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Ryzen 5 10530H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.2GHz', '3 cổng', 'Không'),"  +
                "(5,'MSI Modern 15',13790000, 'MSI', 'Trung Quốc', '15.6 inch', 'Đỏ', '3.1 kg', 'Nhựa', 'Intel i7 10400H', 'HDD', '8GB', '256GB', 'RTX 1030', '2.1GHz', '2 cổng', 'Có'),"  +
                "(6,'Macbook Air 2020',17690000, 'Apple', 'Nhật Bản', '13.3 inch', 'Trắng', '1.5 kg', 'Nhôm', 'Intel i5 11400H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Không'),"  +
                "(7,'Macbook Air 2022',25450000, 'Apple', 'Việt Nam', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Không'),"  +
                "(8,'Macbook Pro 16',57500000, 'Apple', 'Thái Lan', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '12GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Có')" );

        String sanpham_gaming = "CREATE TABLE GAMING(masp INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tensp TEXT , " +
                "gia INTEGER, " +
                "thuonghieu TEXT, " +
                "xuatxu TEXT, " +
                "kichthuocmanhinh TEXT, " +
                "mausac TEXT, " +
                "trongluong TEXT, " +
                "chatlieu TEXT, " +
                "cpu TEXT, " +
                "ocung TEXT, " +
                "ram TEXT, " +
                "rom TEXT, " +
                "card TEXT, " +
                "tocdocpu TEXT, " +
                "congusb TEXT, " +
                "vantay TEXT)";
        db.execSQL(sanpham_gaming);

        db.execSQL("INSERT INTO GAMING VALUES (1,'Acer Nitro Gaming',15900000, 'Acer', 'Trung Quốc', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Không')," +
                "(2,'Asus TUF Gaming',16900000, 'Asus', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không'),"  +
                "(3,'MSI Thin GF63 Gaming',18900000, 'MSI', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không'),"  +
                "(4,'Acer Aspire 3',15600000, 'Acer', 'Việt Nam', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i3 12500H', 'HDD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không'),"  +
                "(5,'Nitro 5 Tiger',23900000, 'Acer', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Có'),"  +
                "(6,'Nitro 5 2022',17690000, 'Acer', 'Việt Nam', '15.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '8GB', '256GB', 'RTX 3050', '2.7GHz', '3 cổng', 'Không'),"  +
                "(7,'HP Victus Gaming',15900000, 'HP', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không'),"  +
                "(8,'Lenovo Thinkpad Gaming',17900000, 'Lenovo', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không')");


        String sanpham_vanphong = "CREATE TABLE VANPHONG(masp INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tensp TEXT , " +
                "gia INTEGER, " +
                "thuonghieu TEXT, " +
                "xuatxu TEXT, " +
                "kichthuocmanhinh TEXT, " +
                "mausac TEXT, " +
                "trongluong TEXT, " +
                "chatlieu TEXT, " +
                "cpu TEXT, " +
                "ocung TEXT, " +
                "ram TEXT, " +
                "rom TEXT, " +
                "card TEXT, " +
                "tocdocpu TEXT, " +
                "congusb TEXT, " +
                "vantay TEXT)";
        db.execSQL(sanpham_vanphong);

        db.execSQL("INSERT INTO VANPHONG VALUES (1,'HP Pavilion 15',16800000, 'HP', 'Việt Nam', '15.6 inch', 'Đen', '1.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Có')," +
                "(2,'HP 240 G9 ',15790000, 'HP', 'Trung Quốc', '14.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không'),"  +
                "(3,'Dell Inspiron T7430',21690000, 'Dell', 'Việt Nam', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không'),"  +
                "(4,'Dell Vostro',16990000, 'Dell', 'Trung Quốc', '15.6 inch', 'Đen', '2.3 kg', 'Nhựa', 'Intel i7 12500H', 'SSD', '16GB', '512GB', 'RTX 3050', '3.2GHz', '3 cổng', 'Không'),"  +
                "(5,'Asus Zenbook 14',24590000,  'Asus', 'Trung Quốc', '13.6 inch', 'Đen', '1.7 kg', 'Nhôm', 'Ryzen 5 7530H', 'SSD', '16GB', '256GB', 'Iris Xe', '4.3GHz', '3 cổng', 'Có'),"  +
                "(6,'Zenbook 14X OLED',18790000,  'Asus', 'Việt Nam', '13.6 inch', 'Đen', '1.7 kg', 'Nhôm', 'Ryzen 5 7530U', 'HDD', '32GB', '256GB', 'Iris Xe', '4.3GHz', '3 cổng', 'Không'),"  +
                "(7,'Macbook Air 2020',17690000, 'Apple', 'Thái Lan', '13.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Intel i5 11400H', 'SSD', '64GB', '256GB', 'RTX 4050', '2.7GHz', '3 cổng', 'Có'),"  +
                "(8,'Macbook Air 2022',17690000, 'Apple', 'Việt Nam', '16.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Apple m1', 'SSD', '12GB', '512GB', 'RTX 6050', '5.7GHz', '3 cổng', 'Có'),"  +
                "(9,'Macbook Pro 16',57000000, 'Apple', 'Thái Lan', '12.6 inch', 'Đen', '2.5 kg', 'Nhựa', 'Appple m2', 'HDD', '12GB', '256GB', 'RTX 1050', '4.7GHz', '3 cổng', 'Có')");


        String sanpham_phukien = "CREATE TABLE PHUKIEN (mapk INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenpk TEXT , " +
                "gia INTEGER, " +
                "dungluong TEXT, " +
                "loairam TEXT, " +
                "hotro TEXT, " +
                "voltage TEXT, " +
                "busram TEXT, " +
                "hangsanxuat TEXT)";
        db.execSQL(sanpham_phukien);

        db.execSQL("INSERT INTO PHUKIEN VALUES (1,'RAM Laptop Kingston',570000, '8GB', 'DDR4', 'SO-DIMM(Laptop)', '1.2V', '3200MHz', 'Kingston')," +
                " (2,'RAM Laptop Lexar',490000, '8GB', 'DDR4', 'SO-DIMM(Laptop)', '1.2V', '3200MHz','Lexar')," +
                " (3,'RAM Laptop Samsung',650000, '8GB', 'DDR4', 'SO-DIMM(Laptop)', '1.2V', '3200MHz','Samsung')," +
                " (4,'RAM Laptop Samsung',1090000, '16GB', 'DDR4', 'SO-DIMM(Laptop)', '1.2V', '3200MHz','Samsung')," +
                " (5,'RAM PC T-Force',750000, '8GB', 'DDR4', 'DIMM(PC, Destop)', '1.35V', '3200MHz','Hãng khác')," +
                " (6,'RAM Laptop Kingston',890000, '8GB', 'DDR5', 'SO-DIMM(Laptop)', '1.1V', '4800MT/s','Kingston')," +
                " (7,'RAM Laptop Samsung',790000, '8GB', 'DDR5', 'SO-DIMM(Laptop)', '1.1V', '4800MT/s','Samsung')," +
                " (8,'RAM Laptop Kingston',1490000, '16GB', 'DDR5', 'SO-DIMM(Laptop)', '1.1V', '3200MHz','Kingston')");


        String giohang = "CREATE TABLE GIOHANG(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tensp TEXT REFERENCES SANPHAM(tensp), " +
                "gia INTEGER REFERENCES SANPHAM(gia),  " +
                "soluong INTEGER, " +
                "id_ac INTEGER REFERENCES ACCOUNT(id), " +
                "masp INTEGER REFERENCES SANPHAM(masp))";
        db.execSQL(giohang);





        String hoadon_sanpham = "CREATE TABLE HOADON (mahd INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoten TEXT REFERENCES ACCOUNT(hoten), " +
                "tensp TEXT REFERENCES GIOHANG(tensp),  " +
                "soluong INTEGER REFERENCES GIOHANG(soluong),  " +
                "gia INTEGER REFERENCES GIOHANG(gia)," +
                "ngaymua TEXT," +
                "trangthai INTEGER, id_ac_hd INTEGER REFERENCES ACCOUNT(id) )";
        db.execSQL(hoadon_sanpham);


        String chitiet_hoadon = "CREATE TABLE CTHOADON (mahdct INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sdt TEXT,  " +
                "namsinh TEXT, " +
                "diachi TEXT, " +
                "hoten TEXT REFERENCES ACCOUNT(hoten))";
        db.execSQL(chitiet_hoadon);



//



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("drop table if exists ACCOUNT");
            db.execSQL("drop table if exists SANPHAM");
            db.execSQL("drop table if exists GAMING");
            db.execSQL("drop table if exists VANPHONG");
            db.execSQL("drop table if exists PHUKIEN");
            db.execSQL("drop table if exists GIOHANG");
            db.execSQL("drop table if exists HOADON");
            db.execSQL("drop table if exists CTHOADON");
            onCreate(db);
        }
    }

    public void resetLocalData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("HOADON", null, null);
        db.delete("GIOHANG", null, null);
    }
}
