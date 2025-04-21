import 'package:flutter/material.dart';
import 'package:flutter_zoom_drawer/flutter_zoom_drawer.dart';

class CustomDrawerButton extends StatelessWidget {
  const CustomDrawerButton({super.key});

  @override
  Widget build(BuildContext context) {
    return Positioned(
      top: 33,
      left: -10,
      child: Builder(
        builder:
            (context) => MaterialButton(
              onPressed: () {
                ZoomDrawer.of(context)?.toggle();
              },
              child: const SizedBox(
                height: 100,
                child: Icon(Icons.menu, size: 30, color: Colors.white),
              ),
            ),
      ),
    );
  }
}
