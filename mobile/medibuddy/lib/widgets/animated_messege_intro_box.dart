import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class AnimatedMessegeIntroBox extends StatefulWidget {
  const AnimatedMessegeIntroBox({super.key, required this.messege});
  final String messege;

  @override
  State<AnimatedMessegeIntroBox> createState() =>
      _AnimatedMessegeIntroBoxState();
}

class _AnimatedMessegeIntroBoxState extends State<AnimatedMessegeIntroBox> {
  bool _animate = false; // متغير لتشغيل الأنيميشن

  @override
  void initState() {
    super.initState();
    Future.delayed(Duration(milliseconds: 500), () {
      setState(() {
        _animate = true;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return AnimatedPositioned(
      duration: Duration(milliseconds: 500),
      curve: Curves.easeOut,
      top: 560, // مكان النص تحت الصورة شوية
      left:
          _animate
              ? MediaQuery.of(context).size.width / 2 - 140
              : -300, // يبدأ من اليسار ويتحرك
      child: AnimatedOpacity(
        duration: Duration(milliseconds: 500),
        opacity: _animate ? 1.0 : 0.0, // يبدأ مخفي ويظهر تدريجيًا
        child: Container(
          height: 176,
          width: 281,
          padding: EdgeInsets.all(16), // إضافة مسافة داخلية لترتيب النص
          decoration: BoxDecoration(
            color: Color.fromARGB(225, 26, 95, 183),
            borderRadius: BorderRadius.circular(30),
            border: Border.all(color: Colors.white, width: 3), // إضافة حد أبيض
            boxShadow: [
              BoxShadow(
                // ignore: deprecated_member_use
                color: Colors.black.withOpacity(0.3), // لون الظل
                spreadRadius: 2,
                blurRadius: 10,
                offset: Offset(0, 5), // الاتجاه للأسفل
              ),
            ],
          ),
          child: Center(
            child: Text(
              widget.messege,
              textAlign: TextAlign.center,
              style: TextStyle(
                color: Colors.white,
                fontSize: 14,
                fontWeight: FontWeight.bold,
                fontFamily: GoogleFonts.merriweather().fontFamily,
              ),
            ),
          ),
        ),
      ),
    );
  }
}
