import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/screens/app_home_screen.dart';
import 'package:medibuddy/widgets/choosing_image_avatar_button.dart';
import 'package:medibuddy/widgets/custom_checkAcount_row.dart';
import 'package:medibuddy/widgets/custom_elevated_page_button.dart';
import 'package:medibuddy/widgets/custom_password_textField.dart';
import 'package:medibuddy/widgets/custom_textField.dart';

class Register extends StatelessWidget {
  const Register({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // الخلفية
          Positioned.fill(
            child: Image.asset(
              'lib/assets/images/$themeColor/RegisterationBackGround.png',
              fit: BoxFit.cover,
            ),
          ),
          Center(
            child: SingleChildScrollView(
              child: Column(
                children: [
                  const SizedBox(height: 20),

                  const SizedBox(height: 20),
                  TextButton(
                    onPressed: () {
                      final currentLocale = context.locale;
                      if (currentLocale.languageCode == 'en') {
                        context.setLocale(const Locale('ar'));
                      } else {
                        context.setLocale(const Locale('en'));
                      }
                    },
                    child: Text(
                      context.locale.languageCode == 'en'
                          ? 'العربية'
                          : 'English',
                      style: const TextStyle(color: Colors.white, fontSize: 16),
                    ),
                  ),

                   ChoosingImageAvatarButton(radius: 60),
                  const SizedBox(height: 30),
                  CustomTextfield(
                    hintText: 'name'.tr(),
                    icon: Icons.person,
                    keyboardType: TextInputType.name,
                  ),
                  const SizedBox(height: 15),
                  CustomTextfield(
                    hintText: 'phone_number'.tr(),
                    icon: Icons.phone,
                    keyboardType: TextInputType.phone,
                  ),
                  const SizedBox(height: 15),
                  CustomTextfield(
                    hintText: 'email'.tr(),
                    icon: Icons.email,
                    keyboardType: TextInputType.emailAddress,
                  ),
                  const SizedBox(height: 15),
                  CustomPasswordTextfield(labelText: 'password'.tr()),
                  const SizedBox(height: 15),
                  CustomPasswordTextfield(labelText: 'confirm_password'.tr()),
                  const SizedBox(height: 40),
                  CustomElevatedPageButton(
                    title: 'register'.tr(),
                    onPressed: () {
                      // sign up action

                      Navigator.pushReplacement(
                        context,
                        CupertinoPageRoute(
                          builder: (context) => AppHomeScreen(),
                        ),
                      );
                    },
                  ),
                  const SizedBox(height: 20),
                  const Divider(
                    color: Colors.white,
                    thickness: 1,
                    indent: 20,
                    endIndent: 20,
                  ),
                  const SizedBox(height: 5),
                  CustomCheckacountRow(
                    question: "allready_have_account".tr(),
                    option: "login".tr(),
                    onpressed: () {
                      // هنا ال دايركت لينك
                    },
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
