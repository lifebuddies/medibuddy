import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:medibuddy/consts.dart';

class UserNameDetail extends StatelessWidget {
  const UserNameDetail({super.key});

  @override
  Widget build(BuildContext context) {
    return Text(
      'mohmmed_ayman'.tr(),
      style: TextStyle(
        color: textBlackColor,
        fontSize: 22,
        fontWeight: FontWeight.bold,
      ),
    );
  }
}
