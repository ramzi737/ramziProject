package com.ramzifah.newproject.viewPkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ramzifah.newproject.R;
import com.ramzifah.newproject.viewmodel.UserViewModel;

public class ActivityRegister extends AppCompatActivity {
    // تعريف متغيرات عناصر الواجهة (النصوص، حقول إدخال بيانات المستخدم، وأزرار التسجيل والانتقال)
    private TextView textView;
    private EditText etFullName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPhone;
    private Button btnRegister;
    private Button btnToSignIn;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // تفعيل العرض من الحافة للحافة
        setContentView(R.layout.activity_register); // ربط الكود بملف التصميم الخاص بالتسجيل

        // ضبط الهوامش تلقائياً لمنع تداخل المحتوى مع أشرطة النظام
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ربط متغيرات الجافا بعناصر الواجهة في ملف الـ XML باستخدام الـ ID
        textView = findViewById(R.id.textView);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);
        btnRegister = findViewById(R.id.btnRegister);
        btnToSignIn = findViewById(R.id.btnToSignIn);
    }
    private void Saveuser ()
    {
        // 1. استخراج فحوى الحقول

    }
}
