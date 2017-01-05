package code.router.components.footer;

import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Created by razvanolar on 29.12.2016
 */
public class FooterView implements FooterController.IFooterView {

  private HBox mainContainer;

  public FooterView() {
    init();
  }

  private void init() {
    mainContainer = new HBox();

    mainContainer.setMinHeight(25);
    mainContainer.setMaxHeight(25);
    mainContainer.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
