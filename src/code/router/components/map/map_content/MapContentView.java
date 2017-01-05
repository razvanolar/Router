package code.router.components.map.map_content;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Created by razvanolar on 29.12.2016
 */
public class MapContentView implements MapContentController.IMapContentView {

  private BorderPane mainContainer;

  public MapContentView() {
    init();
  }

  private void init() {
    mainContainer = new BorderPane();
  }

  public void setMap(Node map) {
    mainContainer.setCenter(map);
  }

  public BorderPane getMainContainer() {
    return mainContainer;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
