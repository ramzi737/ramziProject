package com.ramzifah.newproject.viewPkg;

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
import androidx.lifecycle.ViewModelProvider;

import com.ramzifah.newproject.R;
import com.ramzifah.newproject.model.MyUserTable.MyUser;
import com.ramzifah.newproject.utils.Valedate;
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
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // ربط متغيرات الجافا بعناصر الواجهة في ملف الـ XML باستخدام الـ ID
        textView = findViewById(R.id.textView);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);
        btnRegister = findViewById(R.id.btnRegister);
        btnToSignIn = findViewById(R.id.btnToSignIn);
        btnRegister.setOnClickListener(view -> {signUpUser();});

        // برمجة حدث الضغط (OnClick) على زر الانتقال إلى شاشة تسجيل الدخول
        btnToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // إنشاء Intent (نية انتقال) من شاشة التسجيل الحالية إلى شاشة تسجيل الدخول (signIn)
                Intent intent = new Intent(ActivityRegister.this, signIn.class);
                startActivity(intent); // تنفيذ الانتقال
            }
        });
    }
    private void signUpUser () {
        // 1. استخراج فحوى الحقول
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String name = etFullName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        // 2. فحص سلامة المدخلات
        if(!Valedate.isValidEmail(email))
       {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }
        if (Valedate.isValidPassword(password)) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }
        if (!Valedate.isValidName(name)) {
            etFullName.setError("name is required");
            etFullName.requestFocus();
            return;
        }
        if (!Valedate.isValidPhone(phone)){
            etPhone.setError("phone is required");
            etPhone.requestFocus();
            return;
        }

        // 3. إنشاء كائن مستخدم جديد وتعبئة كافة بياناته باستخدام الـ Setters
        MyUser myUser=new MyUser();
        myUser.setEmail(email);
        myUser.setPassw(password);
        myUser.setFullName(name);
        myUser.setPhone(phone);

        // 4. إدراج المستخدم في قاعدة البيانات عبر الـ UserViewModel والانتقال إلى شاشة تسجيل الدخول
        userViewModel.insert(myUser);
        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ActivityRegister.this, signIn.class);
        startActivity(intent);
        finish();
    }
}
