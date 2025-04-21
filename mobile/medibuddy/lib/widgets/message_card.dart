import 'package:flutter/material.dart';

class MessageCard extends StatelessWidget {
  final String text;
  final String imagePath;

  const MessageCard({super.key, required this.text, required this.imagePath});

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        // الـ Container الأزرق تحت
        Container(
          margin: const EdgeInsets.only(top: 8),
          height: 130,
          width: double.infinity,
          decoration: BoxDecoration(
            color: Color(0xFF1A5FB7),
            borderRadius: BorderRadius.circular(20),
          ),
        ),

        // Container البيضا فوق
        Container(
          margin: const EdgeInsets.only(bottom: 8),
          padding: const EdgeInsets.all(16),
          height: 130,
          width: double.infinity,
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(20),
          ),
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              // النص
              Expanded(
                child: Text(
                  text,
                  textAlign: TextAlign.center,
                  style: const TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.bold,
                    color: Color(0xFF1A5FB7),
                  ),
                ),
              ),
              const SizedBox(width: 10),
              // الصورة
              Image.asset(imagePath, height: 150, width: 100),
            ],
          ),
        ),
      ],
    );
  }
}
