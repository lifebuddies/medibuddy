import 'package:flutter/material.dart';
import 'package:easy_localization/easy_localization.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/screens/app_home_screen.dart';

class SettingScreen extends StatelessWidget {
  const SettingScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async {
        // الرجوع للصفحة الرئيسية
        Navigator.pushAndRemoveUntil(
          context,
          PageRouteBuilder(
            transitionDuration: Duration(milliseconds: 600),
            pageBuilder: (_, __, ___) => AppHomeScreen(),
            transitionsBuilder: (
              context,
              animation,
              secondaryAnimation,
              child,
            ) {
              var begin = Offset(
                -1.0,
                0.0,
              ); // بداية الحركة من الشمال زي ال cupertino
              var end = Offset.zero;
              var curve = Curves.easeInOut;

              var tween = Tween(
                begin: begin,
                end: end,
              ).chain(CurveTween(curve: curve));

              return SlideTransition(
                position: animation.drive(tween),
                child: child,
              );
              /////////////////
            },
          ),
          (Route<dynamic> route) => false, // عشان مرجعش ل الصففحه ال فاتت
        );
        return false; //عشان مرجعش لورا بالغلط و اقفل التطبيق
        // عشان مرجعش لورا بالغلط
      },
      child: Stack(
        children: [
          // Background Image
          Positioned.fill(
            child: Image.asset(
              'lib/assets/images/$themeColor/AppBackGround.png',
              fit: BoxFit.cover,
            ),
          ),
          // Title
          Positioned(
            top: 80,
            left: context.locale.languageCode == 'en' ? 80 : 0,
            right: context.locale.languageCode == 'en' ? 0 : 80,
            child: Text(
              "Settings",
              style: TextStyle(
                fontSize: 30,
                fontWeight: FontWeight.bold,
                color: Colors.white,
                shadows: [
                  Shadow(
                    blurRadius: 10,
                    color: Colors.black.withOpacity(0.7),
                    offset: Offset(2, 2),
                  ),
                ],
              ),
            ),
          ),
          // Content
          Positioned.fill(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                // Language Selection Section
                Text(
                  "Select Language",
                  style: TextStyle(
                    fontSize: 24,
                    fontWeight: FontWeight.bold,
                    color: Colors.white,
                  ),
                ),
                SizedBox(height: 20),
                // Arabic Language Option
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    padding: EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                    backgroundColor: Color.fromARGB(
                      224,
                      183,
                      94,
                      26,
                    ), // Logout button color
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                    elevation: 5,
                  ),
                  onPressed: () {
                    context.setLocale(Locale('ar'));
                  },
                  child: Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      SizedBox(width: 10),
                      Text('Arabic', style: TextStyle(color: Colors.white)),
                    ],
                  ),
                ),
                SizedBox(height: 20),

                // English Language Option
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    padding: EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                    backgroundColor: Color.fromARGB(
                      224,
                      39,
                      183,
                      26,
                    ), // Logout button color
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                    elevation: 5,
                  ),
                  onPressed: () {
                    context.setLocale(Locale('en'));
                  },
                  child: Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      SizedBox(width: 10),
                      Text('English', style: TextStyle(color: Colors.white)),
                    ],
                  ),
                ),
                SizedBox(height: 40),

                // Logout Button
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    padding: EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                    backgroundColor: Color.fromARGB(
                      225,
                      26,
                      95,
                      183,
                    ), // Logout button color
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                    elevation: 5,
                  ),
                  onPressed: () {
                    // Handle Logout Logic
                  },
                  child: Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Icon(Icons.exit_to_app, color: Colors.white),
                      SizedBox(width: 10),
                      Text('Logout', style: TextStyle(color: Colors.white)),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
