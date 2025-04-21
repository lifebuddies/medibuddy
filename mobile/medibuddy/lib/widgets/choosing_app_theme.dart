import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:medibuddy/consts.dart';
import 'package:medibuddy/cubit/theme_cubit.dart';

class ChoosingAppTheme extends StatelessWidget {
  const ChoosingAppTheme({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          "Select Theme :",
          style: TextStyle(
            color: Colors.black,
            fontSize: 22,
            fontWeight: FontWeight.bold,
          ),
        ),
        const SizedBox(height: 10),
        Padding(
          padding: const EdgeInsets.only(left: 20),
          child: SizedBox(
            height: 50,
            width: 200,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                IconButton(
                  onPressed: () {
                    themeColor = context.read<ThemeCubit>().changTheme(
                      "BlueTheme",
                    );
                  },
                  icon: Icon(
                    Icons.circle,
                    color: Colors.blue.shade900,
                    size: 30,
                  ),
                ),
                IconButton(
                  onPressed: () {
                    themeColor = context.read<ThemeCubit>().changTheme(
                      "PinkTheme",
                    );
                  },
                  icon: Icon(Icons.circle, color: Colors.pink, size: 30),
                ),
                IconButton(
                  onPressed: () {
                    themeColor = context.read<ThemeCubit>().changTheme(
                      "GreenTheme",
                    );
                  },
                  icon: Icon(
                    Icons.circle,
                    color: const Color.fromARGB(255, 74, 195, 94),
                    size: 30,
                  ),
                ),
              ],
            ),
          ),
        ),
      ],
    );
  }
}
