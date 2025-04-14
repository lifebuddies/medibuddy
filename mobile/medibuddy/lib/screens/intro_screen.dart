import 'package:flutter/material.dart';
import 'package:medibuddy/screens/intro_1.dart';
import 'package:medibuddy/screens/intro_2.dart';
import 'package:medibuddy/widgets/intro_navigator_bar.dart';

// ignore: must_be_immutable
class IntroScreen extends StatefulWidget {
  const IntroScreen({super.key});

  @override
  State<IntroScreen> createState() => _IntroScreenState();
}

class _IntroScreenState extends State<IntroScreen> {
  PageController controller = PageController();

  bool isLastPage = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          PageView(
            controller: controller,
            onPageChanged: (ind) {
              setState(() {});
              if (ind == 1) {
                isLastPage = true;
              } else {
                isLastPage = false;
              }
            },

            children: [const Intro1(), const Intro2()],
          ),
          IntroNavigatorBar(controller: controller, isLastPage: isLastPage),
        ],
      ),
    );
  }
}
