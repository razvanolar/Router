package code.router.components.map.map_content.utils;

import code.router.EventBus;
import code.router.events.clear_chart_event.ClearChartEvent;
import code.router.events.map_height_changed_event.MapHeightChangedEvent;
import code.router.events.map_height_changed_event.MapHeightChangedEventHandler;
import code.router.events.markers_events.show_elevation_marker_event.ShowElevationMarkerEvent;
import code.router.events.routes_events.find_route_event.FindRouteEvent;
import code.router.events.map_settings_change_event.MapSettingsChangeEvent;
import code.router.events.map_settings_change_event.MapSettingsChangeEventHandler;
import code.router.events.mask_unmask_window_event.MaskWindowEvent;
import code.router.events.mask_unmask_window_event.UnmaskWindowEvent;
import code.router.events.routes_events.find_route_for_last_2_elevation_markers_event.FindRouteForLast2ElevationMarkersEvent;
import code.router.events.routes_events.update_elevations_event.UpdateElevationsEvent;
import code.router.events.show_new_route_dialog_event.ShowNewRouteDialogEvent;
import code.router.model.Elevation;
import code.router.model.LatLng;
import code.router.model.Route;
import code.router.utils.event.Event;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by razvanolar on 03.01.2017
 */
public class MapContentUtils {

  private WebEngine webEngine;
  private boolean domLoaded;
  private MaskWindowEvent maskWindowEvent;
  private UnmaskWindowEvent unmaskWindowEvent;
  private Queue<Event> events;

  public MapContentUtils(WebEngine webEngine) {
    this.webEngine = webEngine;
    this.maskWindowEvent = new MaskWindowEvent();
    this.unmaskWindowEvent = new UnmaskWindowEvent();
    this.events = new LinkedList<>();
    webEngine.setOnAlert(event -> System.out.println("JS: " + event.toString()));
    JSObject window = (JSObject) webEngine.executeScript("window");
    window.setMember("util", this);
    addHandlers();
  }

  private void addHandlers() {
    EventBus.addHandler(MapHeightChangedEvent.TYPE, (MapHeightChangedEventHandler) event -> {
      mapHeightChanged(event.getHeight());
    });

    EventBus.addHandler(MapSettingsChangeEvent.TYPE, (MapSettingsChangeEventHandler) event -> {
      if (!isDOMLoaded())
        return;
      String name = event.getMapSettings().getMarkerType().name();
      webEngine.executeScript("document.changeMarkerType('" + name.toLowerCase() + "');");
    });
  }

  public void onFindRouteEvent(Route route) {
    if (!isDOMLoaded())
      return;
    if (route != null) {
      LatLng start = route.getFromPoint();
      LatLng end = route.getToPoint();
      webEngine.executeScript("document.addStartMarker(" + start.getLatitude() + ", " + start.getLongitude() + ");");
      webEngine.executeScript("document.addEndMarker(" + end.getLatitude() + ", " + end.getLongitude() + ");");
    }
    webEngine.executeScript("document.findRoute();");
  }

  public void getPreviousRoute() {
    if (!isDOMLoaded())
      return;
    webEngine.executeScript("document.prevRoute();");
  }

  public void getNextRoute() {
    if (!isDOMLoaded())
      return;
    webEngine.executeScript("document.nextRoute();");
  }

  public void clearStartMarker() {
    webEngine.executeScript("document.clearStartMarker();");
    webEngine.executeScript("document.clearRenderer();");
  }

  public void clearEndMarker() {
    webEngine.executeScript("document.clearEndMarker();");
    webEngine.executeScript("document.clearRenderer();");
  }

  public void clearIntermediateMarkers() {
    webEngine.executeScript("document.clearIntermediateMarkers();");
  }

  public void clearElevationMarkers() {
    webEngine.executeScript("document.clearElevationMarkers();");
  }

  public void clearAllMarkers() {
    webEngine.executeScript("document.clearAllMarkers();");
    webEngine.executeScript("document.clearRenderer();");
  }

  public void clearAllFromMap() {
    webEngine.executeScript("document.clearAllMarkers();");
    webEngine.executeScript("document.clearRenderer();");
    webEngine.executeScript("document.clearInfoWindow();");
    EventBus.fireEvent(new ClearChartEvent());
  }

  public void showElevationMarker(ShowElevationMarkerEvent event) {
    Elevation elevation = event.getElevation();
    double lat = elevation.getLatitude();
    double lng = elevation.getLongitude();
    int label = (int) elevation.getElevation();
    webEngine.executeScript("document.addElevationMarker(" + lat + ", " + lng + ", '" + label + "');");
  }

