import 'dart:ui';

import 'package:flutter/material.dart';

class SettingThemeModel {
  String themeName;
  Color currentThemeColor;
  Color textWhiteColor;
  Color textBlackColor;
  Color currentTextColor;
  Color currentDrawerColor;
  Color currentDividerColor;

  SettingThemeModel({
    required this.themeName,
    required this.currentThemeColor,
    required this.textWhiteColor,
    required this.textBlackColor,
    required this.currentTextColor,
    required this.currentDrawerColor,
    required this.currentDividerColor,
  });
}
