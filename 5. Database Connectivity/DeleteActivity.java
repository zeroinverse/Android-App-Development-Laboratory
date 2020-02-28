package com.example.databaseconnectivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    EditText del;
    Button submit;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        del = findViewById(R.id.code);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new Database(getApplicationContext());
                db.delete(Integer.parseInt(del.getText().toString()));

                Toast.makeText(DeleteActivity.this, "Deleted " + del.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
