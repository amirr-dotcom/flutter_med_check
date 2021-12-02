package com.devnation.flutter_med_check;


import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;

import com.getmedcheck.lib.MedCheck;
import com.getmedcheck.lib.MedCheckActivity;
import com.getmedcheck.lib.model.BleDevice;
import com.getmedcheck.lib.model.IDeviceData;

import java.util.ArrayList;


class  Synth extends MedCheckActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDeviceConnectionStateChange(BleDevice bleDevice, int status) {
        super.onDeviceConnectionStateChange(bleDevice, status);
        Log.d("My Device State", String.valueOf(status));

        if (bleDevice.getMacAddress().equals(bleDevice.getMacAddress()) && status == 1) {


        }
    }





    private String macAd="18:7A:93:6E:41:55";

    MedCheck _medCheck= new MedCheck();



    void start() {
        Log.d("myTag", "Started........");
    }

    void stop() {

    }

    int keyDown(int key) {
        Log.d("myTag", "Key Pressd........");
        return 50;
    }

    int keyUp(int key) {
        Log.d("myTag", "Key Pressd........");
        return 0;
    }

//    void connect(String macAddress) {
//
//        _medCheck.connect(getApplication().getApplicationContext(), "18:7A:93:6E:41:55");
//   // _medCheck.connect( getApplication().getApplicationContext(),  '18:7A:93:6E:41:55');
//
//        Log.d("myTag", "No Connected........");
//       // return 0;
//    }

    //connectDevice




    void connect(String macAddress) {

        Log.d("myTag", "Yesss Connected........");
        _medCheck.connect(App.context,macAd );

        Log.d("myTag", "Noooo Connected........");
       // readData("18:7A:93:6E:41:55");
    }


    void disConnect() {
        Log.d("myTag", "Yesss DisConnected........");
        _medCheck.disconnectDevice(App.context);

    }



    void list(){

    }

    private void readData(String macAddress) {

        _medCheck.writeCommand(App.context, macAddress);
    }

    protected void onDeviceDataReceive(BluetoothDevice device, ArrayList<IDeviceData> deviceData, String jsonString, String deviceType) {
        Log.e("TAG", "onDeviceDataReceive: " + jsonString);
    }


}