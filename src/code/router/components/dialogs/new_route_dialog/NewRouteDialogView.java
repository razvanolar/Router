package code.router.components.dialogs.new_route_dialog;

import code.router.utils.RouterUtils;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by razvanolar on 06.01.2017
 */
public class NewRouteDialogView implements NewRouteDialogController.INewRouteDialogView {

  private GridPane mainContainer;
  private JFXTextField nameField;

  public NewRouteDialogView() {
    inti();
  }

  private void inti() {
    nameField = new JFXTextField();
    mainContainer = new GridPane();

    mainContainer.add(nameField, 0, 0);
    mainContainer.setPadding(new Insets(15, 5, 5, 5));
    mainContainer.setHgap(10);
    mainContainer.setAlignment(Pos.CENTER);

    nameField.setMinWidth(250);

    nameField.setFocusColor(RouterUtils.getDefaultFocusColor());
    nameField.setPromptText("Tab Name");
    nameField.setLabelFloat(true);
  }

  public TextField getNameField() {
    return nameField;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
