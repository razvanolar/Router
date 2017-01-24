package code.router.components.dialogs.new_route_dialog;

import code.router.model.other.StringEntity;
import code.router.utils.DialogController;
import code.router.utils.callbacks.DialogCallback;
import code.router.utils.validators.StringValidator;
import code.router.utils.View;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by razvanolar on 06.01.2017
 */
public class NewRouteDialogController extends DialogController<NewRouteDialogController.INewRouteDialogView, StringEntity> {

  public interface INewRouteDialogView extends View {
    TextField getNameField();
  }

  private INewRouteDialogView view;

  public NewRouteDialogController(Button actionButton, DialogCallback<StringEntity> callback) {
    super(actionButton, callback);
  }

  @Override
  public void bind(INewRouteDialogView view) {
    this.view = view;

    actionButton.setDisable(true);

    view.getNameField().textProperty().addListener((observable, oldValue, newValue) -> {
      actionButton.setDisable(StringValidator.isNullOrEmpty(newValue));
    });
  }

  @Override
  public View getView() {
    return view;
  }

  @Override
  public StringEntity collect() {
    return new StringEntity(view.getNameField().getText());
  }
}
