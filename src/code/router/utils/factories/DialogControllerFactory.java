package code.router.utils.factories;

import code.router.components.dialogs.new_route_dialog.NewRouteDialogController;
import code.router.components.dialogs.save_route_dialog.SaveRouteDialogController;
import code.router.utils.DialogController;
import code.router.utils.callbacks.DialogCallback;
import code.router.utils.types.DialogComponentTypes;
import javafx.scene.control.Button;

/**
 * Created by razvanolar on 24.01.2017
 */
public class DialogControllerFactory {

  @SuppressWarnings("unchecked")
  public static DialogController createDialogController(DialogComponentTypes type, Button actionButton, DialogCallback callback) {
    switch (type) {
      case NEW_ROUTE_DIALOG:
        return new NewRouteDialogController(actionButton, callback);
      case SAVE_ROUTE_DIALOG:
        return new SaveRouteDialogController(actionButton, callback);
    }
    return null;
  }
}
