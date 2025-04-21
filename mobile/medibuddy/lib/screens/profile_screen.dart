import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/cubit/theme_cubit.dart';
import 'package:medibuddy/screens/app_home_screen.dart';
import 'package:medibuddy/widgets/drawer_button.dart';
import 'package:medibuddy/widgets/user_details.dart'; // تأكد من المسار الصحيح للصفحة الرئيسية

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({super.key});

  @override
  Widget build(BuildContext context) {
    // ignore: deprecated_member_use
    return BlocBuilder<ThemeCubit, ThemeState>(
      builder: (context, state) {
        if (state is ThemeChanged) {
          themeColor = state.theme;
        } else if (state is ThemeError) {
          themeColor = "BlueTheme";
        } else {
          themeColor = "BlueTheme";
        }
        // ignore: deprecated_member_use
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
          },
          child: Scaffold(
            body: Stack(
              children: [
                // الخلفية
                Container(
                  decoration: BoxDecoration(
                    image: DecorationImage(
                      image: AssetImage(
                        'lib/assets/images/$themeColor/UserBackGround.png',
                      ),
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
                CustomDrawerButton(),
                UserDetails(),
              ],
            ),
          ),
        );
      },
    );
  }
}
