package com.rishabhpanesar.employeereadwrite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtRole, txtId, txtDept, txtPay;
    Button btnSubmit, load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.txtName);
        txtId = findViewById(R.id.txtID);
        txtRole = findViewById(R.id.txtRole);
        txtDept = findViewById(R.id.txtDept);
        txtPay = findViewById(R.id.txtPay);

        btnSubmit = findViewById(R.id.btnSubmit);
        load = findViewById(R.id.btnLoad);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, id, role, dept, pay;
                SharedPreferences sharedPreferences = getSharedPreferences("EMPLOYEE", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int count = 0;
                if (txtName.getText().toString().isEmpty()) {
                    Toast.makeText(view.getContext(), "Enter a name", Toast.LENGTH_SHORT).show();
                    count = 0;
                } else {
                    editor.putString("E_Name", txtName.getText().toString());
                    editor.apply();
                    count++;
                }
                if (txtId.getText().toString().isEmpty()) {
                    Toast.makeText(view.getContext(), "Enter an ID", Toast.LENGTH_SHORT).show();
                    count = 0;
                } else {
                    editor.putString("E_Id", txtId.getText().toString());
                    editor.apply();
                    count++;
                }
                if (txtRole.getText().toString().isEmpty()) {
                    Toast.makeText(view.getContext(), "Enter your Role!!", Toast.LENGTH_SHORT).show();
                    count = 0;
                } else {
                    editor.putString("E_Role", txtRole.getText().toString());
                    editor.apply();
                    count++;
                }
                if (txtDept.getText().toString().isEmpty()) {
                    Toast.makeText(view.getContext(), "Enter your Department!!", Toast.LENGTH_SHORT).show();
                    count = 0;
                } else {
                    editor.putString("E_Dept", txtDept.getText().toString());
                    editor.apply();
                    count++;
                }
                if (txtPay.getText().toString().isEmpty()) {
                    Toast.makeText(view.getContext(), "Enter your Salary!!", Toast.LENGTH_SHORT).show();
                    count = 0;
                } else {
                    editor.putString("E_Pay", txtPay.getText().toString());
                    editor.apply();
                    count++;
                }
                if (count == 5) {
                    Intent intent = new Intent(MainActivity.this, EmployeeDetails.class);
                    startActivity(intent);
                }
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EmployeeDetails.class);
                startActivity(intent);
            }
        });
    }
}