import 'package:bloc/bloc.dart';
import 'package:medibuddy/consts.dart';

part 'theme_state.dart';

class ThemeCubit extends Cubit<ThemeState> {
  ThemeCubit() : super(ThemeInitial());
  String changTheme(String theme) {
    try {
      final selectedTheme = allThemes[theme];
      if (selectedTheme != null) {
        applyTheme(selectedTheme);
        emit(ThemeChanged(theme: theme));
        saveTheme(theme);
      } else {
        throw Exception("Invalid theme name: $theme");
      }
    } catch (e) {
      emit(ThemeError(e.toString()));
    }
    return theme;
  }
}
