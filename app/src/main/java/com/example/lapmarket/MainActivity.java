package com.example.lapmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.lapmarket.dao.AccountDAO;
import com.example.lapmarket.frg.frg_cskh;
import com.example.lapmarket.frg.frg_gaming_doHoa;
import com.example.lapmarket.frg.frg_giohang;
import com.example.lapmarket.frg.frg_lichsu;
import com.example.lapmarket.frg.frg_phuKien;
import com.example.lapmarket.frg.frg_quanly_gaming;
import com.example.lapmarket.frg.frg_quanly_phukien;
import com.example.lapmarket.frg.frg_quanly_sanpham_home;
import com.example.lapmarket.frg.frg_quanly_vanphong;
import com.example.lapmarket.frg.frg_thongke_dt;
import com.example.lapmarket.frg.frg_trangchu;
import com.example.lapmarket.frg.frg_vanPhong_macbook;
import com.example.lapmarket.model.account;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private AccountDAO accountDAO;
    private ArrayList<account> list;

    DrawerLayout drawerLayout;
    NavigationView nav;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        accountDAO = new AccountDAO(this);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        View headerLayout = nav.getHeaderView(0);
        TextView txt_ten = headerLayout.findViewById(R.id.txt_ten);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trang Chủ");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        getSupportFragmentManager().beginTransaction().replace(R.id.frmNav, new frg_trangchu()).commit();


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.thoat){
                    showdialogDangXuat();
                }
                else if (item.getItemId()==R.id.trangchu) {
                    frg_trangchu frgTrangchu = new frg_trangchu();
                    replaceFragment(frgTrangchu);

                }


                else if (item.getItemId()==R.id.lapGaming_doHoa) {
                    frg_gaming_doHoa frgGamingDoHoa = new frg_gaming_doHoa();
                    replaceFragment(frgGamingDoHoa);
                }

                else if (item.getItemId()==R.id.lapVanPhong_macbook) {
                    frg_vanPhong_macbook frgVanPhongMacbook = new frg_vanPhong_macbook();
                    replaceFragment(frgVanPhongMacbook);
                }

//                else if (item.getItemId()==R.id.phuKien) {
//                    frg_phuKien frgPhuKien = new frg_phuKien();
//                    replaceFragment(frgPhuKien);
//                }

                else if (item.getItemId()==R.id.doimk) {
                    showDoiMK();
                }

                else if (item.getItemId()==R.id.cskh) {
                    frg_cskh frgCskh  = new frg_cskh();
                    replaceFragment(frgCskh);
                }

                else if (item.getItemId()==R.id.giohang) {
                    frg_giohang giohang = new frg_giohang();
                    replaceFragment(giohang);

                }

                else if (item.getItemId()==R.id.lichsu) {
                    frg_lichsu frgLichsu = new frg_lichsu();
                    replaceFragment(frgLichsu);
                }

