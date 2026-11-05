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
    private Button btnSignInScreen;
    private Button btnRegisterScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //بناء قاعدة بيانات وارجاع مؤشر عليها1
        AppDataBase db = AppDataBase.getDB(getApplicationContext());
        //2 مؤشر لكائن عمليات لجدول
        mySubjectQuery subjectQuery = db.getMySubjectQuery();
        //3 بناء كائن من نوع الجدول وتحديد قيم الصفات
        MySubject s1 = new MySubject();
        s1.setTitle("Math");
        MySubject s2 = new MySubject();
        s2.title = "Computers";
        //4 اضافة كائن للجدول
        subjectQuery.insert(s1);
        subjectQuery.insert(s2);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSignInScreen = findViewById(R.id.btnSignInScreen);
        btnRegisterScreen = findViewById(R.id.btnRegisterScreen);

        btnSignInScreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this, signIn.class);
                startActivity(i);
            }
        });

        btnRegisterScreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ActivityRegister.class);
                startActivity(i);
            }
        });
    }

    @Override // بناء القائمة
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override // معالجة حدث اختيار عنصر من القائمة
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
