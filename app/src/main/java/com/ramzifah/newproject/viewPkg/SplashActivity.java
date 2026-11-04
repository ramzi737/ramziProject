package com.ramzifah.newproject.viewPkg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.ramzifah.newproject.R;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//مؤقت التأخير (Handler)
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //هون هدول السطرين من اجل تحديد الانتقال من شاشه الى اخرى
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);//
                finish();//يقوم باغلاق وحذف شاشه ال splashscreen من ذاكرة التخزين المؤقت
            }
        }, SPLASH_DELAY);
    }
}
