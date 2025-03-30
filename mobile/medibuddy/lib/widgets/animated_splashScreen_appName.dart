import 'package:flutter/material.dart';
import 'package:medibuddy/widgets/app_name_logo.dart';

class AnimatedSplashscreenAppname extends StatelessWidget {
  const AnimatedSplashscreenAppname({super.key, required this.animate});
  final bool animate;

  @override
  Widget build(BuildContext context) {
    return AnimatedPositioned(
      duration: Duration(seconds: 2),
      curve: Curves.bounceOut,
      top: animate ? 300 : -100,
      left: 50,
      right: 50,
      child: AppNameLogo(),
    );
  }
}
