package code.router.components.dialogs.new_route_dialog;

import code.router.utils.Controller;
import code.router.utils.StringValidator;
import code.router.utils.View;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by razvanolar on 06.01.2017
 */
public class NewRouteDialogController implements Controller<NewRouteDialogController.INewRouteDialogView> {

  public interface INewRouteDialogView extends View {
    TextField getNameField();
  }

  private Button actionButton;
  private INewRouteDialogView view;

  public NewRouteDialogController(Button actionButton) {
    this.actionButton = actionButton;
  }

  @Override
  public void bind(INewRouteDialogView view) {
    this.view = view;

    view.getNameField().textProperty().addListener((observable, oldValue, newValue) -> {
      actionButton.setDisable(StringValidator.isNullOrEmpty(newValue));
    });
  }

  public String getTabName() {
    return view.getNameField().getText();
  }
}
