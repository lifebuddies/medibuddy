import 'package:flutter/material.dart';

class UserAvatarDetail extends StatelessWidget {
  const UserAvatarDetail({super.key});

  @override
  Widget build(BuildContext context) {
    return const   CircleAvatar(
                  radius: 100,
                  backgroundColor: Colors.white,
                  child: CircleAvatar(
                    backgroundImage:  AssetImage(
                      'lib/assets/images/defultUser.jpeg',
                    ),
                    radius: 95,
                  ),
                );
  }
}