package com.rishabhpanesar.myintents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class secondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search(view);
            }
        });
    }

    private void search(View view) {
        EditText searchText = (EditText) findViewById(R.id.searchTerm);
        Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
        String term = searchText.getText().toString();
        i.putExtra(SearchManager.QUERY, term);
        startActivity(i);
    }
}