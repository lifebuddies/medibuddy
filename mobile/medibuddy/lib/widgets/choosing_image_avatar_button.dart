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
        showGeneralDialog(
          context: context,
          barrierDismissible: true,
          barrierLabel:
              MaterialLocalizations.of(context).modalBarrierDismissLabel,
          barrierColor: Colors.black54, // لون الخلفية الشفاف
          transitionDuration: const Duration(milliseconds: 300),
          pageBuilder: (context, animation, secondaryAnimation) {
            return Center(
              child: ScaleTransition(
                scale: CurvedAnimation(
                  parent: animation,
                  curve: Curves.easeOutBack,
                ),
                child: AlertDialog(
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.vertical(
                      top: Radius.circular(50),
                      bottom: Radius.circular(50),
                    ),
                  ),
                  backgroundColor: currentThemeColor,
                  title: Center(
                    child: Text(
                      'select_image'.tr(),
                      style: const TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.bold,
                        fontSize: 25,
                      ),
                    ),
                  ),
                  content: Column(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      ListTile(
                        leading: const Icon(
                          Icons.camera_alt_outlined,
                          color: Colors.white,
                          size: 25,
                        ),
                        title: Text(
                          'camera'.tr(),
                          style: const TextStyle(
                            color: Colors.white,
                            fontSize: 20,
                          ),
                        ),
                        onTap: () {
                          _pickImage(ImageSource.camera);
                          Navigator.pop(context);
                        },
                      ),
                      Divider(color: Colors.white, indent: 20, endIndent: 20),
                      ListTile(
                        leading: const Icon(
                          Icons.photo,
                          color: Colors.white,
                          size: 25,
                        ),
                        title: Text(
                          'gallery'.tr(),
                          style: const TextStyle(
                            color: Colors.white,
                            fontSize: 20,
                          ),
                        ),
                        onTap: () {
                          _pickImage(ImageSource.gallery);
                          Navigator.pop(context);
                        },
                      ),
                    ],
                  ),
                ),
              ),
            );
          },
          transitionBuilder: (context, animation, secondaryAnimation, child) {
            return FadeTransition(opacity: animation, child: child);
          },
        );

        // showDialog(
        //   context: context,
        //   builder:
        //       (context) => AlertDialog(
        //         title: const Text('select_image').tr(),
        //         content: Column(
        //           mainAxisSize: MainAxisSize.min,
        //           children: [
        //             ListTile(
        //               leading: const Icon(Icons.camera_alt_outlined),
        //               title: const Text('camera').tr(),
        //               onTap: () {
        //                 _pickImage(ImageSource.camera);
        //                 Navigator.pop(context);
        //               },
        //             ),
        //             ListTile(
        //               leading: const Icon(Icons.photo),
        //               title: const Text('gallery').tr(),
        //               onTap: () {
        //                 _pickImage(ImageSource.gallery);
        //                 Navigator.pop(context);
        //               },
        //             ),
        //           ],
        //         ),
        //       ),
        // );

        // showModalBottomSheet(
        //   isScrollControlled: true,
        //   isDismissible: true,
        //   backgroundColor: Colors.white,
        //   context: context,
        //   shape: RoundedRectangleBorder(
        //     borderRadius: BorderRadius.vertical(
        //       top: Radius.circular(50),
        //       bottom: Radius.circular(50),
        //     ),
        //   ),
        //   builder: (BuildContext context) {
        //     return Container(
        //       decoration: BoxDecoration(
        //         color: Colors.white,
        //         borderRadius: BorderRadius.vertical(
        //           top: Radius.circular(50),
        //           bottom: Radius.circular(50),
        //         ),
        //       ),
        //       margin: const EdgeInsets.all(5),
        //       height: 150,
        //       child: Container(
        //         height: 150,
        //         decoration: BoxDecoration(
        //           color: currentThemeColor,
        //           borderRadius: BorderRadius.vertical(
        //             top: Radius.circular(50),
        //             bottom: Radius.circular(50),
        //           ),
        //         ),
        //         child: Column(
        //           mainAxisSize: MainAxisSize.min,
        //           mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        //           children: [
        //             ChoosingImageAvatarModelButton(
        //               icon: Icons.camera_alt,
        //               text: 'camera',
        //               onImagePicked: () {
        //                 _pickImage(ImageSource.camera);
        //                 Navigator.pop(context);
        //               },
        //             ),
        //             Divider(
        //               color: Colors.white,
        //               thickness: .5,
        //               indent: 50,
        //               endIndent: 50,
        //             ),
        //             ChoosingImageAvatarModelButton(
        //               icon: Icons.photo,
        //               text: 'gallery',
        //               onImagePicked: () {
        //                 _pickImage(ImageSource.gallery);
        //                 Navigator.pop(context);
        //               },
        //             ),
        //           ],
        //         ),
        //       ),
        //     );
        //   },
        // );
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
