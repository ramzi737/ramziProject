package com.ramzifah.newproject.viewPkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    private Button btn_Add_Task_Screen;
    private Button btn_Register_Screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //بناء قاعدة بيانات وارجاع مؤشر عليها1
        AppDataBase db=AppDataBase.getDB(getApplicationContext());
//2 مؤشر لكائن عمليات  لجدول
        mySubjectQuery subjectQuery = db.getMySubjectQuery();
//3  بناء كائن من نوع الجدول وتحديد قيم الصفات
        MySubject s1=new MySubject();
        s1.setTitle("Math");
        MySubject s2=new MySubject();
        s2.title="Computers";
//4 اضافة كائن للجدول
        subjectQuery.insert(s1);
        subjectQuery.insert(s2);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn_Add_Task_Screen=findViewById(R.id.btnAddTaskScreen);

        btn_Add_Task_Screen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this, ActivityAddTask.class);
                startActivity(i);
            }

        });
        btn_Register_Screen.findViewById(R.id.btnRegisterScreen);

        btn_Register_Screen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ActivityRegister.class);
                startActivity(i);
            }
        });
    }
}