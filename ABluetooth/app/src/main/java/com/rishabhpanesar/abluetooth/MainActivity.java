package com.rishabhpanesar.abluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.rishabhpanesar.abluetooth.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN}, PackageManager.PERMISSION_GRANTED);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        binding.onButton.setOnClickListener(v -> {
            if (bluetoothAdapter == null) {
                Snackbar snackbar = Snackbar.make(v, "Bluetooth Adapter Not Supported", Snackbar.LENGTH_SHORT);
                snackbar.show();
            } else {
                Intent bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(bluetoothIntent, 1);
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (bluetoothAdapter.isEnabled()) {
                    Snackbar.make(v, "Bluetooth Enabled", Snackbar.LENGTH_SHORT).show();
                    binding.bluetoothStatus.setText("Enabled");
                }
            }
        });
        binding.offButton.setOnClickListener(v -> {
            if (bluetoothAdapter == null) {
                Snackbar snackbar = Snackbar.make(v, "Bluetooth Adapter Not Supported", Snackbar.LENGTH_SHORT);
                snackbar.show();
            } else {
                bluetoothAdapter.disable();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!bluetoothAdapter.isEnabled()) {
                    Snackbar.make(v, "Bluetooth Disabled", Snackbar.LENGTH_SHORT).show();
                    binding.bluetoothStatus.setText("Disabled");
                }
            }
        });
        binding.pairedButton.setOnClickListener(v -> {
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                String x = "";
                for (BluetoothDevice device : pairedDevices) {
                    x += "\n" + device.getName() + "\n";
                }
//                binding.pairedDevices.setVisibility(View.VISIBLE);
                binding.pairedDevices.setText(x);
            }
        });
    }

    private void displayDevices(ArrayList<String> dName) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String list = "";
        for (String name :
                dName) {
            list += name;
            list += "\n";
        }
        builder.setMessage(list);
        builder.create();
    }
}