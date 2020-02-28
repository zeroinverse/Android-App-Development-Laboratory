package com.example.databaseconnectivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_insert = findViewById(R.id.bt_insert);
        Button bt_update = findViewById(R.id.bt_update);
        Button bt_delete = findViewById(R.id.bt_delete);
        Button bt_display_all = findViewById(R.id.bt_display_all);
        Button bt_display = findViewById(R.id.bt_display);

        final EditText et_code = findViewById(R.id.code);
        final TextView m_result = findViewById(R.id.result);


        db = new Database(getApplicationContext());


        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
//                db.insert(Integer.parseInt(et_code.getEditableText().toString()),
//                        et_name.getEditableText().toString());
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                db.update(Integer.parseInt(et_code.getEditableText().toString()),
//                        et_name.getEditableText().toString());
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(intent);
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });

        bt_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor res = db.display(Integer.parseInt(et_code.getEditableText().toString()));
                    String mstemp = "";
                    while (res.moveToNext()) {
//                       NAME VARCHAR, GENDER VARCHAR, CODE INT PRIMARY KEY, " +
//                "DEPARTMENT VARCHAR, SALARY VARCHAR
                        mstemp =  mstemp + res.getInt(2) + " - " +
                                res.getString(0) + " " +
                                res.getString(1) + " " +
                                res.getString(3) + " " +
                                "\n";
                    }
                    m_result.setText(mstemp);
                } catch (Exception e) {
                    m_result.setText(e.toString());
                }
            }
        });

        bt_display_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor res = db.display();
                    String mstemp = "";
                    while (res.moveToNext()) {
//                       NAME VARCHAR, GENDER VARCHAR, CODE INT PRIMARY KEY, " +
//                "DEPARTMENT VARCHAR, SALARY VARCHAR
                        mstemp =  mstemp + res.getInt(2) + " - " +
                                res.getString(0) + " " +
                                res.getString(1) + " " +
                                res.getString(3) + " " +
                                "\n";
                    }
                    m_result.setText(mstemp);
                } catch (Exception e) {
                    m_result.setText(e.toString());
                }
            }
        });
    }
}
