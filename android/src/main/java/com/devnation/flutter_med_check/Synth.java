package com.devnation.flutter_med_check;


import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;

import com.getmedcheck.lib.MedCheck;
import com.getmedcheck.lib.model.IDeviceData;

import java.util.ArrayList;


class Synth extends Application {
   MedCheck _medCheck= new MedCheck();

    private static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }



    void start() {

    }

    void stop() {

    }

    int keyDown(int key) {
        return 50;
    }

    int keyUp(int key) {
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

        _medCheck.connect(getApplication().getApplicationContext(), "18:7A:93:6E:41:55");
        Log.d("myTag", "Yesss Connected........");
        readData("18:7A:93:6E:41:55");
    }


    private void readData(String macAddress) {

        _medCheck.writeCommand(getApplication().getApplicationContext(), macAddress);
    }

    protected void onDeviceDataReceive(BluetoothDevice device, ArrayList<IDeviceData> deviceData, String jsonString, String deviceType) {
        Log.e("TAG", "onDeviceDataReceive: " + jsonString);
    }


}