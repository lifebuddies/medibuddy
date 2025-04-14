import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:medibuddy/screens/register.dart';
import 'package:medibuddy/widgets/custom_checkAcount_row.dart';
import 'package:medibuddy/widgets/custom_elevated_page_button.dart';
import 'package:medibuddy/widgets/custom_textField.dart';

class SignIn extends StatelessWidget {
  const SignIn({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // الخلفية
          Positioned.fill(
            child: Image.asset(
              'lib/assets/images/signing.jpg',
              fit: BoxFit.cover,
            ),
          ),
          Center(
            child: SingleChildScrollView(
              child: Column(
                children: [
                  const SizedBox(height: 400),
                  SingleChildScrollView(
                    child: Column(
                      children: [
                        CustomTextfield(
                          hintText: 'Email',
                          icon: Icons.email,
                          keyboardType: TextInputType.emailAddress,
                        ),
                        const SizedBox(height: 20),
                        CustomTextfield(
                          hintText: 'Password',
                          icon: Icons.lock,
                          keyboardType: TextInputType.visiblePassword,
                        ),
                        const SizedBox(height: 40),
                        CustomElevatedPageButton(
                          title: 'Sign In',
                          onPressed: () {},
                        ),
                        const SizedBox(height: 20),
                        Divider(
                          color: Color.fromARGB(225, 26, 95, 183),
                          thickness: 1,
                          indent: 20,
                          endIndent: 20,
                        ),
                        const SizedBox(height: 20),
                        CustomCheckacountRow(
                          question: "Don't have an account ?",
                          option: "Sign Up",
                          onpressed: () {
                            Navigator.push(context, 
                            CupertinoPageRoute(builder: (context) => Register()));
                          },
                        ),
                      ],
                    ),
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
