import 'dart:io';

import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:medibuddy/consts.dart';
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
  void initState() {
    super.initState();
    _loadSavedImage();
  }

  Future<void> _pickImage(ImageSource source) async {
    final XFile? pickedFile = await _picker.pickImage(source: source);
    if (pickedFile != null) {
      setState(() {
        _pickedImage = File(pickedFile.path); // نحدث الفايل المعروض
        userImage = pickedFile.path; // نخزن الباث في المتغير
      });
      await _saveImagePath(pickedFile.path); // وكمان نحفظه في SharedPreferences
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
    }
  }

  @override
  Widget build(BuildContext context) {
    return IconButton(
      iconSize: widget.radius * 2,

      onPressed: () {
        showDialog(
          context: context,
          builder:
              (context) => AlertDialog(
                title: const Text('select_image').tr(),
                content: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    ListTile(
                      leading: const Icon(Icons.camera_alt_outlined),
                      title: const Text('camera').tr(),
                      onTap: () {
                        _pickImage(ImageSource.camera);
                        Navigator.pop(context);
                      },
                    ),
                    ListTile(
                      leading: const Icon(Icons.photo),
                      title: const Text('gallery').tr(),
                      onTap: () {
                        _pickImage(ImageSource.gallery);
                        Navigator.pop(context);
                      },
                    ),
                  ],
                ),
              ),
        );
      },
      icon: CircleAvatar(
        radius: widget.radius,
        backgroundImage:
            _pickedImage != null
                ? FileImage(_pickedImage!)
                : const AssetImage('lib/assets/images/defultUser.jpeg')
                    as ImageProvider,
        backgroundColor: Colors.white,
      ),
    );
  }
}
