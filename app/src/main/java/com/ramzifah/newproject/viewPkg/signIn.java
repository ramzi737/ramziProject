package com.ramzifah.newproject.viewPkg;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ramzifah.newproject.R;
import com.ramzifah.newproject.model.MyUserTable.MyUser;
import com.ramzifah.newproject.viewmodel.UserViewModel;

public class signIn extends AppCompatActivity {

    // تعريف المتغيرات الخاصة بعناصر الواجهة (النصوص، حقول الإدخال، والأزرار)
    private TextView textView;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignIn;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // تفعيل وضع العرض من الحافة للحافة للشاشة
        setContentView(R.layout.activity_sign_in); // ربط الكود بملف التصميم المرئي XML

        // تعديل الحوامش والهوامش تلقائياً لتناسب أشرطة النظام (الكاميرا وأزرار التنقل)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ربط متغيرات الجافا بعناصر الواجهة في ملف الـ XML باستخدام المعرّف (ID)
        textView = findViewById(R.id.textView);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        // برمجة حدث الضغط (OnClick) على زر تسجيل الدخول
        btnSignIn.setOnClickListener(view -> signInUser());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        /**
         * يقوم بالتحقق من صحة الحقول المدخلة، استدعاء عملية تسجيل الدخول،
         * عند النجاح MainActivity والانتقال ل Toast وإدارة النتيجة (نجاح أو فشل) عبر
         */

    }
    private void signInUser ()
    {
        // 1. استخراج فحوى الحقول
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // 2. فحص سلامة المدخلات
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        // 3. استعمال العملية login التابعة لـ UserViewModel الخاص بالمستعمل
        // MyUser والتي تعيد null إن لم يكن المستعمل موجودا حسب الايميل وكلمة السر
        MyUser user = userViewModel.login(email, password);

        if (user != null) {
            Toast.makeText(this, "Welcome, " + user.fullName, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(signIn.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }
}