//                else if (item.getItemId()==R.id.quanli_phukien) {
//
//                    frg_quanly_phukien frgQuanlyPhukien = new frg_quanly_phukien();
//                    replaceFragment(frgQuanlyPhukien);
//
//                }

                else if (item.getItemId()==R.id.quanli_sp_home) {

                    frg_quanly_sanpham_home frg_quanly_sanpham_home = new frg_quanly_sanpham_home();
                    replaceFragment(frg_quanly_sanpham_home);


                }

                else if (item.getItemId()==R.id.quanli_sp_gaming) {

                    frg_quanly_gaming frgQuanlyGaming = new frg_quanly_gaming();
                    replaceFragment(frgQuanlyGaming);


                }

                else if (item.getItemId()==R.id.quanli_sp_vanphong) {

                    frg_quanly_vanphong frg_quanly_vanphong = new frg_quanly_vanphong();
                    replaceFragment(frg_quanly_vanphong);


                }

                else if (item.getItemId()==R.id.thongKe_dt) {

                    frg_thongke_dt frgthongkedt = new frg_thongke_dt();
                    replaceFragment(frgthongkedt);

                }




                drawerLayout.closeDrawer(GravityCompat.START);  // Đóng NavigationView



                getSupportActionBar().setTitle(item.getTitle());
                return true;
            }
        });





        //hien thi chuc nang cho admin
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
        String loaitk = sharedPreferences.getString("loaitaikhoan", "");
        if (!loaitk.equals("admin")){
            Menu menu = nav.getMenu();
//            menu.findItem(R.id.quanli_phukien).setVisible(false);
            menu.findItem(R.id.quanli_sp_home).setVisible(false);
            menu.findItem(R.id.quanli_sp_gaming).setVisible(false);
            menu.findItem(R.id.quanli_sp_vanphong).setVisible(false);
            menu.findItem(R.id.thongKe_dt).setVisible(false);

        } else if (loaitk.equals("admin")) {
            Menu menu = nav.getMenu();
            menu.findItem(R.id.cskh).setVisible(false);
            menu.findItem(R.id.giohang).setVisible(false);
            menu.findItem(R.id.lichsu).setVisible(false);
            menu.findItem(R.id.lapGaming_doHoa).setVisible(false);
            menu.findItem(R.id.lapVanPhong_macbook).setVisible(false);
//            menu.findItem(R.id.phuKien).setVisible(false);
            menu.findItem(R.id.trangchu).setVisible(false);
        }

        //
        String hoten = sharedPreferences.getString("hoten", "");
        txt_ten.setText(hoten);


    }
    public void replaceFragment(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmNav,frg).commit();




    }

    private void showDoiMK(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_doimatkhau,null);


        EditText edt_mkCu = view.findViewById(R.id.edt_mkCu);
        EditText edt_mkMoi = view.findViewById(R.id.edt_mkMoi);
        EditText edt_nhapLai_mkMoi = view.findViewById(R.id.edt_NhapLai_mkMoi);

        builder.setView(view);



        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        builder.setNegativeButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mkCu = edt_mkCu.getText().toString().trim();
                String mkMoi = edt_mkMoi.getText().toString().trim();
                String nhaplai_mkMoi = edt_nhapLai_mkMoi.getText().toString().trim();

                if (mkCu.isEmpty() ||  nhaplai_mkMoi.isEmpty()){
                    Toast.makeText(MainActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!validate_matkhau(mkMoi)) {
                    Toast.makeText(MainActivity.this, "Mật khẩu cần ít nhất 8 ký tự, và có ít nhất một kí tự viết hoa và một kí tự viết thường.", Toast.LENGTH_SHORT).show();
                    return; // Không gọi đăng ký nếu mật khẩu không hợp lệ
                }


                if (mkMoi.equals(nhaplai_mkMoi)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);

                    String email = sharedPreferences.getString("email", "");
                    accountDAO  = new AccountDAO(MainActivity.this);
                    boolean check = accountDAO.capNhatMatKhau(email, mkCu, mkMoi);
                    if (check){
                        Toast.makeText(MainActivity.this, "Đổi mk thành công", Toast.LENGTH_SHORT).show();
                        finish();

                    }else {
                        Toast.makeText(MainActivity.this, "Đổi mk thất bại", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(MainActivity.this, "Nhập mk không trùng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }
    private boolean validate_matkhau(String matkhau) {
        // Biểu thức chính quy kiểm tra mật khẩu:
        // - Ít nhất 8 ký tự
        // - Ít nhất một chữ viết hoa
        // - Ít nhất một chữ viết thường
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$";

        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(matkhau);

        return matcher.matches();
    }



    private void showdialogDangXuat(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //tạo dối tượng
        builder.setIcon(R.drawable.canhbao); //set icon
        builder.setMessage("Bạn chắc chắn muốn thoát");


        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Toast.makeText(MainActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create(); //tạo hộp thoại
        dialog.show();
    }
 

}