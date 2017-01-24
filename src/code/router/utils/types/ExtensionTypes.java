package code.router.utils.types;

/**
 * Created by razvanolar on 24.01.2017
 */
public enum ExtensionTypes {

  ROUTE(".route");

  String extension;

  ExtensionTypes(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }
}
