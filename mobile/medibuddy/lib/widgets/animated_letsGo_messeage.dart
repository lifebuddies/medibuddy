import 'package:flutter/material.dart';
// import 'package:google_fonts/google_fonts.dart';

class AnimatedLetsgoMesseage  extends StatelessWidget {
  const AnimatedLetsgoMesseage ({super.key, required this.animate});
  final bool animate ;

  @override
  Widget build(BuildContext context) {
    return  AnimatedPositioned(
            duration: Duration(seconds: 1),
            curve: Curves.easeOut,
            top: 320, // مكان النص تحت الصورة شوية
            left:
                animate
                    ? MediaQuery.of(context).size.width / 2 - 180
                    : -200, // يبدأ من الشمال
            child: AnimatedOpacity(
              duration: Duration(seconds: 1),
              opacity: animate ? 1.0 : 0.0, // يبدأ مخفي ويظهر تدريجيًا
              child: Container(
                height: 100,
                width: 170,
                decoration: BoxDecoration(
                  color: Color.fromARGB(225, 26, 95, 183),
                  borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(30),
                    topRight: Radius.circular(30),
                    bottomLeft: Radius.circular(30),
                  ),
                ),
                child: Center(
                  child: Text(
                    "Let's \n Go On!",
                    maxLines: 2,
                    textAlign: TextAlign.center,
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      // fontFamily: GoogleFonts.merriweather().fontFamily,
                    ),
                  ),
                ),
              ),
            ),
          );
  }
}