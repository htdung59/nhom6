package com.example.lapmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lapmarket.dao.AccountDAO;
import com.example.lapmarket.util.Amount;
import com.example.lapmarket.util.CheckEmail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class man_hinh_dang_ki extends AppCompatActivity {
    private AccountDAO accountDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_ki);

        accountDAO = new AccountDAO(this);
        TextView txt_back = findViewById(R.id.txt_back);


        EditText edt_hoten = findViewById(R.id.edt_hoten_dk);
        EditText edt_pass = findViewById(R.id.edt_password_dk);
        EditText edt_repass = findViewById(R.id.edt_repassword_dk);
        EditText edt_email = findViewById(R.id.edt_email_dk);
        Button btn_signup = findViewById(R.id.btn_signup_dk);



        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = edt_hoten.getText().toString().trim();
                String pass = edt_pass.getText().toString().trim();
                String repass = edt_repass.getText().toString().trim();


                boolean email = CheckEmail.validateEmail(edt_email.getText().toString().trim());


                if (!validate_matkhau(pass)) {
                    Toast.makeText(man_hinh_dang_ki.this, "Mật khẩu cần ít nhất 8 ký tự, và có ít nhất một kí tự viết hoa và một kí tự viết thường.", Toast.LENGTH_SHORT).show();
                    return; // Không gọi đăng ký nếu mật khẩu không hợp lệ
                }
                if (hoten.length() < 8){
                    Toast.makeText(man_hinh_dang_ki.this, "Name cần ít nhất 8 kí tự !", Toast.LENGTH_SHORT).show();

                }else if ( pass.isEmpty() || repass.isEmpty() || !email) {
                    Toast.makeText(man_hinh_dang_ki.this, "Không để trống dữ liệu hoặc email không hợp lệ", Toast.LENGTH_SHORT).show();
                } else {
                    if (!pass.equals(repass)) {
                        Toast.makeText(man_hinh_dang_ki.this, "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean check = accountDAO.signup(hoten, pass, edt_email.getText().toString());

                        if (check) {
                            Toast.makeText(man_hinh_dang_ki.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(man_hinh_dang_ki.this, "Email đã được đăng kí", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }
        });
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

}