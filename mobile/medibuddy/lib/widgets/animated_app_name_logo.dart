import 'package:flutter/material.dart';
import 'package:medibuddy/widgets/app_name_logo.dart';

 class AnimatedAppNameLogo extends StatelessWidget {
  const AnimatedAppNameLogo({super.key, required this.animate});
   final  bool animate; // متغير لتشغيل الأنيميشن


    @override

  Widget build(BuildContext context) {
    return AnimatedPositioned(
      duration: Duration(seconds: 1),
      curve: Curves.easeOut,
      top: animate ? 220 : -100, // يبدأ من فوق (-100) وينزل لـ 180
      left: MediaQuery.of(context).size.width / 2 - 180, // توسيط الصورة
      child: AppNameLogo(),
    );
  }
  

}
