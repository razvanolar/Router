package code.router.components.dialogs.new_route_dialog;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by razvanolar on 06.01.2017
 */
public class NewRouteDialogView implements NewRouteDialogController.INewRouteDialogView {

  private GridPane mainContainer;
  private TextField nameField;

  public NewRouteDialogView() {
    inti();
  }

  private void inti() {
    nameField = new TextField();
    mainContainer = new GridPane();

    mainContainer.add(new Label("Tab Name: "), 0, 0);
    mainContainer.add(nameField, 1, 0);
    mainContainer.setPadding(new Insets(5));
    mainContainer.setHgap(10);

    nameField.setMinWidth(250);
  }

  public TextField getNameField() {
    return nameField;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
