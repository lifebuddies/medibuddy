import 'dart:io';

import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';

class ChoosingImageAvatarButton extends StatefulWidget {
  const ChoosingImageAvatarButton({super.key});

  @override
  State<ChoosingImageAvatarButton> createState() => _MyWidgetState();
}

class _MyWidgetState extends State<ChoosingImageAvatarButton> {
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
    return MaterialButton(
      onPressed: () {
        showDialog(
          context: context,
          builder:
              (context) => AlertDialog(
                title: const Text('Select Image'),
                content: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    ListTile(
                      leading: const Icon(Icons.camera_alt_outlined),
                      title: const Text('Camera'),
                      onTap: () {
                        _pickImage(ImageSource.camera);
                        Navigator.pop(context);
                      },
                    ),
                    ListTile(
                      leading: const Icon(Icons.photo),
                      title: const Text('Gallery'),
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
      child: CircleAvatar(
        radius: 60,
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
