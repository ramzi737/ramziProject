package com.ramzifah.newproject.viewPkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ramzifah.newproject.R;
import com.ramzifah.newproject.model.AppDataBase;
import com.ramzifah.newproject.model.MySubjectTable.MySubject;
import com.ramzifah.newproject.model.MySubjectTable.mySubjectQuery;

public class MainActivity extends AppCompatActivity
{
    // تعريف أزرار التنقل للشاشات (زر تسجيل الدخول وزر إنشاء حساب)
    private Button btnSignInScreen;
    private Button btnRegisterScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // تفعيل وضع العرض من الحافة للحافة

        // 1. بناء قاعدة البيانات واسترجاع مؤشر عليها
        AppDataBase db = AppDataBase.getDB(getApplicationContext());
        
        // 2. أخذ مؤشر لكائن العمليات الخاص بجدول المواضيع (DAO)
        mySubjectQuery subjectQuery = db.getMySubjectQuery();
        
        // 3. إنشاء كائنات جديدة لمواضيع وتحديد قيم خصائصها
        MySubject s1 = new MySubject();
        s1.setTitle("Math");
        MySubject s2 = new MySubject();
        s2.title = "Computers";
        
        // 4. إدراج المواضيع تلقائياً في قاعدة البيانات عند فتح التطبيق
        subjectQuery.insert(s1);
        subjectQuery.insert(s2);

        setContentView(R.layout.activity_main); // ربط الكود بملف التصميم المرئي XML

        // ضبط الهوامش تلقائياً لتناسب أشرطة النظام (System Bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ربط أزرار الواجهة بمعرفاتها (IDs) في ملف الـ XML
        btnSignInScreen = findViewById(R.id.btnSignInScreen);
        btnRegisterScreen = findViewById(R.id.btnRegisterScreen);

        // برمجة حدث الضغط على زر تسجيل الدخول للانتقال لشاشة الـ Sign In
        btnSignInScreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // إنشاء Intent (نية انتقال) من النشاط الحالي إلى نشاط تسجيل الدخول
                Intent i = new Intent(MainActivity.this, signIn.class);
                startActivity(i); // بدء الانتقال
            }
        });

        // برمجة حدث الضغط على زر إنشاء حساب للانتقال لشاشة التسجيل
        btnRegisterScreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                // إنشاء Intent (نية انتقال) من النشاط الحالي إلى نشاط التسجيل
                Intent i = new Intent(MainActivity.this, ActivityRegister.class);
                startActivity(i); // بدء الانتقال
            }
        });
    }

    @Override // دالة لإنشاء وبناء قائمة الخيارات العلويّة (Options Menu)
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu); // نفخ ملف الـ menu وربطه بالشاشة
        return true;
    }

    @Override // دالة لمعالجة الحدث عند النقر على أي عنصر من قائمة الخيارات العلويّة
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // فحص العنصر الذي تم الضغط عليه حسب معرّفه (ID) وإظهار رسالة Toast قصيرة
        if (item.getItemId() == R.id.itmSettings)
        {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.itmSignout)
        {
            Toast.makeText(this, "Sign out", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.itmHistory)
        {
            Toast.makeText(this, "History", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
