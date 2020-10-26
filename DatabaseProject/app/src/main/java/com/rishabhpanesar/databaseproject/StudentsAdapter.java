package com.rishabhpanesar.databaseproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class StudentsAdapter extends ArrayAdapter<Students> {
    public StudentsAdapter(Context context, ArrayList<Students> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Students students = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_layout, parent, false);
        }
        TextView nameTv = convertView.findViewById(R.id.nameTV);
        TextView rollTv = convertView.findViewById(R.id.rollTV);
        TextView branchTv = convertView.findViewById(R.id.branchTV);
        TextView marksTV = convertView.findViewById(R.id.marksTV);

        nameTv.setText(students.Stu_Name);
        rollTv.setText(students.Stu_Roll);
        branchTv.setText(students.Stu_Department);
        marksTV.setText(students.Stu_Marks);

        CardView cardView = convertView.findViewById(R.id.cardBox);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView rNo = v.findViewById(R.id.rollTV);
                final String rollNo = rNo.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.create();
                builder.setTitle("Delete Entry");
                builder.setMessage("This operation will delete the selected entry from Database");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean delStat = delete(v.getContext(), rollNo);
                        Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_SHORT);
                        snackbar.getView().setBackgroundColor(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        if (delStat) {
                            snackbar.setText("Deleted Successfully");
                            StudentsAdapter.this.remove(students);
                            StudentsAdapter.this.notifyDataSetChanged();
                            if (StudentsAdapter.this.isEmpty()) {

                            }
                        } else {
                            snackbar.setText("Some Error Occurred");
                        }
                        snackbar.show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(v, "Operation Cancelled", Snackbar.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        return convertView;
    }


    private boolean delete(Context context, String roll) {
        DbHelper myDb = new DbHelper(context);
        if (myDb.delete(roll)) {
            myDb.close();
            return true;
        }
        myDb.close();
        return false;
    }
}
