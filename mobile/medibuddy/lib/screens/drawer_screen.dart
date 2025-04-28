import 'dart:io';

import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/screens/main_app_screen.dart';
import 'package:medibuddy/screens/profile_screen.dart';
import 'package:medibuddy/screens/setting_screen.dart';
import 'package:medibuddy/screens/tes_theme_screen.dart';
import 'package:medibuddy/widgets/drawer_item.dart';
import 'package:shared_preferences/shared_preferences.dart';

class DrawerScreen extends StatefulWidget {
  final Function(Widget) onItemSelected;
  const DrawerScreen({super.key, required this.onItemSelected});

  @override
  State<DrawerScreen> createState() => _DrawerScreenState();
}

class _DrawerScreenState extends State<DrawerScreen> {
  void initState() {
    super.initState();
    _loadSavedImage();
  }

  Future<void> _loadSavedImage() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    final String? savedPath = prefs.getString('user_image_path');
    if (savedPath != null && File(savedPath).existsSync()) {
      setState(() {
        userImage = savedPath;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: currentDrawerColor,
      body: Container(
        padding: const EdgeInsets.only(top: 60, left: 20, right: 20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // صورة البروفايل
            CircleAvatar(
              backgroundImage:
                  // userImage == 'lib/assets/images/defultUser.jpeg'
                  //     ? AssetImage(userImage)
                  FileImage(File(userImage)) as ImageProvider,
              radius: 100,
            ),
            const SizedBox(height: 20),
            Text(
              'mohmmed_ayman'.tr(),
              style: TextStyle(
                color: Colors.white,
                fontSize: 22,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 40),
            const Divider(indent: 10, endIndent: 25),
            const SizedBox(height: 20),

            DrawerItem(
              icon: Icons.home,
              title: 'home'.tr(),
              onPressed: () => widget.onItemSelected(const MainAppScreen()),
            ),
            const SizedBox(height: 5),
            DrawerItem(
              icon: Icons.person,
              title: 'profile'.tr(),
              onPressed: () => widget.onItemSelected(const ProfileScreen()),
            ),
            const SizedBox(height: 5),
            DrawerItem(
              icon: Icons.settings,
              title: 'settings'.tr(),
              onPressed: () => widget.onItemSelected(const SettingScreen()),
            ),
            const SizedBox(height: 5),
            DrawerItem(
              icon: Icons.local_pharmacy_rounded,
              title: 'new_plan'.tr(),
              onPressed: () => widget.onItemSelected(const TesThemeScreen()),
            ),
          ],
        ),
      ),
    );
  }
}
