package com.example.lapmarket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lapmarket.dao.AccountDAO;
import com.example.lapmarket.designPantter.AccountSingle;
import com.example.lapmarket.util.SendMail;

import org.w3c.dom.Text;

public class man_hinh_dang_nhap extends AppCompatActivity {


    private AccountDAO accountDAO;
    private SendMail sendMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);

        accountDAO = new AccountDAO(this);
        sendMail = new SendMail();



        CheckBox  chk_remember = findViewById(R.id.chk_remember);

        TextView txt_signup  =findViewById(R.id.txt_signup);
        TextView txt_quenmk  =findViewById(R.id.txt_quenmatkhau);
        EditText edt_user = findViewById(R.id.edt_username);
        EditText edt_pass = findViewById(R.id.edt_password);
        Button btn_Login = findViewById(R.id.btn_login);

        AccountDAO accountDAO = new AccountDAO(this);

        //nho mk
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean rememberMe = sharedPreferences.getBoolean("remember_me", false);
        if (rememberMe) {
            String savedUsername = sharedPreferences.getString("email","" );
            String savedPassword = sharedPreferences.getString("matkhau","");
            chk_remember.setChecked(true);
            edt_user.setText(savedUsername);
            edt_pass.setText(savedPassword);
        }

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_user.getText().toString().trim();
                String pass = edt_pass.getText().toString();

                if (accountDAO.checkdn(user, pass)){


                    //
                    if (chk_remember.isChecked()) {
                        editor.putString("email", user);
                        editor.putString("matkhau", pass);
                        editor.putBoolean("remember_me", true);
                        editor.apply();
                    } else {
                        editor.clear();
                        editor.apply();
                    }

                    //
//                    editor.commit();


                    startActivity(new Intent(man_hinh_dang_nhap.this, MainActivity.class));


                }else {
                    Toast.makeText(man_hinh_dang_nhap.this, "Tài khoản hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                }

            }
        });


        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(man_hinh_dang_nhap.this, man_hinh_dang_ki.class));
            }
        });
        txt_quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogFogot();
            }
        });

    }


    //lấy lại mk
    private void dialogFogot(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_quen_mat_khau, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edt_email = view.findViewById(R.id.edt_email_fogot);
        Button btn_send = view.findViewById(R.id.btn_send_fogot);
        Button btn_huy = view.findViewById(R.id.btn_huy_fogot);


        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edt_email.getText().toString();
                String matkhau = accountDAO.fogotpass(email);

                if (matkhau.isEmpty()){
                    Toast.makeText(man_hinh_dang_nhap.this, "Email chưa được đăng kí", Toast.LENGTH_SHORT).show();
                }else {
                    sendMail.Send(man_hinh_dang_nhap.this, email, "Lấy lại mật khẩu LapMarket"," Mật khẩu của bạn là: "+ matkhau );
                    Toast.makeText(man_hinh_dang_nhap.this, "Vui lòng kiểm tra email để lấy lại mật khẩu", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            }
        });

    }


}