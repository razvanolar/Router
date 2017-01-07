package code.router;

import code.router.components.utils.EventsListener;
import code.router.events.load_resources_events.LoadResourcesEvent;
import code.router.events.load_resources_events.LoadResourcesEventHandler;
import code.router.events.new_route_event.AddNewRouteViewEvent;
import code.router.utils.Component;
import code.router.utils.Controller;
import code.router.utils.View;
import code.router.utils.factories.ComponentFactory;
import code.router.utils.types.ComponentTypes;
import javafx.scene.control.TabPane;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by razvanolar on 29.12.2016
 */
public class RouterController implements Controller<RouterController.IRouterView> {

  public static Object SELECTED_MAP_CONTROLLER;

  interface IRouterView extends View {
    TabPane getTabPane();
  }

  private IRouterView routerView;
  private Map<ComponentTypes, Component> componentsMap;

  public RouterController() throws Exception {
    componentsMap = new HashMap<>();
    init();
  }

  private void init() throws Exception {
    for (ComponentTypes type : ComponentTypes.mainTypes()) {
      Component component = ComponentFactory.createComponent(type);
      if (component != null)
        componentsMap.put(type, component);
    }
    routerView = new RouterView(componentsMap);
  }

  @Override
  public void bind(IRouterView view) {
    // ignore received view object, routerView is computed internally

    routerView.getTabPane().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null && newValue.getUserData() != null) {
        SELECTED_MAP_CONTROLLER = newValue.getUserData();
      }
    });

    EventBus.addHandler(LoadResourcesEvent.TYPE, (LoadResourcesEventHandler) event -> {
      // when the project state will be loaded from the disk, AddNewRouteViewEvent will be fired only when no other map views are available
      EventBus.fireEvent(new AddNewRouteViewEvent("New Routes View"));
    });

    new EventsListener();
  }

  public View getRouterView() {
    return routerView;
  }
}