  public void findRouteForLastTwoElevationMarkers(FindRouteForLast2ElevationMarkersEvent event) {
    Object result = webEngine.executeScript("document.canFindRouteForLastTwoElevationMarkers();");
    if (result != null && result instanceof Boolean) {
      boolean res = (Boolean) result;
      if (res) {
        if (event.isInNewWindow()) {
          Object rez = webEngine.executeScript("document.getLastTwoElevationMarkersPositions();");
          if (rez != null && rez instanceof String) {
            String string = (String) rez;
            try {
              EventBus.fireEvent(new ShowNewRouteDialogEvent(createRouteFromString(string)));
            } catch (Exception e) {
              System.out.println("Error while parsing last two elevation markers positions. Error message: " + e.getMessage());
              e.printStackTrace();
            }
          } else {
            System.out.println("Unable to retrieve last two elevation markers positions");
          }
        } else {
          webEngine.executeScript("document.clearStartMarker();document.clearEndMarker();");
          webEngine.executeScript("document.cloneStartMarkerFromElevationMarkers();");
          webEngine.executeScript("document.cloneEndMarkerFromElevationMarkers();");
          webEngine.executeScript("document.clearLastTwoElevationMarkers();");
          System.out.println("fire FindRouteEvent in same window");
          EventBus.fireEvent(new FindRouteEvent());
        }
      }
    }
  }

  public Route getCurrentRoute() {
    Object obj = webEngine.executeScript("document.canFindRouteForStartEndMarkers();");
    if (obj != null && obj instanceof Boolean) {
      boolean canFindRouteForStartEndMarkers = (Boolean) obj;
      if (canFindRouteForStartEndMarkers) {
        obj = webEngine.executeScript("document.getCurrentRoutePositions()");
        if (obj != null && obj instanceof String) {
          String string = (String) obj;
          try {
            return createRouteFromString(string);
          } catch (Exception e) {
            System.out.println("Error while parsing start and end markers positions. Error message: " + e.getMessage());
            e.printStackTrace();
          }
        }
      }
    }
    return null;
  }

  public void mapHeightChanged(double newHeight) {
    if (!domLoaded) {
      events.add(new MapHeightChangedEvent(newHeight));
      return;
    }
    int size = 51;
    if (newHeight > size)
      newHeight -= size;
    webEngine.executeScript("document.heightChanged(" + String.valueOf(newHeight) + ");");
  }

  public void setDomLoaded(boolean domLoaded) {
    this.domLoaded = domLoaded;
    // consume all the events that were fired before the DOM was loaded
    if (domLoaded) {
      events.forEach(EventBus::fireEvent);
    }
  }

  private boolean isDOMLoaded() {
    if (!domLoaded) {
      System.out.println("DOM is not fully loaded");
      return false;
    }
    return true;
  }

  private Route createRouteFromString(String string) throws Exception {
    String[] split = string.split(",");
    if (split.length != 4)
      throw new Exception("Incorrect positions size.");
    double startLatitude = Double.parseDouble(split[0]);
    double startLongitude = Double.parseDouble(split[1]);
    double endLatitude = Double.parseDouble(split[2]);
    double endLongitude = Double.parseDouble(split[3]);
    return new Route(new LatLng(startLatitude, startLongitude), new LatLng(endLatitude, endLongitude));
  }

  /**
   *
   * Methods called from javascript side
   *
   *
   */

  public void addMarker(double lat, double lng, String method) {
    webEngine.executeScript("document." + method + "(" + lat + ", " + lng + ");");
  }

  public void beginServiceCall(String message) {
    maskWindowEvent.setMessage(message);
    EventBus.fireEvent(maskWindowEvent);
  }

  public void endServiceCall() {
    EventBus.fireEvent(unmaskWindowEvent);
  }

  public void updateElevationChart(String elevations) {
    try {
      elevations = elevations.substring(0, elevations.length() - 1);
      String[] split = elevations.split(",");
      List<Elevation> elevationsList = new ArrayList<>(split.length);
      for (int i = 0; i < split.length; i ++) {
        String[] elevationSplit = split[i].split("\\+");
        elevationsList.add(new Elevation(
                Double.parseDouble(elevationSplit[0]),
                Double.parseDouble(elevationSplit[1]),
                Double.parseDouble(elevationSplit[2])
        ));
      }
      EventBus.fireEvent(new UpdateElevationsEvent(elevationsList));
    } catch (Exception e) {
      System.out.println("Unable to split elevations response");
    }
  }
}
