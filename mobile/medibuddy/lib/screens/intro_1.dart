// import 'package:flutter/material.dart';
// import 'package:medibuddy/widgets/animated_messege_intro_box.dart';
// import 'package:medibuddy/widgets/animated_robot_intro1,2.dart';
// import 'package:medibuddy/widgets/app_name_logo.dart';
// import 'package:medibuddy/widgets/app_subtitle.dart';

// class Intro1 extends StatelessWidget {
//   const Intro1({super.key});

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       body: Stack(
//         children: [
//           // الخلفية
//           Positioned.fill(
//             child: Image.asset('lib/assets/images/2.png', fit: BoxFit.cover),
//           ),

//           // المحتوى الأساسي
//           Center(
//             child: Column(
//               children: [
//                 const SizedBox(height: 165),
//                 const AppNameLogo(),
//                 AppSubtitle(),
//                 const SizedBox(height: 180),
//               ],
//             ),
//           ),
//           AnimatedMessegeIntroBox(
//             messege:
//                 "Hello, I am your AI friend! \n I am here to help you \n with your health and wellness.",
//           ),

//           // الروبوت اللي يدور تدريجيًا
//           AnimatedRobotIntro_1_2(
//             robotImage: 'lib/assets/images/robot_1.png',
//           ),
//         ],
//       ),
//     );
//   }
// }
import 'package:flutter/material.dart';
import 'package:medibuddy/widgets/animated_buble.dart';
import 'package:medibuddy/widgets/animated_messege_intro_box.dart';
import 'package:medibuddy/widgets/animated_robot_intro1,2.dart';
import 'package:medibuddy/widgets/app_name_logo.dart';
import 'package:medibuddy/widgets/app_subtitle.dart';

class Intro1 extends StatelessWidget {
  const Intro1({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // الخلفية
          Positioned.fill(
            child: Image.asset('lib/assets/images/22.png', fit: BoxFit.cover),
          ),

          // البابل المتحرك
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
                "Hello, I am your AI friend! \n I am here to help you \n with your health and wellness.",
          ),

          AnimatedRobotIntro_1_2(robotImage: 'lib/assets/images/robot_1.png'),
        ],
      ),
    );
  }
}
