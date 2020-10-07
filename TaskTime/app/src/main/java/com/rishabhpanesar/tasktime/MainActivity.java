package com.rishabhpanesar.tasktime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> tasks = new ArrayList<>();
    String currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView myTasksView = (ListView) findViewById(R.id.myTasksView);
        final EditText enterNewTask = (EditText) findViewById(R.id.enterNewTask);
        final TextView chosen_item = (TextView) findViewById(R.id.chosen_item);
        final LinearLayout taskOption = (LinearLayout) findViewById(R.id.taskOption);
        Button add_task_button = (Button) findViewById(R.id.add_task_button);
        Button delete_task_button = (Button) findViewById(R.id.delete_task_button);
//        tasks.add("Android HW");
//        tasks.add("SE HW");
//        tasks.add("ASP.NET HW");
//        tasks.add("Blockchain HW");
        if(tasks.isEmpty()){
            Toast.makeText(getApplicationContext(), "No Tasks Added!", Toast.LENGTH_LONG).show();
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);
                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tv.setTextSize(20);
                tv.setPadding(40,4,0,4);
                // Generate ListView Item using TextView
                return view;
            }
        };
        myTasksView.setAdapter(arrayAdapter);
        add_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }

            private void addTask() {
                if (enterNewTask.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "No Task to Add!", Toast.LENGTH_LONG).show();
                } else {
                    String task = enterNewTask.getText().toString();
                    tasks.add(task);
                    myTasksView.setAdapter(arrayAdapter);
                    enterNewTask.setText("");
                    enterNewTask.clearFocus();
                }
            }
        });
        myTasksView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String taskName = ((TextView) view).getText().toString();
                currentTask = taskName;
                chosen_item.setText(taskName);
                taskOption.setVisibility(View.VISIBLE);

            }
        });

        delete_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskOption.setVisibility(View.GONE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                try {

                    alertDialogBuilder.setMessage("Mark " + currentTask + " as Done?");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tasks.remove(currentTask);
                            arrayAdapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "Completed " + currentTask, Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), currentTask + " Removed", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "Operation Cancelled", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}