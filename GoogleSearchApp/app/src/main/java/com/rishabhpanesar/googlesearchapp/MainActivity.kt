package com.rishabhpanesar.googlesearchapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchButton: Button = findViewById(R.id.search_button)
        searchButton.setOnClickListener(View.OnClickListener {
            searchGoogle(it)
        })
    }

    private fun searchGoogle(view: View) {
        TODO("Not yet implemented")
        val editText: EditText = findViewById(R.id.search_text)
        val url: String = editText.getText().toString()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}