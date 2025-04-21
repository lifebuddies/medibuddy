import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

// ignore: must_be_immutable
class CustomElevatedPageButton extends StatelessWidget {
  CustomElevatedPageButton({
    this.onPressed,
    super.key,
    required this.title,
    // required this.color,
  });
  final String title;
  VoidCallback? onPressed;
  // final Color color;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 300,
      child: ElevatedButton(
        onPressed: onPressed,
        style: ElevatedButton.styleFrom(
          backgroundColor: Color.fromARGB(225, 26, 95, 183),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(25),
          ),
          minimumSize: const Size(double.infinity, 50),
        ),
        child: Text(
          title,
          style: TextStyle(
            fontSize: 18,
            color: Colors.white,
            fontFamily: GoogleFonts.merriweather().fontFamily,
          ),
        ),
      ),
    );
  }
}
