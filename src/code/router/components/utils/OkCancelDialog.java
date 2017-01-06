package code.router.components.utils;

import code.router.utils.RoutesUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * Created by razvanolar on 06.01.2017
 */
public class OkCancelDialog {

  private Stage stage;
  private Scene scene;

  private BorderPane mainContainer;
  private Button okButton;
  private Button cancelButton;
  private Window owner;

  private String title;

  public OkCancelDialog(String title, Window owner) {
    this.title = title;
    this.owner = owner;
    init();
  }

  private void init() {
    stage = new Stage(StageStyle.UTILITY);
    okButton = new Button("OK");
    cancelButton = new Button("Cancel");
    HBox buttonsContainer = new HBox(5, okButton, cancelButton);
    mainContainer = new BorderPane();

    okButton.setPrefWidth(70);
    cancelButton.setPrefWidth(70);

    buttonsContainer.setAlignment(Pos.CENTER_RIGHT);
    buttonsContainer.setPadding(new Insets(5));

    mainContainer.setBottom(buttonsContainer);
  }

  private void initStateComponents() {
    addListeners();
    if (scene == null) {
      scene = new Scene(mainContainer);
      String themePath = RoutesUtils.getDefaultTheme();
      if (themePath != null)
        scene.getStylesheets().add(themePath);
      stage.setScene(scene);
      stage.setTitle(title);
      stage.setAlwaysOnTop(true);
      stage.sizeToScene();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(true);
      stage.setAlwaysOnTop(true);
      if (owner != null)
        stage.initOwner(owner);
    }
  }

  private void addListeners() {
    cancelButton.setOnAction(event -> {
      if (stage != null)
        stage.close();
    });
  }

  public void setContent(Node node) {
    mainContainer.setCenter(node);
  }

  public Button getOkButton() {
    return okButton;
  }

  public void show() {
    initStateComponents();
    stage.show();
  }

  public void close() {
    if (stage != null)
      stage.close();
  }
}
