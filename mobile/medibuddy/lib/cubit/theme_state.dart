part of 'theme_cubit.dart';

class ThemeState {}

final class ThemeInitial extends ThemeState {}

final class ThemeChanged extends ThemeState {
  ThemeChanged({required this.theme});
  final String theme;
}

final class ThemeError extends ThemeState {
  final String errorMessage;

  ThemeError(this.errorMessage);
}
