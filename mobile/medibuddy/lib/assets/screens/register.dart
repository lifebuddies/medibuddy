import 'dart:io';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:medibuddy/widgets/custom_checkAcount_row.dart';
import 'package:medibuddy/widgets/custom_elevated_page_button.dart';
import 'package:medibuddy/widgets/custom_textField.dart';

class Register extends StatefulWidget {
  const Register({super.key});

  @override
  State<Register> createState() => _RegisterState();
}

class _RegisterState extends State<Register> {
  File? _pickedImage;
  final ImagePicker _picker = ImagePicker();

  Future<void> _pickImage(ImageSource source) async {
    final XFile? pickedFile = await _picker.pickImage(source: source);
    if (pickedFile != null) {
      setState(() {
        _pickedImage = File(pickedFile.path);
      });
    }
  }

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

                  // صورة البروفايل واختياراتها
                  Column(
                    children: [
                      CircleAvatar(
                        radius: 60,
                        backgroundImage:
                            _pickedImage != null
                                ? FileImage(_pickedImage!)
                                : const AssetImage(
                                      'lib/assets/images/defultUser.jpeg',
                                    )
                                    as ImageProvider,
                        backgroundColor: Colors.white,
                      ),
                      SizedBox(height: 20),
                      Row(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          IconButton(
                            icon: const Icon(Icons.photo, color: Colors.black),
                            onPressed: () => _pickImage(ImageSource.gallery),
                          ),
                          IconButton(
                            icon: const Icon(
                              Icons.camera_alt,
                              color: Colors.black,
                            ),
                            onPressed: () => _pickImage(ImageSource.camera),
                          ),
                        ],
                      ),
                    ],
                  ),

                  const SizedBox(height: 30),
                  CustomTextfield(
                    hintText: 'Name',
                    icon: Icons.person,
                    keyboardType: TextInputType.name,
                  ),
                  const SizedBox(height: 10),

                  CustomTextfield(
                    hintText: 'Phone Number',
                    icon: Icons.phone,
                    keyboardType: TextInputType.phone,
                  ),
                  const SizedBox(height: 10),

                  CustomTextfield(
                    hintText: 'Email',
                    icon: Icons.email,
                    keyboardType: TextInputType.emailAddress,
                  ),
                  const SizedBox(height: 10),
                  CustomTextfield(
                    hintText: 'Password',
                    icon: Icons.lock,
                    keyboardType: TextInputType.visiblePassword,
                  ),
                  const SizedBox(height: 10),
                  CustomTextfield(
                    hintText: 'Confirm Password',
                    icon: Icons.lock,
                    keyboardType: TextInputType.visiblePassword,
                  ),
                  const SizedBox(height: 40),
                  CustomElevatedPageButton(title: 'Sign Up', onPressed: () {}),
                  const SizedBox(height: 20),
                  Divider(
                    color: const Color.fromARGB(225, 26, 95, 183),
                    thickness: 1,
                    indent: 20,
                    endIndent: 20,
                  ),
                  const SizedBox(height: 5),
                  CustomCheckacountRow(
                    question: "Already have an account ?",
                    option: "Sign In",
                    onpressed: () {
                      Navigator.pop(context);
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
