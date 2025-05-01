import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/cubit/theme_cubit.dart';

class ChoosingAppTheme extends StatelessWidget {
  const ChoosingAppTheme({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          "select_theme_:".tr(),
          style: TextStyle(
            color: textBlackColor,
            fontSize: 22,
            fontWeight: FontWeight.bold,
          ),
        ),
        const SizedBox(height: 10),
        Padding(
          padding: const EdgeInsets.only(left: 20),
          child: SizedBox(
            height: 50,
            width: 150,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                IconButton(
                  onPressed: () {
                    themeColor = context.read<ThemeCubit>().changTheme(
                      "BlueTheme",
                    );
                    // currentThemeColor = Color.fromARGB(225, 26, 95, 183);
                    // textWhiteColor = Colors.white;
                    // textBlackColor = Colors.black87;
                    // currentTextColor = currentThemeColor;
                    // currentDrawerColor = Colors.blue.shade900;
                    // currentDividerColor = currentThemeColor;
                    // //save the theme color to shared preferences
                    // saveTheme(themeColor);
                    var selectedTheme = allThemes["BlueTheme"]!;
                    context.read<ThemeCubit>().changTheme(
                      selectedTheme.themeName,
                    );
                    applyTheme(selectedTheme);
                    saveTheme(selectedTheme.themeName);
                  },
                  icon: Icon(
                    Icons.circle,
                    color: Colors.blue.shade900,
                    size: 30,
                  ),
                ),
                IconButton(
                  onPressed: () {
                    themeColor = context.read<ThemeCubit>().changTheme(
                      "PinkTheme",
                    );
                    // currentThemeColor = Color.fromARGB(225, 229, 53, 97);
                    // textWhiteColor = Colors.white;
                    // textBlackColor = Colors.black87;
                    // currentTextColor = currentThemeColor;
                    // currentDrawerColor = Colors.pink.shade900;
                    // currentDividerColor = currentThemeColor;
                    // //save the theme color to shared preferences
                    // saveTheme(themeColor);
                    var selectedTheme = allThemes["PinkTheme"]!;
                    context.read<ThemeCubit>().changTheme(
                      selectedTheme.themeName,
                    );
                    applyTheme(selectedTheme);
                    saveTheme(selectedTheme.themeName);
                  },
                  icon: Icon(Icons.circle, color: Colors.pink, size: 30),
                ),
                IconButton(
                  onPressed: () {
                    themeColor = context.read<ThemeCubit>().changTheme(
                      "GreenTheme",
                    );
                    // currentThemeColor = Color.fromARGB(225, 0, 96, 81);
                    // textWhiteColor = Colors.white;
                    // textBlackColor = Colors.black87;
                    // currentTextColor = currentThemeColor;
                    // currentDrawerColor = currentThemeColor;
                    // currentDividerColor = currentThemeColor;
                    // //save the theme color to shared preferences
                    // saveTheme(themeColor);
                    var selectedTheme = allThemes["GreenTheme"]!;
                    context.read<ThemeCubit>().changTheme(
                      selectedTheme.themeName,
                    );
                    applyTheme(selectedTheme);
                    saveTheme(selectedTheme.themeName);
                  },
                  icon: Icon(
                    Icons.circle,
                    color: const Color.fromARGB(255, 74, 195, 94),
                    size: 30,
                  ),
                ),
              ],
            ),
          ),
        ),
      ],
    );
  }
}
