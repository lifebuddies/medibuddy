import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_zoom_drawer/flutter_zoom_drawer.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/cubit/theme_cubit.dart';
import 'package:medibuddy/screens/drawer_screen.dart';
import 'package:medibuddy/screens/main_app_screen.dart';
import 'package:flutter/services.dart';

class AppHomeScreen extends StatefulWidget {
  const AppHomeScreen({super.key});

  @override
  State<AppHomeScreen> createState() => _AppHomeScreenState();
}

class _AppHomeScreenState extends State<AppHomeScreen> {
  final ZoomDrawerController _drawerController = ZoomDrawerController();

  Widget _currentScreen = const MainAppScreen();
  DateTime? _lastBackPressed; // لمتابعة آخر مرة ضغط فيها المستخدم

  void _setScreen(Widget screen) {
    setState(() {
      _currentScreen = screen;
      _drawerController.toggle!();
    });
  }

  //   @override
  //   Widget build(BuildContext context) {
  //     // ignore: deprecated_member_use
  //     return BlocBuilder<ThemeCubit, ThemeState>(
  //       builder: (context, state) {
  //         if (state is ThemeChanged) {
  //           themeColor = state.theme;
  //         } else if (state is ThemeError) {
  //           themeColor = "BlueTheme";
  //         } else {
  //           themeColor = "BlueTheme";
  //         }
  //         // ignore: deprecated_member_use
  //         return WillPopScope(
  //           onWillPop: () async {
  //             if (_drawerController.isOpen!()) {
  //               _drawerController.close!();
  //               return false;
  //             } else {
  //               DateTime now = DateTime.now();
  //               if (_lastBackPressed == null ||
  //                   now.difference(_lastBackPressed!) >
  //                       const Duration(seconds: 2)) {
  //                 _lastBackPressed = now;
  //                 ScaffoldMessenger.of(context).showSnackBar(
  //                   SnackBar(
  //                     backgroundColor: currentDrawerColor,
  //                     behavior: SnackBarBehavior.fixed,
  //                     content:  Center(
  //                       child: Text(
  //                         'Back_again_to_exit'.tr(),
  //                         style: TextStyle(fontSize: 18, color: Colors.white),
  //                       ),
  //                     ),
  //                     duration: const Duration(seconds: 2),
  //                   ),
  //                 );
  //                 return false;
  //               }
  //               SystemNavigator.pop(); // قفل التطبيق
  //               return true;
  //             }
  //           },
  //           child: ZoomDrawer(
  //             controller: _drawerController,
  //             menuBackgroundColor: currentDrawerColor,
  //             style: DrawerStyle.defaultStyle,
  //             borderRadius: 24.0,
  //             angle: -10.0,
  //             slideWidth: MediaQuery.of(context).size.width * .9,
  //             showShadow: true,
  //             drawerShadowsBackgroundColor: Colors.white,
  //             menuScreen: DrawerScreen(onItemSelected: _setScreen),
  //             mainScreen: _currentScreen,
  //           ),
  //         );
  //       },
  //     );
  //   }
  // }
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
            if (_drawerController.isOpen!()) {
              _drawerController.close!();
              return false;
            } else if (_currentScreen is MainAppScreen) {
              DateTime now = DateTime.now();
              if (_lastBackPressed == null ||
                  now.difference(_lastBackPressed!) >
                      const Duration(seconds: 2)) {
                _lastBackPressed = now;
                ScaffoldMessenger.of(context).showSnackBar(
                  SnackBar(
                    backgroundColor: currentDrawerColor,
                    behavior: SnackBarBehavior.fixed,
                    content: Center(
                      child: Text(
                        'Back_again_to_exit'.tr(),
                        style: TextStyle(fontSize: 18, color: Colors.white),
                      ),
                    ),
                    duration: const Duration(seconds: 2),
                  ),
                );
                return false;
              }
              SystemNavigator.pop(); // قفل التطبيق
              return true;
            }
            return true; // عدم إظهار السناك بار على باقي الصفحات
          },
          child: ZoomDrawer(
            controller: _drawerController,
            menuBackgroundColor: currentDrawerColor,
            style: DrawerStyle.defaultStyle,
            borderRadius: 24.0,
            angle: -10.0,
            slideWidth: MediaQuery.of(context).size.width * .9,
            showShadow: true,
            drawerShadowsBackgroundColor: Colors.white,
            menuScreen: DrawerScreen(onItemSelected: _setScreen),
            mainScreen: _currentScreen,
          ),
        );
      },
    );
  }
}
