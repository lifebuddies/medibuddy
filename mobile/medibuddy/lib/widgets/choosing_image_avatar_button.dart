import 'dart:io';

import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';

class ChoosingImageAvatarButton extends StatefulWidget {
  const ChoosingImageAvatarButton({super.key, required this.radius});
  final double radius;

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
