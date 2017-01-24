package code.router.events.utils;

import code.router.EventBus;
import code.router.components.dialogs.new_route_dialog.NewRouteDialogView;
import code.router.components.dialogs.save_route_dialog.SaveRouteDialogView;
import code.router.components.utils.JFXOkCancelDialog;
import code.router.components.utils.OkCancelDialog;
import code.router.components.utils.SystemFilesView;
import code.router.events.new_route_event.AddNewRouteViewEvent;
import code.router.events.routes_events.open_route_event.ShowOpenRouteDialogEvent;
import code.router.events.routes_events.open_route_event.ShowOpenRouteDialogEventHandler;
import code.router.events.routes_events.save_route_events.SaveRouteEvent;
import code.router.events.show_dialog_event.ShowDialogEvent;
import code.router.events.show_new_route_dialog_event.ShowNewRouteDialogEvent;
import code.router.events.show_new_route_dialog_event.ShowNewRouteDialogEventHandler;
import code.router.events.show_save_route_dialog_event.ShowSaveRouteDialogEvent;
import code.router.events.show_save_route_dialog_event.ShowSaveRouteDialogEventHandler;
import code.router.model.other.SaveRouteDialogEntity;
import code.router.model.other.StringEntity;
import code.router.model.routes.MapDetails;
import code.router.utils.DialogController;
import code.router.utils.HardCodedConstants;
import code.router.utils.View;
import code.router.utils.callbacks.DialogCallback;
import code.router.utils.factories.DialogControllerFactory;
import code.router.utils.types.DialogComponentTypes;
import code.router.utils.validators.RouteFileValidator;
import com.jfoenix.controls.JFXDialog;

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
      JFXDialog dialog = new JFXDialog();
      DialogCallback callback = entity -> {
        if (entity != null && entity instanceof StringEntity) {
          dialog.close();
          EventBus.fireEvent(new AddNewRouteViewEvent(((StringEntity) entity).getValue(), event.getRouteToBeLoaded()));
        }
      };

      dialog.setContent(initDialog(dialog, callback, "Create New Route", "Create",
              DialogComponentTypes.NEW_ROUTE_DIALOG, 150, new NewRouteDialogView()));
      EventBus.fireEvent(new ShowDialogEvent(dialog));
    });

    EventBus.addHandler(ShowSaveRouteDialogEvent.TYPE, (ShowSaveRouteDialogEventHandler) event -> {
      JFXDialog dialog = new JFXDialog();
      DialogCallback callback = entity -> {
        if (entity != null && entity instanceof SaveRouteDialogEntity) {
          SaveRouteDialogEntity routeEntity = (SaveRouteDialogEntity) entity;
          MapDetails mapDetails = event.getMapDetails();
          mapDetails.setName(routeEntity.getRouteName());
          mapDetails.setParentDirPath(routeEntity.getRoutePath());
          dialog.close();
          EventBus.fireEvent(new SaveRouteEvent(mapDetails));
        }
      };

      dialog.setContent(initDialog(dialog, callback, "Save Route", "Save", DialogComponentTypes.SAVE_ROUTE_DIALOG,
              -1, new SaveRouteDialogView(event.getMapDetails().getName(), event.getMapDetails().getParentDirPath())));
      EventBus.fireEvent(new ShowDialogEvent(dialog));
    });

    EventBus.addHandler(ShowOpenRouteDialogEvent.TYPE, (ShowOpenRouteDialogEventHandler) event -> {
      OkCancelDialog dialog = new OkCancelDialog("Open Route File", null);
      File rootFile = new File(HardCodedConstants.PROJECT_DEFAULT_PATH);
      SystemFilesView systemFilesView = new SystemFilesView(dialog.getOkButton(), rootFile, true, HardCodedConstants.PROJECT_ROUTES_PATH, new RouteFileValidator());
      dialog.setContent(systemFilesView.asNode());
      dialog.show();
    });
  }

  @SuppressWarnings("unchecked")
  private JFXOkCancelDialog initDialog(JFXDialog dialog, DialogCallback callback, String title, String actionName, DialogComponentTypes type, int width, View view) {
    JFXOkCancelDialog dialogView = new JFXOkCancelDialog(title, actionName, dialog);
    DialogController controller = DialogControllerFactory.createDialogController(type, dialogView.getOkButton(), callback);
    if (controller != null) {
      controller.bind(view);
      dialogView.setBody(controller.getView().asNode());
    }
    if (width > 0)
      dialogView.setPrefWidth(width);
    return dialogView;
  }
}
