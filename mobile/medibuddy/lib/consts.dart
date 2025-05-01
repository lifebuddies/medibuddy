import 'package:flutter/material.dart';
import 'package:medibuddy/models/setting_theme_model.dart';
import 'package:shared_preferences/shared_preferences.dart';

String themeColor = "BlueTheme";
Color currentThemeColor = Color.fromARGB(225, 26, 95, 183);
Color textWhiteColor = Colors.white;
Color textBlackColor = Colors.black87;
Color currentTextColor = currentThemeColor;
Color currentDrawerColor = Colors.blue.shade900;
Color currentDividerColor = currentDrawerColor;
String userImage = "lib/assets/images/defultUser.jpeg";

void saveTheme(String theme) async {
  final prefs = await SharedPreferences.getInstance();
  await prefs.setString('appTheme', theme);
}

Future<String?> loadTheme() async {
  final prefs = await SharedPreferences.getInstance();
  return prefs.getString('appTheme');
}

SettingThemeModel blueTheme = SettingThemeModel(
  themeName: "BlueTheme",
  currentThemeColor: Color.fromARGB(225, 26, 95, 183),
  textWhiteColor: Colors.white,
  textBlackColor: Colors.black87,
  currentDrawerColor: Colors.blue.shade900,
  currentTextColor: Color.fromARGB(225, 26, 95, 183),
  currentDividerColor: Color.fromARGB(225, 26, 95, 183),
);

SettingThemeModel pinkTheme = SettingThemeModel(
  themeName: "PinkTheme",
  currentThemeColor: Color.fromARGB(225, 229, 53, 97),
  textWhiteColor: Colors.white,
  textBlackColor: Colors.black87,
  currentTextColor: Color.fromARGB(225, 229, 53, 97),
  currentDrawerColor: Colors.pink.shade900,
  currentDividerColor: Color.fromARGB(225, 229, 53, 97),
);
SettingThemeModel greenTheme = SettingThemeModel(
  themeName: "GreenTheme",
  currentThemeColor: Color.fromARGB(225, 0, 96, 81),
  textWhiteColor: Colors.white,
  textBlackColor: Colors.black87,
  currentTextColor: Color.fromARGB(225, 0, 96, 81),
  currentDrawerColor: Color.fromARGB(225, 0, 96, 81),
  currentDividerColor: Color.fromARGB(225, 0, 96, 81),
);
SettingThemeModel darkTheme = SettingThemeModel(
  themeName: "DarkTheme",
  textBlackColor: Colors.white,
  textWhiteColor: Colors.white,
  currentDividerColor: Colors.white,
  currentDrawerColor: Color.fromARGB(225, 0, 30, 69),
  currentThemeColor: Color.fromARGB(255, 0, 30, 69),
  currentTextColor: Color.fromARGB(255, 0, 30, 69),
);
final Map<String, SettingThemeModel> allThemes = {
  "BlueTheme": blueTheme,
  "PinkTheme": pinkTheme,
  "GreenTheme": greenTheme,
  "DarkTheme": darkTheme,
};

void applyTheme(SettingThemeModel model) {
  themeColor = model.themeName;
  currentThemeColor = model.currentThemeColor;
  textWhiteColor = model.textWhiteColor;
  textBlackColor = model.textBlackColor;
  currentTextColor = model.currentTextColor;
  currentDrawerColor = model.currentDrawerColor;
  currentDividerColor = model.currentDividerColor;
}
