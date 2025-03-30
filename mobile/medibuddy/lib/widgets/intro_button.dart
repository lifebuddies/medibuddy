import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class IntroButton extends StatelessWidget {
  const IntroButton({super.key, required this.controller, required this.text, required this.onPressed});
  final PageController controller;
  final String text;
  final VoidCallback onPressed;

  @override
  Widget build(BuildContext context) {
    return TextButton(
      style: ElevatedButton.styleFrom(
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(30)),
        padding: const EdgeInsets.symmetric(horizontal: 15, vertical: 15),
        backgroundColor: Colors.transparent,
      ),
      onPressed:onPressed,
      child: Text(
        text,
        style: TextStyle(
          fontSize: 14,
          fontWeight: FontWeight.bold,
          color: Color.fromARGB(225, 26, 95, 183),
          fontFamily: GoogleFonts.merriweather().fontFamily,
        ),
      ),
    );
  }
}
