package com.ramzifah.newproject.viewPkg;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ramzifah.newproject.R;

public class Activityhomescreen extends AppCompatActivity {
    // تعريف متغيرات عناصر الواجهة (عنوان الشاشة، حقول إدخال بيانات المهمة، وزر الحفظ)
    private TextView textView;
    private EditText etTitle;
    private EditText etDescription;
    private EditText etPriority;
    private Button btnSaveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // تفعيل وضع العرض من الحافة للحافة
        setContentView(R.layout.activity_homescreen); // ربط الكود بملف التصميم الخاص بإضافة المهمة

        // ضبط المسافات تلقائياً لكي لا تتداخل العناصر مع أشرطة النظام
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ربط متغيرات الجافا بعناصر الواجهة في ملف الـ XML باستخدام المعرّف (ID)
        textView = findViewById(R.id.textView);
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etPriority = findViewById(R.id.etPriority);
        btnSaveTask = findViewById(R.id.btnSaveTask);
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
            showYesNoDialog();
            return true;
        }
        if (item.getItemId() == R.id.itmHistory)
        {
            Toast.makeText(this, "History", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    /**
     * بناء ديالوج
     */
    public void showYesNoDialog()
    {
        //تجهيز بنّاء شباك حوار "ديالوغ" يتلقى بارمتر مؤشر للنشاط (اكتيفيتي) الحالي
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sign Out");// تحديد العنوان
        builder.setMessage("Are you sure?");// تحدي فحوى شباك الحوار
        //النص على الزر ومعالج الحدث
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //معالجة حدث للموافقة
                Toast.makeText(Activityhomescreen.this, "Signing out", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        //النص على الزر ومعالج الحدث
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //معالجة حدث للموافقة
                Toast.makeText(Activityhomescreen.this, "Signing out", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();//بناء شباك الحوار -ديالوغ
        dialog.show();//عرض الشباك
    }
}


