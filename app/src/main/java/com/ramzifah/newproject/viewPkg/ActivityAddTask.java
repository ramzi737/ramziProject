package com.ramzifah.newproject.viewPkg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ramzifah.newproject.R;

public class ActivityAddTask extends AppCompatActivity {
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
        setContentView(R.layout.activity_add_task); // ربط الكود بملف التصميم الخاص بإضافة المهمة

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
}
