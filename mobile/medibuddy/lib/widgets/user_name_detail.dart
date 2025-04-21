import 'package:flutter/material.dart';

class UserNameDetail extends StatelessWidget {
  const UserNameDetail({super.key});

  @override
  Widget build(BuildContext context) {
    return const Text(
      'Mohamed Ghoniem',
      style: TextStyle(
        color: Colors.black,
        fontSize: 22,
        fontWeight: FontWeight.bold,
      ),
    );
  }
}
