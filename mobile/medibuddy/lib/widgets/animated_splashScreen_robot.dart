import 'package:flutter/material.dart';

class AnimatedSplashscreenRobot extends StatelessWidget {
  const AnimatedSplashscreenRobot({super.key, required this.animate});
  final bool animate;

  @override
  Widget build(BuildContext context) {
    return Center(
      child: AnimatedOpacity(
        duration: Duration(seconds: 2), // مدة الفيد إن
        curve: Curves.easeIn, // منحنى ناعم للظهور التدريجي
        opacity: animate ? 1.0 : 0.0, // يبدأ مختفي ويظهر تدريجياً
        child: Image.asset(
          'lib/assets/images/robot_2.png',
          height: 300,
          width: 300,
        ),
      ),
    );
  }
}
