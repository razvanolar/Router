package code.router.components.routes_tree;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Created by razvanolar on 29.12.2016
 */
public class RoutesTreeView implements RoutesTreeController.IRoutesTreeView {

  private BorderPane mainContainer;

  public RoutesTreeView() {
    init();
  }

  private void init() {
    mainContainer = new BorderPane(new Label("Routes Tree View"));
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
