package com.rishabhpanesar.employeereadwrite;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeDetails extends AppCompatActivity {
    TextView Name, Role, ID, Dept, Pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences("EMPLOYEE", MODE_PRIVATE);
        Name = findViewById(R.id.Name);
        Role = findViewById(R.id.Role);
        ID = findViewById(R.id.ID);
        Dept = findViewById(R.id.Dept);
        Pay = findViewById(R.id.Pay);
        Name.setText("Name:\t:\t" + sharedPreferences.getString("E_Name", "NA"));
        Role.setText("Role:\t:\t" + sharedPreferences.getString("E_Role", "NA"));
        ID.setText("ID\t:\t" + sharedPreferences.getString("E_Id", "NA"));
        Dept.setText("Department\t:\t" + sharedPreferences.getString("E_Dept", "NA"));
        Pay.setText("Salary\t:\t" + sharedPreferences.getString("E_Pay", "NA"));
        Toast.makeText(this, "Data Retrieved", Toast.LENGTH_SHORT).show();
    }
}