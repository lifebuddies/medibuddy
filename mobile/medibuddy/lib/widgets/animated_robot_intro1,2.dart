import 'package:flutter/material.dart';

class AnimatedRobotIntro_1_2 extends StatefulWidget {
  const AnimatedRobotIntro_1_2({super.key, required this.robotImage});
  final String robotImage;

  @override
  State<AnimatedRobotIntro_1_2> createState() => _AnimatedRobotIntroState();
}

class _AnimatedRobotIntroState extends State<AnimatedRobotIntro_1_2> {
  bool _animate = false; // متغير لتشغيل الأنيميشن

  @override
  void initState() {
    super.initState();
    Future.delayed(Duration(milliseconds: 500), () {
      if (mounted) {
        // تأكد من أن الودجت لا يزال موجودًا قبل تحديث الحالة
        setState(() {
          _animate = true;
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return AnimatedPositioned(
      duration: Duration(milliseconds: 500),
      curve: Curves.easeOut,
      right: _animate ? -160 : -250, // يبدأ من الشمال ويتحرك لمكانه الطبيعي
      bottom: 170,
      child: AnimatedOpacity(
        duration: Duration(milliseconds: 500),
        opacity: _animate ? 1.0 : 0.0, // يبدأ مخفي ويظهر تدريجيًا
        child: AnimatedRotation(
          duration: Duration(milliseconds: 500),
          curve: Curves.easeOut,
          turns: _animate ? -0.1 : 0.0, // يبدأ مستقيم وبعدها يدور شوية
          child: Image.asset(widget.robotImage, width: 350),
        ),
      ),
    );
  }
}
