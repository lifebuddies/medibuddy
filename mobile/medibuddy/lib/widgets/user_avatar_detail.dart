import 'package:flutter/material.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/widgets/choosing_image_avatar_button.dart';

class UserAvatarDetail extends StatelessWidget {
  const UserAvatarDetail({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 200,
      height: 200,
      // padding: const EdgeInsets.all(.00000009), 
      decoration: BoxDecoration(
        shape: BoxShape.circle,
        color: textBlackColor, // ← الإطار الأبيض
      ),
      child: const ChoosingImageAvatarButton(
        radius: 96,
      ), // radius أقل شوية علشان يبان الإطار
    );
  }
}
