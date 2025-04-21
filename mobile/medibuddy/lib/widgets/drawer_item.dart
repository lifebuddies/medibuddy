import 'package:flutter/material.dart';

// ignore: must_be_immutable
class DrawerItem extends StatelessWidget {
  final IconData icon;
  final String title;
  final Function()? onPressed;
  const DrawerItem({
    required this.icon,
    required this.title,
    this.onPressed,
    super.key,
  });
  @override
  Widget build(BuildContext context) {
    return MaterialButton(
      onPressed: onPressed,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          Icon(icon, color: Colors.white, size: 26),
          const SizedBox(width: 15),
          Text(
            title,
            style: const TextStyle(
              color: Colors.white,
              fontSize: 18,
              fontWeight: FontWeight.w500,
            ),
          ),
        ],
      ),
    );
  }
}
