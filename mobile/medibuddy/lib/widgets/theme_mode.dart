import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/cubit/theme_cubit.dart';

class SelectThemeMode extends StatelessWidget {
  const SelectThemeMode({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          "mode_:".tr(),
          style: TextStyle(
            color: textBlackColor,
            fontSize: 22,
            fontWeight: FontWeight.bold,
          ),
        ),
        const SizedBox(height: 10),
        SizedBox(
          height: 50,
          width: 100,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // IconButton(
              //   onPressed: () {
              //     themeColor = context.read<ThemeCubit>().changTheme(
              //       "BlueTheme",
              //     );
              //     currentThemeColor = Color.fromARGB(225, 26, 95, 183);
              //     textWhiteColor = Colors.white;
              //     textBlackColor = Colors.black87;
              //     currentTextColor = currentThemeColor;
              //     currentDrawerColor = Colors.blue.shade900;
              //     currentDividerColor = currentThemeColor;
              //     //save the theme color to shared preferences
              //     saveTheme(themeColor);
              //   },
              //   icon: Icon(Icons.sunny, size: 30, color: textBlackColor),
              // ),
              IconButton(
                onPressed: () {
                  var selectedTheme = allThemes["BlueTheme"]!;
                  context.read<ThemeCubit>().changTheme(
                    selectedTheme.themeName,
                  );
                  applyTheme(selectedTheme);
                  saveTheme(selectedTheme.themeName);
                },
                icon: Icon(Icons.sunny, size: 30, color: textBlackColor),
              ),
              // IconButton(
              //   onPressed: () async {
              //     themeColor = context.read<ThemeCubit>().changTheme(
              //       "DarkTheme",
              //     );
              //     textBlackColor = textWhiteColor;
              //     currentDrawerColor = Color.fromARGB(225, 26, 95, 183);
              //     currentThemeColor = Color.fromARGB(255, 0, 30, 69);
              //     textBlackColor = Colors.white;
              //     textWhiteColor = Colors.white;
              //     currentDividerColor = textWhiteColor;
              //     currentTextColor = currentThemeColor;
              //     //save the theme color to shared preferences
              //     saveTheme(themeColor);
              //   },
              //   icon: Icon(Icons.dark_mode, size: 30, color: textBlackColor),
              // ),
              IconButton(
                onPressed: () {
                  var selectedTheme = allThemes["DarkTheme"]!;
                  context.read<ThemeCubit>().changTheme(
                    selectedTheme.themeName,
                  );
                  applyTheme(selectedTheme);
                  saveTheme(selectedTheme.themeName);
                },
                icon: Icon(Icons.dark_mode, size: 30, color: textBlackColor),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
