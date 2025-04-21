import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_zoom_drawer/flutter_zoom_drawer.dart';
import 'package:medibuddy/cubit/theme_cubit.dart';

class TesThemeScreen extends StatefulWidget {
  const TesThemeScreen({super.key});

  @override
  State<TesThemeScreen> createState() => _TesThemeScreenState();
}

class _TesThemeScreenState extends State<TesThemeScreen> {
  @override
  Widget build(BuildContext context) {
    return BlocBuilder<ThemeCubit, ThemeState>(
      builder: (context, state) {
        return Scaffold(
          appBar: AppBar(),
          body: Positioned(
            top: 15,
            left: -10,
            child: Builder(
              builder:
                  (context) => MaterialButton(
                    onPressed: () {
                      ZoomDrawer.of(context)?.toggle(); // تصحيح استدعاء toggle
                    },
                    child: SizedBox(
                      height: 100,
                      child: Icon(Icons.menu, size: 30, color: Colors.black),
                    ),
                  ),
            ),
          ),
          // body: Stack(
          //   children: [
          //     Container(
          //       width: double.infinity,
          //       decoration: BoxDecoration(
          //         image: DecorationImage(
          //           image: AssetImage(
          //             "lib/assets/images/${themeColor}/RegisterationBackGround.png",
          //             // "lib/assets/images/PinkTheme/RegisterationBackGround.png"
          //           ),
          //         ),
          //       ),
          //     ),
          //     Positioned(
          //       left: 50,
          //       bottom: 50,
          //       child: Row(
          //         mainAxisAlignment: MainAxisAlignment.spaceBetween,
          //         children: [
          //           MaterialButton(
          //             onPressed: () {
          //               themeColor = context.read<ThemeCubit>().changTheme(
          //                 "PinkTheme",
          //               );
          //               setState(() {});
          //             },
          //             child: Container(
          //               height: 20,
          //               width: 50,
          //               decoration: BoxDecoration(color: Colors.red),
          //             ),
          //           ),
          //           MaterialButton(
          //             onPressed: () {
          //               themeColor = context.read<ThemeCubit>().changTheme(
          //                 "BlueTheme",
          //               );
          //               setState(() {});
          //             },
          //             child: Container(
          //               height: 20,
          //               width: 50,
          //               decoration: BoxDecoration(color: Colors.blue),
          //             ),
          //           ),
          //           MaterialButton(
          //             onPressed: () {
          //               Navigator.push(
          //                 context,
          //                 CupertinoPageRoute(
          //                   builder: (context) => const TesThemeScreen(),
          //                 ),
          //               );
          //             },
          //             child: Container(
          //               height: 20,
          //               width: 50,
          //               decoration: BoxDecoration(color: Colors.black),
          //             ),
          //           ),
          //         ],
          //       ),
          //     ),
          //   ],
          // ),
        );
      },
    );
  }
}

changeTheme(String theme) {
  String newTheme = theme;
  return newTheme;
}
