// ignore: file_names
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class CustomCheckacountRow extends StatelessWidget {
  const CustomCheckacountRow({
    super.key,
    required this.question,
    required this.option,
    required this.onpressed,
  });
  final String question;
  final String option;
  final Function() onpressed;

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Text(
          question,
          style: TextStyle(fontFamily: GoogleFonts.merriweather().fontFamily),
        ),
        TextButton(
          onPressed: onpressed,
          child: Text(
            option,
            style: TextStyle(
              fontWeight: FontWeight.bold,
              color: Color.fromARGB(225, 26, 95, 183),
              fontFamily: GoogleFonts.merriweather().fontFamily,
            ),
          ),
        ),
      ],
    );
  }
}
