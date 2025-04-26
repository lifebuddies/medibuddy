import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class CustomTextfield extends StatelessWidget {
  CustomTextfield({
    this.onChanged,
    super.key,
    required this.hintText,
    required this.icon,
    required this.keyboardType,
  });

  final String hintText;
  final IconData icon;
  final TextInputType keyboardType;
  Function(String)? onChanged;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 300,
      child: TextFormField(
        validator: (value) {
          if (value!.isEmpty) {
            return 'Field is required';
          }
          return null;
        },
        onChanged: onChanged,
        keyboardType: keyboardType,
        textInputAction: TextInputAction.next,
        style: TextStyle(
          fontFamily: GoogleFonts.merriweather().fontFamily,
          fontSize: 16,
          color: Colors.black87,
        ),
        decoration: InputDecoration(
          contentPadding: const EdgeInsets.symmetric(
            horizontal: 20,
            vertical: 18,
          ),
          prefixIcon: Icon(icon, color: Colors.grey.shade600),
          labelText: hintText,
          floatingLabelStyle: TextStyle(
            color: Colors.black,
            fontWeight: FontWeight.bold,
            fontFamily: GoogleFonts.merriweather().fontFamily,
            fontSize: 20,
          ),
          labelStyle: TextStyle(
            color: Colors.grey.shade600,
            fontFamily: GoogleFonts.merriweather().fontFamily,
            fontSize: 16,
          ),
          filled: true,
          fillColor: Colors.white,
          floatingLabelBehavior: FloatingLabelBehavior.auto,
          enabledBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(20),
            borderSide: BorderSide(color: Colors.blueGrey.shade200, width: 1.8),
          ),
          focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(20),
            borderSide: const BorderSide(
              color: Color.fromARGB(225, 26, 95, 183),
              width: 2.8,
            ),
          ),
          errorBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(20),
            borderSide: const BorderSide(color: Colors.red, width: 1.5),
          ),
          focusedErrorBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(20),
            borderSide: BorderSide(color: Colors.red.shade400, width: 2),
          ),
        ),
      ),
    );
  }
}

/* ignore: file_names
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

// ignore: must_be_immutable
class CustomTextfield extends StatelessWidget {
  CustomTextfield({
    this.onChanged,
    super.key,
    required this.hintText,
    required this.icon,
    required this.keyboardType,
  });
  final String hintText;
  final IconData icon;
  final TextInputType keyboardType;
  Function(String)? onChanged;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 300,
      child: TextFormField(
        validator: (value) {
          if (value!.isEmpty) {
            return 'Field is required';
          }
          return null;
        },
        onChanged: onChanged,
        keyboardType: keyboardType,
        textInputAction: TextInputAction.next,
        decoration: InputDecoration(
          prefixIcon: Icon(icon, color: Colors.grey),
          hintText: hintText,
          hintStyle: TextStyle(
            color: Colors.grey,
            fontSize: 16,
            fontFamily: GoogleFonts.merriweather().fontFamily,
          ),
          filled: true,
          fillColor: Colors.white,
          enabledBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(25),
            borderSide: const BorderSide(color: Colors.blueGrey, width: 2),
          ),
          focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(25),
            borderSide: const BorderSide(
              color: Color.fromARGB(225, 26, 95, 183),
              width: 4,
            ),
          ),
          errorBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(25),
            borderSide: const BorderSide(color: Colors.red, width: 2),
          ),
          focusedErrorBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(25),
            borderSide: const BorderSide(color: Colors.blueGrey, width: 4),
          ),
        ),
      ),
    );
  }
} */
