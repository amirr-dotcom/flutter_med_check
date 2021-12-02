package com.devnation.flutter_med_check;






import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** FlutterMedCheckPlugin */
public class FlutterMedCheckPlugin implements FlutterPlugin, MethodCallHandler {
    private MethodChannel channel;
    private Synth synth;

    private static final String channelName = "flutter_med_check";

    private static void setup(FlutterMedCheckPlugin plugin, BinaryMessenger binaryMessenger) {
        plugin.channel = new MethodChannel(binaryMessenger, channelName);
        plugin.channel.setMethodCallHandler(plugin);
        plugin.synth = new Synth();
        plugin.synth.start();
    }


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        setup(this, flutterPluginBinding.getBinaryMessenger());
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("onKeyDown")) {
            try {
                ArrayList arguments = (ArrayList) call.arguments;
                int numKeysDown = synth.keyDown((Integer) arguments.get(0));
                result.success(numKeysDown);
            } catch (Exception ex) {
                result.error("1", ex.getMessage(), ex.getStackTrace());
            }
        } else if (call.method.equals("onKeyUp")) {
            try {
                ArrayList arguments = (ArrayList) call.arguments;
                int numKeysDown = synth.keyUp((Integer) arguments.get(0));
                result.success(numKeysDown);
            } catch (Exception ex) {
                result.error("1", ex.getMessage(), ex.getStackTrace());
            }
        }
        else if (call.method.equals("connect")) {
            try {
                ArrayList arguments = (ArrayList) call.arguments;
                Log.d("Connect Array", arguments.toString());
                Log.d("Connect Array", arguments.get(0).toString());
                    synth.connect((String) arguments.get(0));
            //    result.success(1);
            } catch (Exception ex) {

                Log.d("This One", ex.toString());
                result.error("1", ex.getMessage(), ex.getStackTrace());
            }
        }

        else if (call.method.equals("disConnect")) {
            try {

                synth.disConnect();
                //    result.success(1);
            } catch (Exception ex) {
                result.error("1", ex.getMessage(), ex.getStackTrace());
            }
        }


        else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }
}


