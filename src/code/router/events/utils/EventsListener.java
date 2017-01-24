package code.router.events.utils;

import code.router.EventBus;
import code.router.components.dialogs.new_route_dialog.NewRouteDialogController;
import code.router.components.dialogs.new_route_dialog.NewRouteDialogView;
import code.router.components.dialogs.save_route_dialog.SaveRouteDialogController;
import code.router.components.dialogs.save_route_dialog.SaveRouteDialogView;
import code.router.components.utils.OkCancelDialog;
import code.router.components.utils.SystemFilesView;
import code.router.events.new_route_event.AddNewRouteViewEvent;
import code.router.events.routes_events.open_route_event.OpenRouteEvent;
import code.router.events.routes_events.open_route_event.OpenRouteEventHandler;
import code.router.events.routes_events.save_route_events.SaveRouteEvent;
import code.router.events.show_new_route_dialog_event.ShowNewRouteDialogEvent;
import code.router.events.show_new_route_dialog_event.ShowNewRouteDialogEventHandler;
import code.router.events.show_save_route_dialog_event.ShowSaveRouteDialogEvent;
import code.router.events.show_save_route_dialog_event.ShowSaveRouteDialogEventHandler;
import code.router.model.routes.MapDetails;
import code.router.utils.HardCodedConstants;
import code.router.utils.validators.RouteFileValidator;

import java.io.File;

/**
 * Created by razvanolar on 06.01.2017
 */
public class EventsListener {

  public EventsListener() {
    addListeners();
  }

  private void addListeners() {
    /*
      * Show the new route dialog component.
      * Handle the information for the new route and fire the event for adding the route
     */
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

    EventBus.addHandler(ShowSaveRouteDialogEvent.TYPE, (ShowSaveRouteDialogEventHandler) event -> {
      OkCancelDialog dialog = new OkCancelDialog("Save Route", null);
      dialog.getOkButton().setDisable(true);
      SaveRouteDialogController saveRouteDialogController = new SaveRouteDialogController(dialog.getOkButton());
      SaveRouteDialogController.ISaveRouteDialogView saveRouteDialogView = new SaveRouteDialogView(
              event.getMapDetails().getName(), event.getMapDetails().getRelativeProjectPath());
      saveRouteDialogController.bind(saveRouteDialogView);
      dialog.setContent(saveRouteDialogView.asNode());
      dialog.getOkButton().setOnAction(event1 -> {
        MapDetails mapDetails = event.getMapDetails();
        mapDetails.setName(saveRouteDialogController.getRouteName());
        mapDetails.setRelativeProjectPath(saveRouteDialogController.getRoutePath());
        dialog.close();
        EventBus.fireEvent(new SaveRouteEvent(mapDetails));
      });
      dialog.show();
    });

    EventBus.addHandler(OpenRouteEvent.TYPE, (OpenRouteEventHandler) event -> {
      OkCancelDialog dialog = new OkCancelDialog("Open Route File", null);
      File rootFile = new File(HardCodedConstants.PROJECT_ROUTE_PATH);
      SystemFilesView systemFilesView = new SystemFilesView(dialog.getOkButton(), rootFile, true, null, new RouteFileValidator());
      dialog.setContent(systemFilesView.asNode());
      dialog.show();
    });
  }
}
