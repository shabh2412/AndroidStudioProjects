package com.rishabhpanesar.fragmenttutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button fragmentBtn, buttonPopFragment, buttonRemoveFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TextView tvFragmentCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentBtn = (Button) findViewById(R.id.fragmentBtn);
        tvFragmentCount = (TextView) findViewById(R.id.tvFragmentCount);
        fragmentManager = getSupportFragmentManager();
        tvFragmentCount.setText("Fragment Count in Backstack " + fragmentManager.getBackStackEntryCount());
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                tvFragmentCount.setText("Fragment Count in Backstack " + fragmentManager.getBackStackEntryCount());
            }
        });

        buttonPopFragment = (Button) findViewById(R.id.buttonPopFragment);
        buttonRemoveFragment = (Button) findViewById(R.id.buttonRemoveFragment);
        fragmentBtn.setOnClickListener(this);
        buttonRemoveFragment.setOnClickListener(this);
        buttonPopFragment.setOnClickListener(this);
//        addFragment();
    }






    private void addFragment(){
        Fragment fragment;
        /*switch (fragmentManager.getBackStackEntryCount()) {
            case 1: fragment = new FragmentTwo();break;
            case 2: fragment = new FragmentThree();break;
            default: fragment = new SampleFragment();break;
        }*/
        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment instanceof SampleFragment) {
            fragment = new FragmentTwo();
        } else if (fragment instanceof FragmentTwo) {
            fragment = new FragmentThree();
        } else {
            fragment = new SampleFragment();
        }
        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragmentContainer, fragment, "demoFragment");
        fragmentTransaction.replace(R.id.fragmentContainer, fragment, "demoFragment");
        fragmentTransaction.addToBackStack(null);  // adds to back stack, so that when we click back, the app doesn't close.
        fragmentTransaction.commit();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragmentBtn: addFragment();
                break;
            case R.id.buttonPopFragment: fragmentManager.popBackStack();
                break;
            case R.id.buttonRemoveFragment:
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
                if (fragment != null) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.remove(fragment);
                    fragmentTransaction.addToBackStack(null);  // adds to back stack, so that when we click back, the app doesn't close.
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(getApplicationContext(),"No Fragment To Remove",Toast.LENGTH_SHORT).show();;
                }
                break;

        }
    }
}