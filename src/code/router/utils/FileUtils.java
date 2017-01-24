package code.router.utils;

import code.router.utils.types.ExtensionTypes;
import code.router.utils.validators.StringValidator;

/**
 * Created by razvanolar on 24.01.2017
 */
public class FileUtils {

   public static boolean isRouteFileName(String name) {
    return !StringValidator.isNullOrEmpty(name) && name.toLowerCase().endsWith(ExtensionTypes.ROUTE.getExtension());
  }

  public static String routeName(String name) {
    if (StringValidator.isNullOrEmpty(name))
      return null;
    if (isRouteFileName(name))
      return name;

    return name.replace("\\s", "_") + ExtensionTypes.ROUTE.getExtension();
  }

  public static String path(String path) {
    if (StringValidator.isNullOrEmpty(path))
      return null;
    return path.endsWith("\\") ? path : path + "\\";
  }
}