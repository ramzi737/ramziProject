package com.ramzifah.newproject.viewPkg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.ramzifah.newproject.R;

public class SplashActivity extends AppCompatActivity {

    // متغير ثابت يحدد مدة ظهور شاشة الترحيب بالميللي ثانية (2500 = 2.5 ثانية)
    private static final long SPLASH_DELAY = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // ربط الكود بملف التصميم الخاص بشاشة الترحيب

        // استخدام الـ Handler لعمل تأخير زمني قبل الانتقال للشاشة التالية
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // إنشاء Intent (نية انتقال) من شاشة الترحيب (Splash) إلى الشاشة الرئيسية (MainActivity)
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                
                startActivity(intent); // تنفيذ أمر الانتقال وفتح الشاشة الرئيسية
                
                finish(); // إغلاق وحذف شاشة الترحيب تماماً لكي لا يرجع لها المستخدم عند الضغط على زر الرجوع
            }
        }, SPLASH_DELAY); // تمرير مدة التأخير
    }
}
