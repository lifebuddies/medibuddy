
import 'package:bloc/bloc.dart';

part 'theme_state.dart';

class ThemeCubit extends Cubit<ThemeState> {
  ThemeCubit() : super(ThemeInitial());
  String changTheme(String theme) {
    try {
      emit(ThemeChanged(theme: theme));
    } catch (e) {
      emit(ThemeError(e.toString()));
    }
    return theme;
  }
}
