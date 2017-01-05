package code.router.utils;

import java.io.File;

/**
 * Created by razvanolar on 03.01.2017
 */
public class RoutesUtils {

  public static String getDefaultTheme() {
    return getFilePath(new File(RouterConstants.DEFAULT_THEME));
  }

  public static String getHtmlMapFile() {
    return getFilePath(new File(RouterConstants.MAP_HTML_PATH));
  }

  private static String getFilePath(File file) {
    return file.exists() ? "file:///" + file.getAbsolutePath().replace("\\", "/") : null;
  }
}
