package com.rishabhpanesar.mydialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void open(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This will end the activity");
        builder.setPositiveButton("I agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Interesting", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "Activity ended", Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(view, "Operation Cancelled", Snackbar.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "NAH", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}