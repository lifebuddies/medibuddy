import 'package:flutter/material.dart';

import 'package:medibuddy/consts.dart';
import 'package:medibuddy/widgets/choosing_app_theme.dart';
import 'package:medibuddy/widgets/theme_mode.dart';
import 'package:medibuddy/widgets/user_avatar_detail.dart';
import 'package:medibuddy/widgets/user_email_detail.dart';
import 'package:medibuddy/widgets/user_name_detail.dart';

class UserDetails extends StatelessWidget {
  const UserDetails({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 50),
      child: Column(
        children: [
          Positioned(
            top: 50,
            left: 80,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                UserAvatarDetail(),
                // ChoosingImageAvatarButton(radius: 120),
                SizedBox(height: 20),
                UserNameDetail(),
                const SizedBox(height: 10),
              ],
            ),
          ),
          Positioned(
            top: 330,
            left: 20,
            right: 20,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Divider(
                  color: currentDividerColor,
                  thickness: 1.5,
                  indent: 10,
                  endIndent: 25,
                ),
                const SizedBox(height: 20),
                UserEmailDetail(),
                const SizedBox(height: 20),

                Divider(
                  color: currentDividerColor,
                  thickness: 1.5,
                  indent: 10,
                  endIndent: 25,
                ),
                const SizedBox(height: 20),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,

                  children: [
                    ChoosingAppTheme(),
                    const SizedBox(width: 25),
                    SelectThemeMode(),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

//   }
