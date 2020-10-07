package com.rishabhpanesar.webviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.ETC1;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RadioGroup secure, domainExtension;
    RadioButton http, https, com, in, net, other;
    EditText websiteName, domainExtensionText;
    WebView webpage_loader;
    Boolean HTTP, HTTPS, COM, IN, NET;
    String customExtension, link, name;
    Button build_and_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secure = (RadioGroup) findViewById(R.id.secure);
        domainExtension = (RadioGroup) findViewById(R.id.domainExtension);
        websiteName = (EditText) findViewById(R.id.websiteName);
        domainExtensionText = (EditText) findViewById(R.id.domainExtensionText);
        http = (RadioButton) findViewById(R.id.http);
        https = (RadioButton) findViewById(R.id.https);
        com = (RadioButton) findViewById(R.id.com);
        in = (RadioButton) findViewById(R.id.in);
        net = (RadioButton) findViewById(R.id.net);
        other = (RadioButton) findViewById(R.id.other);
        webpage_loader = (WebView) findViewById(R.id.webpage_loader);
        build_and_load = (Button) findViewById(R.id.build_and_load);

        HTTPS = true;
        HTTP = false;

        COM = true;
        IN = false;
        NET = false;

        http.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HTTP = true;
                HTTPS = false;
            }
        });
        https.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HTTPS = true;
                HTTP = false;
            }
        });
        com.setOnClickListener(this);
        in.setOnClickListener(this);
        net.setOnClickListener(this);
        other.setOnClickListener(this);
        build_and_load.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.com:
                COM = true;
                IN = false;
                NET = false;
                break;
            case R.id.in:
                IN = true;
                COM = false;
                NET = false;
                break;
            case R.id.net:
                NET = true;
                COM = false;
                IN = false;
                break;
            case R.id.other:
                domainExtensionText.setVisibility(View.VISIBLE);
                NET = false;
                COM = false;
                IN = false;
                break;
            case R.id.build_and_load:
                if (websiteName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the site's name", Toast.LENGTH_SHORT).show();
                } else {
                    name = websiteName.getText().toString();
                }

                if (domainExtensionText.getVisibility() == View.VISIBLE) {
                    customExtension = domainExtensionText.getText().toString();
                    COM = false;
                    IN = false;
                    NET = false;
                }
                buildLink();
                view.setVisibility(View.GONE);
                webpage_loader.setVisibility(View.VISIBLE);
                webpage_loader.loadUrl(link);

        }
    }
    public void buildLink(){
        if(HTTP){
            link = "http://";
        } else {
            link = "https://";
        }
        link += name + ".";
        if (COM) {
            link += "com/";
        } else if (IN) {
            link += "in/";
        } else if (NET) {
            link += "net/";
        } else {
            link += customExtension + "/";
        }
    }
}