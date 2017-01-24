package code.router.components.map.map_content;

import code.router.EventBus;
import code.router.components.map.MapController;
import code.router.components.map.map_content.utils.MapContentUtils;
import code.router.components.map.map_context_menu.MapContextMenuController;
import code.router.events.mask_unmask_window_event.MaskWindowEvent;
import code.router.events.mask_unmask_window_event.UnmaskWindowEvent;
import code.router.events.routes_events.find_route_event.FindRouteEvent;
import code.router.events.routes_events.find_route_event.FindRouteEventHandler;
import code.router.events.routes_events.find_route_in_new_window_event.FindRouteInNewWindowEvent;
import code.router.events.routes_events.find_route_in_new_window_event.FindRouteInNewWindowEventHandler;
import code.router.events.show_new_route_dialog_event.ShowNewRouteDialogEvent;
import code.router.model.routes.Route;
import code.router.utils.Component;
import code.router.utils.Controller;
import code.router.utils.RouterUtils;
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

  private MapController controller;
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

    EventBus.addHandler(FindRouteEvent.TYPE, (FindRouteEventHandler) event -> {
      if (!controller.isActive() || utils == null)
        return;
      utils.onFindRouteEvent(event.getRoute());
    });

    EventBus.addHandler(FindRouteInNewWindowEvent.TYPE, (FindRouteInNewWindowEventHandler) event -> {
      if (!controller.isActive() || utils == null)
        return;
      Route route = utils.getCurrentRoute();
      if (route != null) {
        EventBus.fireEvent(new ShowNewRouteDialogEvent(route));
      }
    });
  }

  public void loadMap(Route route) {
    EventBus.fireEvent(new MaskWindowEvent("Loading Map Resources..."));
    WebView webView = createWebView();
    this.view.setMap(webView);
    webEngine = webView.getEngine();
    webEngine.load(RouterUtils.getHtmlMapFile());
    utils  = new MapContentUtils(webEngine);

    Worker<Void> loadWorker = webEngine.getLoadWorker();
    loadWorker.stateProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue == Worker.State.SUCCEEDED) {
        utils.mapHeightChanged(this.view.getMainContainer().getHeight());
        utils.setDomLoaded(true);
        EventBus.fireEvent(new UnmaskWindowEvent());
        if (route != null) {
          EventBus.fireEvent(new FindRouteEvent(route));
        }
      }
    });
  }

  public void injectParentController(MapController controller) {
    this.controller = controller;
  }

  public MapContentUtils getUtils() {
    return utils;
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
