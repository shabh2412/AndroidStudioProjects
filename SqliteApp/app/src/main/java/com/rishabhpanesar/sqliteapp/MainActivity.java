package com.rishabhpanesar.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText etName, etLName, etMarks;
    Button add, btnViewAll, btn_update, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        etName = findViewById(R.id.editTextName);
        etLName = findViewById(R.id.lName);
        etMarks = findViewById(R.id.marks);
        add = findViewById(R.id.button_add);
        btnViewAll = findViewById(R.id.btnViewAll);
        btn_update = findViewById(R.id.btn_Update);
        btn_delete = findViewById(R.id.btn_delete);
        addData();
        viewAll();
        update();
        deleteData();
    }

    public void addData() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(etName.getText().toString(), etLName.getText().toString(), etMarks.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void viewAll() {
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = myDb.getAll();
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No data Available", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("\tId\t:\t" + cursor.getString(0) + "\n");
                    buffer.append("\tFirst Name\t:\t" + cursor.getString(1) + "\n");
                    buffer.append("\tLast Name\t:\t" + cursor.getString(2) + "\n");
                    buffer.append("\tMarks\t:\t" + cursor.getString(3) + "\n\n");
                }
//                show all data
                Intent intent = new Intent(MainActivity.this, DatabaseData.class);
                intent.putExtra("data", buffer.toString());
//                showMsg("Data", buffer.toString());
                startActivity(intent);
            }
        });
    }

    public void showMsg(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void update() {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText idET = new EditText(MainActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Enter ID");
                builder.setView(idET);
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Id = idET.getText().toString();
                        Boolean updated = false;
                        updated = myDb.update(Id, etName.getText().toString(), etLName.getText().toString(), etMarks.getText().toString());
                        if (updated) {
                            Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Some error occured", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    public void deleteData() {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText idET = new EditText(MainActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirmation Dialog");
                builder.setMessage("Enter the id of the entry which you want to delete");
                builder.setView(idET);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = idET.getText().toString();
                        if(myDb.delete(id)){
                            Toast.makeText(MainActivity.this, "Delete Operation successful", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }
}