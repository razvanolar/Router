package code;

import code.router.EventBus;
import code.router.RouterController;
import code.router.events.load_resources_events.LoadResourcesEvent;
import code.router.utils.RoutesUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by razvanolar on 29.12.2016
 */
public class RouterApplication extends Application {

  private Scene routerScene;

  @Override
  public void init() throws Exception {
    BorderPane routerMainContainer = new BorderPane();
    try {
      RouterController routerController = new RouterController();
      routerController.bind(null);
      routerMainContainer.setCenter(routerController.getRouterView().asNode());
    } catch (Exception e) {
      e.printStackTrace();
    }
    routerScene = new Scene(routerMainContainer, 600, 350);
    String defaultTheme = RoutesUtils.getDefaultTheme();
    if (defaultTheme != null)
      routerScene.getStylesheets().add(defaultTheme);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Hello World");
    primaryStage.setScene(routerScene);
    primaryStage.setMaximized(true);
    primaryStage.show();
    EventBus.fireEvent(new LoadResourcesEvent());
  }

  public static void main(String[] args) {
    try {
      launch(args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
