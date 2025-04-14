import 'package:flutter/material.dart';
import 'package:medibuddy/widgets/choosing_image_avatar_button.dart';
import 'package:medibuddy/widgets/custom_checkAcount_row.dart';
import 'package:medibuddy/widgets/custom_elevated_page_button.dart';
import 'package:medibuddy/widgets/custom_password_textField.dart';
import 'package:medibuddy/widgets/custom_textField.dart';

class Register extends StatefulWidget {
  const Register({super.key});

  @override
  State<Register> createState() => _RegisterState();
}

class _RegisterState extends State<Register> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // الخلفية
          Positioned.fill(
            child: Image.asset('lib/assets/images/3.png', fit: BoxFit.cover),
          ),
          Center(
            child: SingleChildScrollView(
              child: Column(
                children: [
                  const SizedBox(height: 100),
                  ChoosingImageAvatarButton(), // زر اختيار صورة البروفايل
                  // صورة البروفايل واختياراتها
                  const SizedBox(height: 30),
                  CustomTextfield(
                    hintText: 'Name',
                    icon: Icons.person,
                    keyboardType: TextInputType.name,
                  ),
                  const SizedBox(height: 15),

                  CustomTextfield(
                    hintText: 'Phone Number',
                    icon: Icons.phone,
                    keyboardType: TextInputType.phone,
                  ),
                  const SizedBox(height: 15),

                  CustomTextfield(
                    hintText: 'Email',
                    icon: Icons.email,
                    keyboardType: TextInputType.emailAddress,
                  ),
                  const SizedBox(height: 15),
                  CustomPasswordTextfield(labelText: 'Password'),
                  const SizedBox(height: 15),

                  CustomPasswordTextfield(labelText: 'Confirm Password'),
                  const SizedBox(height: 40),
                  CustomElevatedPageButton(title: 'Sign Up', onPressed: () {}),
                  const SizedBox(height: 20),
                  Divider(
                    color: Colors.white,
                    thickness: 1,
                    indent: 20,
                    endIndent: 20,
                  ),
                  const SizedBox(height: 5),
                  CustomCheckacountRow(
                    question: "Already have an account ?",
                    option: "Sign In",
                    onpressed: () {
                      //هنعمل هنا ري دايركت ع صفحه الويب ع طول
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
