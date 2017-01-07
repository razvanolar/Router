package code.router.components.dialogs.save_route_dialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by razvanolar on 07.01.2017
 */
public class SaveRouteDialogView implements SaveRouteDialogController.ISaveRouteDialogView {

  private GridPane mainContainer;
  private TextField routeNameTextField;
  private TextField routePathTextField;

  public SaveRouteDialogView(String routeName, String routePath) {
    init(routeName, routePath);
  }

  private void init(String routeName, String routePath) {
    routeNameTextField = new TextField(routeName);
    routePathTextField = new TextField(routePath);
    mainContainer = new GridPane();

    mainContainer.setAlignment(Pos.CENTER);
    mainContainer.setPadding(new Insets(5));
    mainContainer.setHgap(5);
    mainContainer.setVgap(5);
    mainContainer.add(new Label("Name: "), 0, 0);
    mainContainer.add(routeNameTextField, 1, 0);
    mainContainer.add(new Label("Path: "), 0, 1);
    mainContainer.add(routePathTextField, 1, 1);

    routeNameTextField.setMinWidth(300);
    routePathTextField.setMinWidth(300);
  }

  public TextField getRouteNameTextField() {
    return routeNameTextField;
  }

  public TextField getRoutePathTextField() {
    return routePathTextField;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
