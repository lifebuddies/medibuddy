import 'dart:io';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/widgets/choosing_image_avatar_model_button.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ChoosingImageAvatarButton extends StatefulWidget {
  const ChoosingImageAvatarButton({super.key, required this.radius});
  final double radius;

  @override
  State<ChoosingImageAvatarButton> createState() => _MyWidgetState();
}

class _MyWidgetState extends State<ChoosingImageAvatarButton> {
  File? _pickedImage;

  final ImagePicker _picker = ImagePicker();
  @override
  void initState() {
    super.initState();
    _loadSavedImage();
  }

  Future<void> _pickImage(ImageSource source) async {
    final XFile? pickedFile = await _picker.pickImage(source: source);
    if (pickedFile != null) {
      setState(() {
        _pickedImage = File(pickedFile.path);
        userImage = pickedFile.path;
      });
      await _saveImagePath(pickedFile.path);
    }
  }

  Future<void> _saveImagePath(String path) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString('user_image_path', path);
  }

  Future<void> _loadSavedImage() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    final String? savedPath = prefs.getString('user_image_path');
    if (savedPath != null && File(savedPath).existsSync()) {
      setState(() {
        userImage = savedPath;
        _pickedImage = File(savedPath);
      });
    } else {
      setState(() {
        _pickedImage = null;
        userImage =
            "lib/assets/images/defultUser.jpeg"; // المسار الصحيح للـ Asset
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return IconButton(
      iconSize: widget.radius * 2,
      onPressed: () {
        showModalBottomSheet(
          isScrollControlled: true,
          isDismissible: true,
          backgroundColor: Colors.white,
          context: context,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.vertical(
              top: Radius.circular(50),
              bottom: Radius.circular(50),
            ),
          ),
          builder: (BuildContext context) {
            return Container(
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.vertical(
                  top: Radius.circular(50),
                  bottom: Radius.circular(50),
                ),
              ),
              margin: const EdgeInsets.all(5),
              height: 150,
              child: Container(
                height: 150,
                decoration: BoxDecoration(
                  color: currentThemeColor,
                  borderRadius: BorderRadius.vertical(
                    top: Radius.circular(50),
                    bottom: Radius.circular(50),
                  ),
                ),
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    ChoosingImageAvatarModelButton(
                      icon: Icons.camera_alt,
                      text: 'camera',
                      onImagePicked: () {
                        _pickImage(ImageSource.camera);
                        Navigator.pop(context);
                      },
                    ),
                    Divider(
                      color: Colors.white,
                      thickness: .5,
                      indent: 50,
                      endIndent: 50,
                    ),
                    ChoosingImageAvatarModelButton(
                      icon: Icons.photo,
                      text: 'gallery',
                      onImagePicked: () {
                        _pickImage(ImageSource.gallery);
                        Navigator.pop(context);
                      },
                    ),
                  ],
                ),
              ),
            );
          },
        );
      },
      icon: CircleAvatar(
        radius: widget.radius,
        backgroundImage:
            _pickedImage != null
                ? FileImage(_pickedImage!)
                : AssetImage(userImage) as ImageProvider,
        backgroundColor: Colors.white,
      ),
    );
  }
}
