import 'package:easy_localization/easy_localization.dart';
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
              text: "prev".tr(),
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
                  text: "register".tr(),
                  onPressed: () {
                    ///// حاجه جديده علشان احدد اتجاه النافيجيت
                    Navigator.push(
                      context,
                      PageRouteBuilder(
                        transitionDuration: Duration(milliseconds: 600),
                        pageBuilder: (_, __, ___) => Register(),
                        transitionsBuilder: (
                          context,
                          animation,
                          secondaryAnimation,
                          child,
                        ) {
                          var begin = Offset(
                            1.0,
                            0.0,
                          ); // بداية الحركة من اليمين زي ال cupertino
                          var end = Offset.zero;
                          var curve = Curves.easeInOut;

                          var tween = Tween(
                            begin: begin,
                            end: end,
                          ).chain(CurveTween(curve: curve));

                          return SlideTransition(
                            position: animation.drive(tween),
                            child: child,
                          );
                          /////////////////
                        },
                      ),
                    );
                  },
                )
                : IntroButton(
                  controller: controller,
                  text: "next".tr(),
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
