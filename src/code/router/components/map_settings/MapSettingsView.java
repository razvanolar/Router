package code.router.components.map_settings;

import code.router.utils.RouterConstants;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by razvanolar on 29.12.2016
 */
public class MapSettingsView implements MapSettingsController.IMapSettingsView {

  private BorderPane mainContainer;

  public MapSettingsView() {
    init();
  }

  private void init() {
    mainContainer = new BorderPane();
  }

  private HBox createHBoxLabel(String title) {
    HBox hBox = new HBox();
    hBox.setAlignment(Pos.CENTER_LEFT);
    hBox.getChildren().add(new Label(title));
    hBox.getStyleClass().add(RouterConstants.HEADER_PANE_CLASS);
    return hBox;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
