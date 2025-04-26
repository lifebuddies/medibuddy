import 'package:flutter/material.dart';
import 'dart:math' as math;

class AnimatedBuble extends StatefulWidget {
  const AnimatedBuble({
    super.key,
    required this.top,
    required this.left,
    required this.image,
    required this.height,
    required this.width,
  });
  final double top;
  final double left;
  final String image;
  final double height;
  final double width;

  @override
  State<AnimatedBuble> createState() => _AnimatedBubleState();
}

class _AnimatedBubleState extends State<AnimatedBuble>
    with SingleTickerProviderStateMixin {
  late AnimationController _controller;
  late Animation<double> _rotation;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      duration: const Duration(seconds: 10),
      vsync: this,
    )..repeat(reverse: false);

    _rotation = Tween<double>(
      begin: 0,
      end: 2 * math.pi,
    ).animate(CurvedAnimation(parent: _controller, curve: Curves.linear));
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Positioned(
      top: widget.top,
      left: widget.left,
      child: AnimatedBuilder(
        animation: _controller,
        builder: (context, child) {
          return Transform.rotate(
            angle: _rotation.value,
            child: Opacity(
              opacity: 1, // علشان تبقى نعومة أكتر في الخلفية
              child: Image.asset(
                widget.image,
                width: widget.width,
                height: widget.height,
                fit: BoxFit.contain,
                // color: const Color.fromARGB(
                //   255,
                //   123,
                //   0,
                //   255,
                // ), // لون أبيض مع شفافية
              ),
            ),
          );
        },
      ),
    );
  }
}
