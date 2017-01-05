package code.router.components.masking_indicator;

import code.router.utils.RouterConstants;
import code.router.utils.View;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Created by razvanolar on 03.01.2017
 */
public class MaskPane implements View {

  private BorderPane mainContainer;
  private Label messageLabel;

  public MaskPane() {
    init();
  }

  private void init() {
    GridPane maskPane = new GridPane();
    mainContainer = new BorderPane(maskPane);
    messageLabel = new Label();
    ProgressIndicator progressIndicator = new ProgressIndicator();

    maskPane.add(progressIndicator, 0, 0);
    maskPane.add(messageLabel, 1, 0);
    maskPane.setHgap(10);
    maskPane.setAlignment(Pos.CENTER);
    mainContainer.getStyleClass().add(RouterConstants.MASK_PANE_CLASS);
  }

  public void setMessageText(String text) {
    this.messageLabel.setText(text);
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
