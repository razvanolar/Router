package code.router.components.map.map_content;

import code.router.EventBus;
import code.router.RouterController;
import code.router.components.map.map_content.utils.MapContentUtils;
import code.router.components.map.map_context_menu.MapContextMenuController;
import code.router.events.load_resources_events.load_map_event.LoadMapEvent;
import code.router.events.load_resources_events.load_map_event.LoadMapEventHandler;
import code.router.events.mask_unmask_window_event.MaskWindowEvent;
import code.router.events.mask_unmask_window_event.UnmaskWindowEvent;
import code.router.utils.Component;
import code.router.utils.Controller;
import code.router.utils.RoutesUtils;
import code.router.utils.View;
import code.router.utils.factories.ComponentFactory;
import code.router.utils.types.ComponentTypes;
import javafx.concurrent.Worker;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Created by razvanolar on 29.12.2016
 */
public class MapContentController implements Controller<MapContentController.IMapContentView> {

  public interface IMapContentView extends View {
    void setMap(Node map);
    BorderPane getMainContainer();
  }

  private WebEngine webEngine;
  private MapContentUtils utils;
  private IMapContentView view;

  @Override
  public void bind(IMapContentView view) {
    this.view = view;

    view.getMainContainer().heightProperty().addListener((observable, oldValue, newValue) -> {
      if (utils != null) {
        utils.mapHeightChanged(newValue.doubleValue());
      }
    });
  }

  public void loadMap() {
    EventBus.fireEvent(new MaskWindowEvent("Loading Map Resources..."));
    WebView webView = createWebView();
    this.view.setMap(webView);
    webEngine = webView.getEngine();
    webEngine.load(RoutesUtils.getHtmlMapFile());
    utils  = new MapContentUtils(webEngine);

    Worker<Void> loadWorker = webEngine.getLoadWorker();
    loadWorker.stateProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue == Worker.State.SUCCEEDED) {
        utils.mapHeightChanged(this.view.getMainContainer().getHeight());
        utils.setDomLoaded(true);
        EventBus.fireEvent(new UnmaskWindowEvent());
      }
    });
  }

  private WebView createWebView() {
    WebView webView = new WebView();
    webView.setContextMenuEnabled(false);
    Component component = ComponentFactory.createComponent(ComponentTypes.MAP_CONTEXT_MENU);
    ContextMenu contextMenu = null;
    if (component != null && component.getView() != null &&
            component.getView() instanceof MapContextMenuController.IMapContextMenuView) {
      contextMenu = ((MapContextMenuController.IMapContextMenuView) component.getView()).getContextMenu();
    }
    ContextMenu finalContextMenu = contextMenu;
    webView.setOnMouseClicked(event -> {
      if (finalContextMenu != null) {
        finalContextMenu.hide();
        if (event.getButton() == MouseButton.SECONDARY) {
          finalContextMenu.show(webView, event.getScreenX(), event.getScreenY());
        }
      }
    });
    return webView;
  }
}
