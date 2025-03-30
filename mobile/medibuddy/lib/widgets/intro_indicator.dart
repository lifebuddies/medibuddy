import 'package:flutter/material.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';

class IntroIndicator extends StatelessWidget {
  const IntroIndicator({super.key, required this.controller});
  final PageController controller;

  @override
  Widget build(BuildContext context) {
    return SmoothPageIndicator(
      controller: controller,
      count: 3,
      effect: const WormEffect(
        activeDotColor: Color.fromARGB(225, 26, 95, 183),
        dotHeight: 10,
        dotWidth: 15,
        spacing: 10,
      ),
    );
  }
}
