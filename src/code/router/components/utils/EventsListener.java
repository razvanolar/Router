package code.router.components.utils;

import code.router.EventBus;
import code.router.components.dialogs.new_route_dialog.NewRouteDialogController;
import code.router.components.dialogs.new_route_dialog.NewRouteDialogView;
import code.router.events.load_resources_events.load_map_event.LoadMapEvent;
import code.router.events.new_route_event.AddNewRouteViewEvent;
import code.router.events.show_new_route_dialog_event.ShowNewRouteDialogEvent;
import code.router.events.show_new_route_dialog_event.ShowNewRouteDialogEventHandler;
import code.router.utils.factories.ComponentFactory;
import code.router.utils.types.ComponentTypes;

/**
 * Created by razvanolar on 06.01.2017
 */
public class EventsListener {

  public EventsListener() {
    addListeners();
  }

  private void addListeners() {
    EventBus.addHandler(ShowNewRouteDialogEvent.TYPE, (ShowNewRouteDialogEventHandler) event -> {
      OkCancelDialog dialog = new OkCancelDialog("New Route View", null);
      dialog.getOkButton().setDisable(true);
      NewRouteDialogController newRouteDialogController = new NewRouteDialogController(dialog.getOkButton());
      NewRouteDialogController.INewRouteDialogView newRouteDialogView = new NewRouteDialogView();
      newRouteDialogController.bind(newRouteDialogView);
      dialog.setContent(newRouteDialogView.asNode());
      dialog.getOkButton().setOnAction(event1 -> {
        dialog.close();
        EventBus.fireEvent(new AddNewRouteViewEvent(newRouteDialogController.getTabName(), event.getRouteToBeLoaded()));
      });
      dialog.show();
    });
  }
}
