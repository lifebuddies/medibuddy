import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

// ignore: must_be_immutable
class CustomPasswordTextfield extends StatefulWidget {
  CustomPasswordTextfield({this.onChanged, super.key, required this.labelText});
  Function(String)? onChanged;
  final String labelText;

  @override
  State<CustomPasswordTextfield> createState() =>
      _CustomPasswordTextfieldState();
}

class _CustomPasswordTextfieldState extends State<CustomPasswordTextfield> {
  IconData icon = Icons.visibility_off;
  bool obscureText = true;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 330,
      child: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          gradient: const LinearGradient(
            colors: [
              Color.fromARGB(224, 18, 66, 129), // الأزرق
              Colors.white,
              Colors.white,
              Colors.white,
              Colors.white,
              Colors.white,
              Colors.white,
              Colors.white,
              Colors.white,
              Colors.white,
              Colors.white,
              Colors.white,
              Color.fromARGB(224, 18, 66, 129),
            ],
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
          ),
        ),
        child: TextFormField(
          validator: (value) {
            if (value!.isEmpty) {
              return 'Field is required';
            }
            return null;
          },
          onChanged: widget.onChanged,
          textInputAction: TextInputAction.next,
          keyboardType: TextInputType.visiblePassword,
          obscureText: obscureText,
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
            prefixIcon: Container(
              padding: const EdgeInsets.symmetric(horizontal: 8),
              child: Row(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Icon(Icons.lock, color: Colors.grey.shade600),
                  Container(
                    margin: const EdgeInsets.symmetric(horizontal: 8),
                    width: 1,
                    height: 24,
                    color: Colors.grey.shade400,
                  ),
                ],
              ),
            ),
            suffixIcon: IconButton(
              icon: Icon(icon, color: Colors.grey.shade600),
              onPressed: () {
                setState(() {
                  if (icon == Icons.visibility_off) {
                    icon = Icons.visibility;
                    obscureText = false;
                  } else {
                    icon = Icons.visibility_off;
                    obscureText = true;
                  }
                });
              },
            ),
            labelText: widget.labelText,
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
            fillColor: Colors.transparent, // عشان الـ gradient يبان
            floatingLabelBehavior: FloatingLabelBehavior.auto,
            enabledBorder: OutlineInputBorder(
              borderRadius: BorderRadius.circular(20),
              borderSide: BorderSide(
                color: Colors.blueGrey.shade200,
                width: 1.8,
              ),
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
      ),
    );
  }
}
// // ignore: file_names
// import 'package:flutter/material.dart';

// // ignore: must_be_immutable
// class CustomPasswordTextfield extends StatefulWidget {
//   CustomPasswordTextfield({this.onChanged, super.key});
//   Function(String)? onChanged;

//   @override
//   State<CustomPasswordTextfield> createState() =>
//       _CustomPasswordTextfieldState();
// }

// class _CustomPasswordTextfieldState extends State<CustomPasswordTextfield> {
//   IconData icon = Icons.visibility_off;

//   bool obscureText = true;

//   @override
//   Widget build(BuildContext context) {
//     return TextFormField(
//       validator: (value) {
//         if (value!.isEmpty) {
//           return 'Field is required';
//         }
//         return null;
//       },
//       onChanged: widget.onChanged,
//       textInputAction: TextInputAction.next,
//       keyboardType: TextInputType.visiblePassword,
//       obscureText: obscureText,
//       decoration: InputDecoration(
//         prefixIcon: Icon(Icons.lock, color: Colors.grey),
//         suffixIcon: IconButton(
//           icon: Icon(icon),
//           color: Colors.grey,
//           onPressed: () {
//             setState(() {
//               if (icon == Icons.visibility_off) {
//                 icon = Icons.visibility;
//                 obscureText = false;
//               } else {
//                 icon = Icons.visibility_off;
//                 obscureText = true;
//               }
//             });
//           },
//         ),
//         hintText: "Password",
//         hintStyle: TextStyle(
//           color: Colors.grey,
//           fontSize: 16,
//           fontFamily: "Pacifico",
//         ),
//         filled: true,
//         fillColor: Colors.white,
//         enabledBorder: OutlineInputBorder(
//           borderRadius: BorderRadius.circular(25),
//           borderSide: const BorderSide(color: Colors.blueGrey, width: 2),
//         ),
//         focusedBorder: OutlineInputBorder(
//           borderRadius: BorderRadius.circular(25),
//           borderSide: const BorderSide(color: Colors.teal, width: 4),
//         ),
//         errorBorder: OutlineInputBorder(
//           borderRadius: BorderRadius.circular(25),
//           borderSide: const BorderSide(color: Colors.red, width: 2),
//         ),
//         focusedErrorBorder: OutlineInputBorder(
//           borderRadius: BorderRadius.circular(25),
//           borderSide: const BorderSide(color: Colors.blueGrey, width: 4),
//         ),
//       ),
//     );
//   }
// }
