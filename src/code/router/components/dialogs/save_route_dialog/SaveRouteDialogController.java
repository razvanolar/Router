package code.router.components.dialogs.save_route_dialog;

import code.router.model.other.SaveRouteDialogEntity;
import code.router.utils.DialogController;
import code.router.utils.callbacks.DialogCallback;
import code.router.utils.validators.StringValidator;
import code.router.utils.View;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by razvanolar on 07.01.2017
 */
public class SaveRouteDialogController extends DialogController<SaveRouteDialogController.ISaveRouteDialogView, SaveRouteDialogEntity> {

  public interface ISaveRouteDialogView extends View {
    TextField getRouteNameTextField();
    TextField getRoutePathTextField();
  }

  private ISaveRouteDialogView view;

  public SaveRouteDialogController(Button actionButton, DialogCallback<SaveRouteDialogEntity> callback) {
    super(actionButton, callback);
  }

  @Override
  public void bind(ISaveRouteDialogView view) {
    this.view = view;
    actionButton.setDisable(!isValid());
  }

  @Override
  public View getView() {
    return view;
  }

  @Override
  public SaveRouteDialogEntity collect() {
    return new SaveRouteDialogEntity(view.getRouteNameTextField().getText(), view.getRoutePathTextField().getText());
  }

  private boolean isValid() {
    return isValidName() && isValidPath();
  }

  private boolean isValidName() {
    return !StringValidator.isNullOrEmpty(view.getRouteNameTextField().getText());
  }

  private boolean isValidPath() {
    return !StringValidator.isNullOrEmpty(view.getRoutePathTextField().getText());
  }
}
