import 'package:flutter/material.dart';
import 'package:medibuddy/widgets/animated_buble.dart';
import 'package:medibuddy/widgets/animated_messege_intro_box.dart';
import 'package:medibuddy/widgets/animated_robot_intro1,2.dart';
import 'package:medibuddy/widgets/app_name_logo.dart';
import 'package:medibuddy/widgets/app_subtitle.dart';

class Intro2 extends StatelessWidget {
  const Intro2({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // الخلفية
          Positioned.fill(
            child: Image.asset('lib/assets/images/22.png', fit: BoxFit.cover),
          ),
          const AnimatedBuble(
            top: 380,
            left: -90,
            image: 'lib/assets/images/puble2.png',
            height: 200,
            width: 200,
          ),
          const AnimatedBuble(
            top: 770,
            left: 320,
            image: 'lib/assets/images/puble2.png',
            height: 100,
            width: 100,
          ),

          // المحتوى الأساسي
          Center(
            child: Column(
              children: [
                const SizedBox(height: 165),
                const AppNameLogo(),
                AppSubtitle(),
                const SizedBox(height: 180),
              ],
            ),
          ),
          AnimatedMessegeIntroBox(
            messege:
                "Let's start your journey to a healthier you! \n\nI will be your guider through every step of the way.",
          ),

          // الروبوت اللي يدور تدريجيًا
          AnimatedRobotIntro_1_2(robotImage: 'lib/assets/images/robot_4.png'),
        ],
      ),
    );
  }
}
