import 'package:flutter/cupertino.dart';
import 'package:medibuddy/screens/register.dart';
import 'package:medibuddy/widgets/intro_button.dart';
import 'package:medibuddy/widgets/intro_indicator.dart';

class IntroNavigatorBar extends StatelessWidget {
  const IntroNavigatorBar({
    super.key,
    required this.controller,
    required this.isLastPage,
  });
  final PageController controller;
  final bool isLastPage;

  @override
  Widget build(BuildContext context) {
    return Positioned(
      bottom: 15,
      left: 0,
      right: 0,
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            IntroButton(
              controller: controller,
              text: "Prev",
              onPressed: () {
                controller.previousPage(
                  duration: Duration(milliseconds: 300),
                  curve: Curves.easeIn,
                );
              },
            ),

            const SizedBox(width: 5),
            IntroIndicator(controller: controller),

            const SizedBox(width: 10),
            isLastPage
                ? IntroButton(
                  controller: controller,
                  text: "Register",
                  onPressed: () {
                    Navigator.pushReplacement(
                      context,
                      CupertinoPageRoute(builder: (context) => const Register()),
                    );
                  },
                )
                : IntroButton(
                  controller: controller,
                  text: "Next",
                  onPressed: () {
                    controller.nextPage(
                      duration: Duration(milliseconds: 300),
                      curve: Curves.easeIn,
                    );
                  },
                ),
          ],
        ),
      ),
    );
  }
}
