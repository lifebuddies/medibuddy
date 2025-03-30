import 'package:flutter/material.dart';

class AnimatedLetsgoMessageRobot extends StatelessWidget {
  const AnimatedLetsgoMessageRobot({super.key, required this.animate});

 final bool animate ;

  @override
  Widget build(BuildContext context) {
    return AnimatedPositioned(
            duration: Duration(seconds: 1),
            curve: Curves.easeOut,
            bottom: animate ? 145 : -300, // يبدأ من تحت (-300) ويطلع لـ 180
            right: -50,
            child: Transform(
              alignment: Alignment.center,
              transform: Matrix4.identity()..scale(-1.0, 1.0), // انعكاس
              child: Image.asset('lib/assets/images/robot_3.png', width: 350),
            ),
          );
  }
}