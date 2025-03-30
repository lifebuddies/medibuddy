import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class AppSubtitle extends StatelessWidget {
  const AppSubtitle({super.key});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 103,
      width: 240,
      child: Text(
        "Your AI Friend For Better Lifestyle",
        maxLines: 2,
        textAlign: TextAlign.center,
        style: TextStyle(
          color: Colors.white,
          fontSize: 20,
          fontWeight: FontWeight.bold,
          fontFamily: GoogleFonts.merriweather().fontFamily,
        ),
      ),
    );
  }
}
