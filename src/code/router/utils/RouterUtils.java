package code.router.utils;

import javafx.scene.paint.*;

import java.io.File;

/**
 * Created by razvanolar on 03.01.2017
 */
public class RouterUtils {

  public static String getDefaultTheme() {
    return getFilePath(new File(RouterConstants.DEFAULT_THEME));
  }

  public static String getHtmlMapFile() {
    return getFilePath(new File(RouterConstants.MAP_HTML_PATH));
  }

  private static String getFilePath(File file) {
    return file.exists() ? "file:///" + file.getAbsolutePath().replace("\\", "/") : null;
  }

  public static Color getDefaultFocusColor() {
    return javafx.scene.paint.Color.valueOf(RouterConstants.DEFAULT_TEXT_FIELD_FOCUS_COLOR);
  }
}
