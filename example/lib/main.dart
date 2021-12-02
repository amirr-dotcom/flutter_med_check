import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_med_check/flutter_med_check.dart';

enum _KeyType { Black, White }

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  FlutterMedCheck med=FlutterMedCheck();

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    try {
      platformVersion =
          await FlutterMedCheck.platformVersion ?? 'Unknown platform version';
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  // void _onKeyDown(int key) {
  //   print("key down:$key");
  //   FlutterMedCheck.onKeyDown(key).then((value) => print(value));
  // }
  //
  // void _onKeyUp(int key) {
  //   print("key up:$key");
  //   FlutterMedCheck.onKeyUp(key).then((value) => print(value));
  // }

  //
  // Widget _makeKey({required _KeyType keyType, required int key}) {
  //   return AnimatedContainer(
  //     height: 200,
  //     width: 44,
  //     duration: const Duration(seconds: 2),
  //     curve: Curves.easeIn,
  //     child: Material(
  //       color: keyType == _KeyType.White
  //           ? Colors.white
  //           : const Color.fromARGB(255, 60, 60, 80),
  //       child: InkWell(
  //         onTap: () => _onKeyUp(key),
  //         onTapDown: (details) => _onKeyDown(key),
  //         onTapCancel: () => _onKeyUp(key),
  //       ),
  //     ),
  //   );
  // }


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(

        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Text('Running on: $_platformVersion\n'),
              TextButton(onPressed: (){
                med.connect(
                    macAdress: '192');
              }, child: Text('Connect')),
              SizedBox(height: 30,),

              TextButton(onPressed: (){
                med.disConnect();
              }, child: Text('DisConnect')),

              TextButton(onPressed: (){
                med.onKeyDown(2);
              }, child: Text('Key'))
            ],
          ),
        ),
      ),
    );
  }
}
