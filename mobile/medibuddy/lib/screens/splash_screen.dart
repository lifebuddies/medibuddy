import 'package:flutter/material.dart';
import 'package:medibuddy/screens/intro_screen.dart';
import 'package:medibuddy/widgets/animated_splashScreen_appName.dart';
import 'package:medibuddy/widgets/animated_splashScreen_robot.dart';

class SplashScreen extends StatefulWidget {
  const SplashScreen({super.key});

  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  bool _animate = false; // متغير لتشغيل الأنيميشن

  @override
  void initState() {
    super.initState();
    Future.delayed(Duration(milliseconds: 500), () {
      setState(() {
        _animate = true;
      });
    });
    Future.delayed(Duration(seconds: 5), () {
      Navigator.pushReplacement(
        // ignore: use_build_context_synchronously
        context,
        MaterialPageRoute(builder: (context) => IntroScreen()),
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // الخلفية
          SizedBox.expand(
            child: Image.asset("lib/assets/images/1.png", fit: BoxFit.cover),
          ),
          // اسم التطبيق
          AnimatedSplashscreenAppname(animate: _animate),
          //لروبوت اللي يطلع من تحت
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              SizedBox(height: 130), // مسافة فوق
              AnimatedSplashscreenRobot(animate: _animate),
            ],
          ),
        ],
      ),
    );
  }
}
