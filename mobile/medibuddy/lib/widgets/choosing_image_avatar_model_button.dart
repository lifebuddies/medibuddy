import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

class ChoosingImageAvatarModelButton extends StatelessWidget {
  const ChoosingImageAvatarModelButton({
    super.key,
    required this.icon,
    required this.text,
    required this.onImagePicked,
  });
  final IconData icon;
  final String text;
  final Function() onImagePicked;

  @override
  Widget build(BuildContext context) {
    return Center(
      child: GestureDetector(
        onTap: onImagePicked,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(icon, color: Colors.white, size: 40),
            const SizedBox(width: 8),
            Text(
              text,
              style: TextStyle(color: Colors.white, fontSize: 20),
            ).tr(),
          ],
        ),
      ),
    );
  }
}
