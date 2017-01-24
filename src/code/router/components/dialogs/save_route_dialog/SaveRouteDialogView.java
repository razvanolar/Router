package code.router.components.dialogs.save_route_dialog;

import code.router.utils.RouterUtils;
import com.jfoenix.controls.JFXTextField;
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
  private JFXTextField routeNameTextField;
  private JFXTextField routePathTextField;

  public SaveRouteDialogView(String routeName, String routePath) {
    init(routeName, routePath);
  }

  private void init(String routeName, String routePath) {
    routeNameTextField = new JFXTextField(routeName);
    routePathTextField = new JFXTextField(routePath != null ? routePath : "C:\\Users\\razvanolar\\Desktop\\RouterUtil");
    mainContainer = new GridPane();

    mainContainer.setAlignment(Pos.CENTER);
    mainContainer.setPadding(new Insets(15, 5, 5, 5));
    mainContainer.setHgap(5);
    mainContainer.setVgap(13);
    mainContainer.add(routeNameTextField, 0, 0);
    mainContainer.add(routePathTextField, 0, 1);

    routeNameTextField.setMinWidth(350);
    routePathTextField.setMinWidth(300);

    routeNameTextField.setFocusColor(RouterUtils.getDefaultFocusColor());
    routeNameTextField.setPromptText("Name");
    routeNameTextField.setLabelFloat(true);
    routePathTextField.setFocusColor(RouterUtils.getDefaultFocusColor());
    routePathTextField.setPromptText("Path");
    routePathTextField.setLabelFloat(true);
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
