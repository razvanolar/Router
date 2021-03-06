package code.router.utils.validators;

import code.router.utils.FileUtils;

import java.io.File;

/**
 * Created by razvanolar on 24.01.2017
 */
public class RouteFileValidator implements FileValidator {

  @Override
  public boolean isValidFile(File file) {
    return file != null && FileUtils.isRouteFileName(file.getName());
  }
}
