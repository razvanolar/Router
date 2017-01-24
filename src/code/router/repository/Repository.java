package code.router.repository;

import code.router.EventBus;
import code.router.events.mask_unmask_window_event.MaskWindowEvent;
import code.router.events.mask_unmask_window_event.UnmaskWindowEvent;
import code.router.events.routes_events.save_route_events.SaveRouteEvent;
import code.router.events.routes_events.save_route_events.SaveRouteEventHandler;
import code.router.model.routes.MapDetails;
import code.router.repository.xml_handlers.RouteXMLConverter;

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
      MapDetails mapDetails = event.getMapDetails();
      EventBus.fireEvent(new MaskWindowEvent("Saving " + mapDetails.getName() + "..."));
      try {
        saveRoute(mapDetails);
      } catch (Exception e) {
        e.printStackTrace();
      }
      EventBus.fireEvent(new UnmaskWindowEvent());
    });
  }

  private void saveRoute(MapDetails mapDetails) throws Exception {
    String routeDir = createPathFromString(mapDetails.getRelativeProjectPath());
    File file = new File(routeDir + mapDetails.getName());
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
