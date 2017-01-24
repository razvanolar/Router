package code.router.repository;

import code.router.EventBus;
import code.router.events.notification_event.NotificationEvent;
import code.router.events.routes_events.save_route_events.SaveRouteEvent;
import code.router.events.routes_events.save_route_events.SaveRouteEventHandler;
import code.router.model.routes.MapDetails;
import code.router.repository.xml_handlers.RouteXMLConverter;
import code.router.utils.FileUtils;
import code.router.utils.types.NotificationLevelTypes;
import javafx.application.Platform;

import java.io.File;
import java.io.PrintWriter;

/**
 * Created by razvanolar on 08.01.2017
 */
public class Repository {

  public Repository() {
    initHandlers();
  }

  private void initHandlers() {
    EventBus.addHandler(SaveRouteEvent.TYPE, (SaveRouteEventHandler) event -> {
      Thread thread = new Thread(() -> {
        MapDetails mapDetails = event.getMapDetails();
        NotificationEvent notificationEvent;
        try {
          saveRoute(mapDetails);
          notificationEvent = new NotificationEvent(mapDetails.getName() + " route was saved successfully", NotificationLevelTypes.INFO);
        } catch (Exception e) {
          e.printStackTrace();
          notificationEvent = new NotificationEvent("Error while saving route " + mapDetails.getName(), NotificationLevelTypes.ERROR);
        }

        NotificationEvent finalNotificationEvent = notificationEvent;
        Platform.runLater(() -> EventBus.fireEvent(finalNotificationEvent));
      });
      thread.run();
    });
  }

  private void saveRoute(MapDetails mapDetails) throws Exception {
    String routeDir = createPathFromString(mapDetails.getParentDirPath());
    File file = new File(FileUtils.path(routeDir) + FileUtils.routeName(mapDetails.getName()));
    String xmlResult = RouteXMLConverter.getInstance().convertRoute(mapDetails);
    writeFile(file, xmlResult);
  }

  private void writeFile(File file, String content) throws Exception {
    PrintWriter writer = new PrintWriter(file);
    writer.print(content);
    writer.close();
  }

  private String createPathFromString(String value) {
    if (value != null) {
      value = value.endsWith("\\") ? value : value + "\\";
    }
    return value;
  }
}
