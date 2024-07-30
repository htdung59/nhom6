package com.example.lapmarket.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Amount {

        // hàm định dạng tiền trong lập trình dùng lớp number format dùng hàm locale định dạng vị trí việt nam
        public static String moneyFormat(int price){
            Locale localeVN = new Locale("vi", "VN");
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);
            return numberFormat.format(price);
        }




}
