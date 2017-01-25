
package code.router.utils.resources;

import code.router.utils.RouterConstants;
import javafx.scene.image.Image;

import java.io.File;

/**
 *
 * Created by razvanolar on 24.01.2016.
 */
public class ImageProvider {

  public static final String PATH = RouterConstants.RESOURCES_PATH + "images\\";

  public static Image genericFile() {
    return getImage(new File(PATH + "generic_file.png"));
  }

  public static Image openFolder() {
    return getImage(new File(PATH + "open_folder.png"));
  }

  public static Image closeFolder() {
    return getImage(new File(PATH + "close_folder.png"));
  }

  public static Image getImage(File file) {
    return file.exists() ? new Image("file:///" + file.getAbsolutePath().replace("\\", "/")) : null;
  }
}
