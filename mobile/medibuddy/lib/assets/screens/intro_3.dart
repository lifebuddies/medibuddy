import 'package:flutter/material.dart';
import 'package:medibuddy/widgets/animated_app_name_logo.dart';
import 'package:medibuddy/widgets/animated_letsGo_message_robot.dart';
import 'package:medibuddy/widgets/animated_letsGo_messeage.dart';

class Intro3 extends StatefulWidget {
  const Intro3({super.key});

  @override
  State<Intro3> createState() => _Intro3State();
}

class _Intro3State extends State<Intro3> {
  bool _animate = false; // متغير لتشغيل الأنيميشن

  @override
  void initState() {
    super.initState();
    Future.delayed(Duration(milliseconds: 500), () {
      setState(() {
        _animate = true;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // الخلفية
          Positioned.fill(
            child: Image.asset("lib/assets/images/2.png", fit: BoxFit.cover),
          ),

          // الصورة اللي تنزل من فوق
          AnimatedAppNameLogo(animate: _animate),

          // النص اللي يظهر تدريجيًا
          AnimatedLetsgoMesseage(animate: _animate),

          // الروبوت اللي يطلع من تحت
          AnimatedLetsgoMessageRobot(animate: _animate),
        ],
      ),
    );
  }
}
