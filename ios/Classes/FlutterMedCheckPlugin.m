#import "FlutterMedCheckPlugin.h"
#if __has_include(<flutter_med_check/flutter_med_check-Swift.h>)
#import <flutter_med_check/flutter_med_check-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_med_check-Swift.h"
#endif

@implementation FlutterMedCheckPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterMedCheckPlugin registerWithRegistrar:registrar];
   FlutterMethodChannel* channel = [FlutterMethodChannel
        methodChannelWithName:@"flutter_med_check"
              binaryMessenger:[registrar messenger]];
    PluginCodelabPlugin* instance = [[PluginCodelabPlugin alloc] init];
    [registrar addMethodCallDelegate:instance channel:channel];
}
@end

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  if ([@"getPlatformVersion" isEqualToString:call.method]) {
    result([@"iOS " stringByAppendingString:[[UIDevice currentDevice] systemVersion]]);
  } else {
    result(FlutterMethodNotImplemented);
  }
}

@Override
public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
  channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_med_check");
  channel.setMethodCallHandler(this);
}

@Override
public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
  if (call.method.equals("getPlatformVersion")) {
    result.success("Android " + android.os.Build.VERSION.RELEASE);
  } else {
    result.notImplemented();
  }
}
