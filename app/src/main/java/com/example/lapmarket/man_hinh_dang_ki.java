package com.example.lapmarket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lapmarket.dao.AccountDAO;
import com.example.lapmarket.util.CheckEmail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class man_hinh_dang_ki extends AppCompatActivity {
    private AccountDAO accountDAO;
    private RadioGroup radioGroupRole;
    private RadioButton radioUser;
    private RadioButton radioAdmin;

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

        radioGroupRole = findViewById(R.id.radioGroupAccountType);
        radioUser = findViewById(R.id.radioUser);
        radioAdmin = findViewById(R.id.radioAdmin);

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
                String email = edt_email.getText().toString().trim();

                boolean emailValid = CheckEmail.validateEmail(email);
                int selectedRoleId = radioGroupRole.getCheckedRadioButtonId();
                String role = (selectedRoleId == R.id.radioUser) ? "user" : (selectedRoleId == R.id.radioAdmin) ? "admin" : "";

                if (!validate_matkhau(pass)) {
                    Toast.makeText(man_hinh_dang_ki.this, "Mật khẩu cần ít nhất 8 ký tự, và có ít nhất một kí tự viết hoa và một kí tự viết thường.", Toast.LENGTH_SHORT).show();
                    return; // Không gọi đăng ký nếu mật khẩu không hợp lệ
                }
                if (hoten.length() < 8) {
                    Toast.makeText(man_hinh_dang_ki.this, "Name cần ít nhất 8 kí tự !", Toast.LENGTH_SHORT).show();
                } else if (pass.isEmpty() || repass.isEmpty() || !emailValid || role.isEmpty()) {
                    Toast.makeText(man_hinh_dang_ki.this, "Không để trống dữ liệu, email không hợp lệ hoặc chưa chọn vai trò", Toast.LENGTH_SHORT).show();
                } else {
                    if (!pass.equals(repass)) {
                        Toast.makeText(man_hinh_dang_ki.this, "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
                    } else {
                        if (role.equals("user")) {
                            boolean check = accountDAO.signup(hoten, pass, email, role);
                            if (check) {
                                Toast.makeText(man_hinh_dang_ki.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(man_hinh_dang_ki.this, "Email đã được đăng kí", Toast.LENGTH_SHORT).show();
                            }
                        } else if (role.equals("admin")) {
                            showAdminContactDialog();
                        }
                    }
                }
            }
        });

    }

    private boolean validate_matkhau(String matkhau) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(matkhau);
        return matcher.matches();
    }

    private void showAdminContactDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Thông báo")
                .setMessage("Vui lòng liên hệ với quản lý để được cấp tài khoản admin.")
                .setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
