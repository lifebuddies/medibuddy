// import 'package:flutter/cupertino.dart';
// import 'package:flutter/material.dart';
// import 'package:flutter_zoom_drawer/flutter_zoom_drawer.dart';
// import 'package:medibuddy/consts.dart';
// import 'package:medibuddy/widgets/app_name_logo.dart';
// import 'package:medibuddy/widgets/message_card.dart';

// class MainAppScreen extends StatelessWidget {
//   const MainAppScreen({super.key});

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       // ✅ Drawer هنا
//       body: Stack(
//         children: [
//           // الخلفية
//           Container(
//             decoration: BoxDecoration(
//               image: DecorationImage(
//                 image: AssetImage(
//                   'lib/assets/images/$themeColor/AppBackGround.png',
//                 ),
//                 fit: BoxFit.cover,
//               ),
//             ),
//           ),

//           // اللوجو
//           Positioned(
//             top: 20,
//             left: 100,
//             child: SizedBox(height: 100, width: 200, child: AppNameLogo()),
//           ),

//           // زرار المنيو
//           Positioned(
//             top: 15,
//             left: -10,
//             child: Builder(
//               builder:
//                   (context) => MaterialButton(
//                     onPressed: () {
//                       ZoomDrawer.of(context)?.toggle(); // تصحيح استدعاء toggle
//                     },
//                     child: SizedBox(
//                       height: 100,
//                       child: Icon(Icons.menu, size: 30, color: Colors.black),
//                     ),
//                   ),
//             ),
//           ),
//           Positioned(child: PageView(
//   controller: PageController(viewportFraction: 0.9),
//   children: [
//     MessageCard(
//       text: "Hi! I'm Sanjo, I'm here to help you reach a healthy life style",
//       imagePath: 'lib/assets/images/sanjo.png',
//     ),
//     MessageCard(
//       text: "Don't forget to drink water regularly!",
//       imagePath: 'lib/assets/images/water.png',
//     ),
//     MessageCard(
//       text: "Time to get moving, let's do some stretches!",
//       imagePath: 'lib/assets/images/stretch.png',
//     ),
//   ],
// ))
//         ],
//       ),
//     );
//   }
// }
import 'package:flutter/material.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/widgets/app_name_logo.dart';
import 'package:medibuddy/widgets/drawer_button.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';
import 'package:medibuddy/widgets/message_card.dart';

class MainAppScreen extends StatefulWidget {
  const MainAppScreen({super.key});

  @override
  State<MainAppScreen> createState() => _MainAppScreenState();
}

class _MainAppScreenState extends State<MainAppScreen> {
  final _pageController = PageController(viewportFraction: 1);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // الخلفية
          Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                image: AssetImage(
                  'lib/assets/images/$themeColor/AppBackGround.png',
                ),
                fit: BoxFit.cover,
              ),
            ),
          ),

          // اللوجو
          Positioned(
            top: 40,
            left: 100,
            child: SizedBox(height: 100, width: 200, child: AppNameLogo()),
          ),

          // زرار المنيو
          CustomDrawerButton(),

          // الـ Container الكبير الشفاف
          Positioned(
            top: 140,
            left: 20,
            right: 20,
            child: Container(
              height: 180,
              padding: const EdgeInsets.symmetric(vertical: 10),
              decoration: BoxDecoration(
                // ignore: deprecated_member_use
                color: Colors.white.withOpacity(0.1),
                borderRadius: BorderRadius.circular(20),
              ),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  // PageView
                  SizedBox(
                    height: 150,
                    child: PageView(
                      controller: _pageController,
                      children: [
                        Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 5),
                          child: MessageCard(
                            text:
                                "Hi! I'm Sanjo, I'm here to help you reach a healthy life style",
                            imagePath: 'lib/assets/images/robot_1.png',
                          ),
                        ),

                        Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 5),
                          child: MessageCard(
                            text: "Don't forget to drink water regularly!",
                            imagePath: 'lib/assets/images/robot_2.png',
                          ),
                        ),
                        Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 5),
                          child: MessageCard(
                            text:
                                "Time to get moving, let's do some stretches!",
                            imagePath: 'lib/assets/images/robot_3.png',
                          ),
                        ),
                      ],
                    ),
                  ),

                  // المؤشر من تحت
                  SmoothPageIndicator(
                    controller: _pageController,
                    count: 3,
                    effect: const ExpandingDotsEffect(
                      activeDotColor: Colors.blue,
                      dotColor: Colors.white54,
                      dotHeight: 8,
                      dotWidth: 5,
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
