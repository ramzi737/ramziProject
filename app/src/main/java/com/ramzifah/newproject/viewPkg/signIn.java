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

public class signIn extends AppCompatActivity {

    // تعريف المتغيرات الخاصة بعناصر الواجهة (النصوص، حقول الإدخال، والأزرار)
    private TextView textView;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignIn;

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
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // إنشاء Intent (نية الانتقال) من الشاشة الحالية إلى شاشة إضافة المهام
                Intent intent = new Intent(signIn.this, ActivityAddTask.class);
                startActivity(intent); // تنفيذ عملية الانتقال للشاشة الجديدة
            }
        });
    }
}
