package code.router;

import code.router.events.load_resources_events.LoadResourcesEvent;
import code.router.events.load_resources_events.LoadResourcesEventHandler;
import code.router.events.load_resources_events.load_map_event.LoadMapEvent;
import code.router.events.mask_unmask_window_event.MaskWindowEvent;
import code.router.events.mask_unmask_window_event.UnmaskWindowEvent;
import code.router.events.new_route_event.NewRouteViewEvent;
import code.router.utils.Component;
import code.router.utils.Controller;
import code.router.utils.View;
import code.router.utils.factories.ComponentFactory;
import code.router.utils.types.ComponentTypes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by razvanolar on 29.12.2016
 */
public class RouterController implements Controller<RouterController.IRouterView> {

  interface IRouterView extends View {
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

    EventBus.addHandler(LoadResourcesEvent.TYPE, (LoadResourcesEventHandler) event -> {
      EventBus.fireEvent(new MaskWindowEvent("Loading Resources..."));
      // when the project state will be loaded from the disk, NewRouteViewEvent will be fired only when no other map views are available
      EventBus.fireEvent(new NewRouteViewEvent("New Routes View", ComponentFactory.createComponent(ComponentTypes.MAP)));
      EventBus.fireEvent(new LoadMapEvent());
      EventBus.fireEvent(new UnmaskWindowEvent());
    });
  }

  public View getRouterView() {
    return routerView;
  }
}
