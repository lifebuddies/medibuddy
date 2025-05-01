import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/cubit/theme_cubit.dart';
import 'package:medibuddy/screens/splash_screen.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await EasyLocalization.ensureInitialized();

  final appTheme = await loadTheme();

  // هنا نجيب الثيم ونطبقه قبل تشغيل التطبيق
  final themeModel = allThemes[appTheme ?? "BlueTheme"];
  if (themeModel != null) {
    applyTheme(themeModel);
  }

  runApp(
    EasyLocalization(
      supportedLocales: [Locale('en'), Locale("ar")],
      path: 'lib/assets/trans',
      fallbackLocale: Locale('en', 'US'),
      saveLocale: true,
      child: MyApp(appTheme: appTheme ?? "BlueTheme"),
    ),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key, required this.appTheme});
  final String appTheme;
  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => ThemeCubit()..changTheme(appTheme),
      child: MaterialApp(
        localizationsDelegates: context.localizationDelegates,
        supportedLocales: context.supportedLocales,
        locale: context.locale,
        debugShowCheckedModeBanner: false,
        home: SplashScreen(),
      ),
    );
  }
}
