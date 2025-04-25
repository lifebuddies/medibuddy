import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:medibuddy/consts.dart';

class UserEmailDetail extends StatelessWidget {
  const UserEmailDetail({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          'email_:'.tr(),
          style: TextStyle(
            color: textBlackColor,
            fontSize: 22,
            fontWeight: FontWeight.bold,
          ),
        ),
        const SizedBox(height: 10),
        Padding(
          padding: const EdgeInsets.only(left: 20),
          child: Text(
            "mmohmmeayman710@gmailcom",
            style: TextStyle(
              color: currentDividerColor,
              fontSize: 18,
              fontWeight: FontWeight.w500,
            ),
          ),
        ),
      ],
    );
  }
}
