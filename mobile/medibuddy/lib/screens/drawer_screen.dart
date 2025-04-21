import 'package:flutter/material.dart';
import 'package:medibuddy/screens/main_app_screen.dart';
import 'package:medibuddy/screens/profile_screen.dart';
import 'package:medibuddy/screens/tes_theme_screen.dart';
import 'package:medibuddy/widgets/drawer_item.dart';

// ignore: must_be_immutable
// class DrawerScreen extends StatelessWidget {
//   const DrawerScreen({super.key});
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       backgroundColor: Colors.blue.shade900,
//       body: Container(
//         width: double.infinity,
//         height: double.infinity,
//         padding: const EdgeInsets.only(top: 60, left: 20, right: 20),
//         child: Column(
//           crossAxisAlignment: CrossAxisAlignment.start,
//           children: [
//             // صورة البروفايل
//             const CircleAvatar(
//               backgroundImage: AssetImage('lib/assets/images/defultUser.jpeg'),
//               radius: 100,
//             ),
//             const SizedBox(height: 20),
//             // اسم المستخدم
//             const Text(
//               'Mohamed Ghoniem',
//               style: TextStyle(
//                 color: Colors.white,
//                 fontSize: 22,
//                 fontWeight: FontWeight.bold,
//               ),
//             ),
//             const SizedBox(height: 40),
//             const Divider(indent: 10, endIndent: 25),
//             const SizedBox(height: 20),
//             DrawerItem(
//               icon: Icons.home,
//               title: 'Home',
//               onPressed: () {
//                 Navigator.push(
//                   context,
//                   CupertinoPageRoute(
//                     builder: (context) {
//                       return MainAppScreen();

//                     },
//                   ),
//                 );
//               },
//             ),
//             const SizedBox(height: 5),

//             DrawerItem(
//               icon: Icons.person,
//               title: 'Profile',
//               onPressed: () {
//                 Navigator.push(
//                   context,
//                   CupertinoPageRoute(
//                     builder: (context) {
//                       return TesThemeScreen();
//                     },
//                   ),
//                 );
//               },
//             ),
//             const SizedBox(height: 5),

//             DrawerItem(
//               icon: Icons.settings,
//               title: 'Settings',
//               onPressed: () {
//                 Navigator.push(
//                   context,
//                   CupertinoPageRoute(
//                     builder: (context) {
//                       return TesThemeScreen();
//                     },
//                   ),
//                 );
//               },
//             ),
//             const SizedBox(height: 5),

//             DrawerItem(
//               icon: Icons.local_pharmacy_rounded,
//               title: 'New plan',
//               onPressed: () {
//                 Navigator.push(
//                   context,
//                   CupertinoPageRoute(
//                     builder: (context) {
//                       return TesThemeScreen();
//                     },
//                   ),
//                 );
//               },
//             ),

//             // عناصر المنيو
//           ],
//         ),
//       ),
//     );
//   }
// }

class DrawerScreen extends StatelessWidget {
  final Function(Widget) onItemSelected;
  const DrawerScreen({super.key, required this.onItemSelected});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blue.shade900,
      body: Container(
        padding: const EdgeInsets.only(top: 60, left: 20, right: 20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // صورة البروفايل
            const CircleAvatar(
              backgroundImage: AssetImage('lib/assets/images/defultUser.jpeg'),
              radius: 100,
            ),
            const SizedBox(height: 20),
            const Text(
              'Mohamed Ghoniem',
              style: TextStyle(
                color: Colors.white,
                fontSize: 22,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 40),
            const Divider(indent: 10, endIndent: 25),
            const SizedBox(height: 20),

            DrawerItem(
              icon: Icons.home,
              title: 'Home',
              onPressed: () => onItemSelected(const MainAppScreen()),
            ),
            const SizedBox(height: 5),
            DrawerItem(
              icon: Icons.person,
              title: 'Profile',
              onPressed: () => onItemSelected(const ProfileScreen()),
            ),
            const SizedBox(height: 5),
            DrawerItem(
              icon: Icons.settings,
              title: 'Settings',
              onPressed: () => onItemSelected(const TesThemeScreen()),
            ),
            const SizedBox(height: 5),
            DrawerItem(
              icon: Icons.local_pharmacy_rounded,
              title: 'New plan',
              onPressed: () => onItemSelected(const TesThemeScreen()),
            ),
          ],
        ),
      ),
    );
  }
}
