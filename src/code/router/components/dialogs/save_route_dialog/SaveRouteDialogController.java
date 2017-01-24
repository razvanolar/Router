package code.router.components.dialogs.save_route_dialog;

import code.router.utils.Controller;
import code.router.utils.validators.StringValidator;
import code.router.utils.View;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by razvanolar on 07.01.2017
 */
public class SaveRouteDialogController implements Controller<SaveRouteDialogController.ISaveRouteDialogView> {

  public interface ISaveRouteDialogView extends View {
    TextField getRouteNameTextField();
    TextField getRoutePathTextField();
  }

  private ISaveRouteDialogView view;
  private Button actionButton;

  public SaveRouteDialogController(Button actionButton) {
    this.actionButton = actionButton;
  }

  @Override
  public void bind(ISaveRouteDialogView view) {
    this.view = view;

    actionButton.setDisable(!isValid());
  }

  public String getRouteName() {
    return view.getRouteNameTextField().getText();
  }

  public String getRoutePath() {
    return view.getRoutePathTextField().getText();
  }

  public boolean isValid() {
    return isValidName() && isValidPath();
  }

  private boolean isValidName() {
    return !StringValidator.isNullOrEmpty(view.getRouteNameTextField().getText());
  }

  private boolean isValidPath() {
    return !StringValidator.isNullOrEmpty(view.getRoutePathTextField().getText());
  }
}
