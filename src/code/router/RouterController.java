package code.router;

import code.router.events.show_dialog_event.ShowDialogEvent;
import code.router.events.show_dialog_event.ShowDialogEventHandler;
import code.router.events.utils.EventsListener;
import code.router.events.load_resources_events.LoadResourcesEvent;
import code.router.events.load_resources_events.LoadResourcesEventHandler;
import code.router.events.new_route_event.AddNewRouteViewEvent;
import code.router.events.routes_events.save_route_events.GenericSaveRouteEvent;
import code.router.events.routes_events.save_route_events.GenericSaveRouteEventHandler;
import code.router.events.routes_events.save_route_events.SaveCurrentRouteEvent;
import code.router.repository.Repository;
import code.router.utils.Component;
import code.router.utils.Controller;
import code.router.utils.View;
import code.router.utils.factories.ComponentFactory;
import code.router.utils.types.ComponentTypes;
import com.jfoenix.controls.JFXDialog;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by razvanolar on 29.12.2016
 */
public class RouterController implements Controller<RouterController.IRouterView> {

  public static Object SELECTED_MAP_CONTROLLER;

  interface IRouterView extends View {
    TabPane getTabPane();
    StackPane getStackPane();
  }

  private IRouterView routerView;
  private Map<ComponentTypes, Component> componentsMap;

  public RouterController() throws Exception {
    componentsMap = new HashMap<>();
    new Repository();
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
      EventBus.fireEvent(new AddNewRouteViewEvent("New_Routes_View"));
    });

    EventBus.addHandler(GenericSaveRouteEvent.TYPE, (GenericSaveRouteEventHandler) event -> {
      Tab selectedTab = routerView.getTabPane().getSelectionModel().getSelectedItem();
      if (selectedTab != null) {
        EventBus.fireEvent(new SaveCurrentRouteEvent(selectedTab.getText()));
      }
    });

    EventBus.addHandler(ShowDialogEvent.TYPE, (ShowDialogEventHandler) event -> {
      JFXDialog dialog = event.getDialog();
      dialog.setDialogContainer(routerView.getStackPane());
      dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
      dialog.show();
    });

    new EventsListener();
  }

  public View getRouterView() {
    return routerView;
  }
}
