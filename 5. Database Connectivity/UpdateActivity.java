package com.example.databaseconnectivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    String name, dept, gender, salary;
    int code;
    Spinner sp;
    RadioGroup rg;
    EditText ename, ecode, esalary;
    Button bt;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        try {
            this.getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

        db = new Database(getApplicationContext());

        ename = findViewById(R.id.name);
        esalary = findViewById(R.id.salary);
        ecode = findViewById(R.id.code);

        rg = findViewById(R.id.radioSex);
        sp = findViewById(R.id.departmentSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.departments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        bt = findViewById(R.id.submit);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = ename.getText().toString();
                salary = ename.getText().toString();
                code = Integer.parseInt(ecode.getText().toString());

                RadioButton radioSexButton = (RadioButton)
                        findViewById(rg.getCheckedRadioButtonId());
                String gender = radioSexButton.getText().toString();

                String department = sp.getSelectedItem().toString();

                db.update(code, name, gender, department, salary);

                Toast.makeText(UpdateActivity.this, "Updated " + code + " : " + name,
                        Toast.LENGTH_SHORT).show();
            }

        });

    }
}
