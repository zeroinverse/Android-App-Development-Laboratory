package com.example.databaseconnectivity;

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
        Button bt_display = findViewById(R.id.bt_display);

        final EditText et_code = findViewById(R.id.code);
        final EditText et_name = findViewById(R.id.name);
        final TextView m_result = findViewById(R.id.result);

        db = new Database(getApplicationContext());


        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                db.insert(Integer.parseInt(et_code.getEditableText().toString()),
                        et_name.getEditableText().toString());
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.update(Integer.parseInt(et_code.getEditableText().toString()),
                        et_name.getEditableText().toString());
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(Integer.parseInt(et_code.getEditableText().toString()));
            }
        });

        bt_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor res = db.display(Integer.parseInt(et_code.getEditableText().toString()));
                    String mstemp = "";
                    while (res.moveToNext()) {
                        mstemp =  mstemp + res.getInt(0) + "---" +
                                res.getString(1) + "\n";
                    }
                    m_result.setText(mstemp);
                } catch (Exception e) {
                    m_result.setText("Nothing in here");
                }
            }
        });
    }
}
