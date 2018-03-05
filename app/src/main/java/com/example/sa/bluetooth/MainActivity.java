package com.example.sa.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA=BluetoothAdapter.getDefaultAdapter();

    public void turnBluetoothOff(View v)
    {
        BA.disable();
        if(BA.isEnabled())
        {
            Toast.makeText(getApplicationContext(),"Bluetooth could not be turned off",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Bluetooth is turned off", Toast.LENGTH_LONG).show();

        }
    }

    public void findDiscoverableDevices(View v)
    {
        Intent i= new Intent (BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        BA.getBluetoothLeScanner();
        startActivity(i);
    }
    public void viewPairedDevices(View v)
    {
        Set<BluetoothDevice> PairedDevices=BA.getBondedDevices();
        ListView PairedDevicesListView =(ListView) findViewById(R.id.ListView);
        ArrayList pairedDevicesArrayList = new ArrayList();
        for (BluetoothDevice d : PairedDevices){
        pairedDevicesArrayList.add(d.getName());


        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,pairedDevicesArrayList);
        PairedDevicesListView.setAdapter(arrayAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1= (Button) findViewById(R.id.button);

        Button b2= (Button) findViewById(R.id.button2);

        Button b3= (Button) findViewById(R.id.button3);
        ListView PairedDevicesListView =(ListView) findViewById(R.id.ListView);
        if(BA.isEnabled())
        {
            Toast.makeText(getApplicationContext(),"bluetooth is on",Toast.LENGTH_LONG).show();
        }
        else
        {

            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);
            if(BA.isEnabled())
            {
                Toast.makeText(getApplicationContext(),"bluetooth is on",Toast.LENGTH_LONG).show();
            }
        }
    }
}
