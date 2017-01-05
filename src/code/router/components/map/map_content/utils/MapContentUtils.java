package code.router.components.map.map_content.utils;

import code.router.EventBus;
import code.router.events.markers_events.clear_markers_events.clear_all_markers_event.ClearAllMarkersEvent;
import code.router.events.markers_events.clear_markers_events.clear_all_markers_event.ClearAllMarkersEventHandler;
import code.router.events.markers_events.clear_markers_events.clear_elevation_markers_event.ClearElevationMarkersEvent;
import code.router.events.markers_events.clear_markers_events.clear_elevation_markers_event.ClearElevationMarkersEventHandler;
import code.router.events.markers_events.clear_markers_events.clear_end_marker_event.ClearEndMarkerEvent;
import code.router.events.markers_events.clear_markers_events.clear_end_marker_event.ClearEndMarkerEventHandler;
import code.router.events.markers_events.clear_markers_events.clear_intermediate_markers_event.ClearIntermediateMarkersEvent;
import code.router.events.markers_events.clear_markers_events.clear_intermediate_markers_event.ClearIntermediateMarkersEventHandler;
import code.router.events.markers_events.clear_markers_events.clear_start_marker_event.ClearStartMarkerEvent;
import code.router.events.markers_events.clear_markers_events.clear_start_marker_event.ClearStartMarkerEventHandler;
import code.router.events.markers_events.show_elevation_marker_event.ShowElevationMarkerEvent;
import code.router.events.markers_events.show_elevation_marker_event.ShowElevationMarkerEventHandler;
import code.router.events.routes_events.find_route_event.FindRouteEvent;
import code.router.events.routes_events.find_route_event.FindRouteEventHandler;
import code.router.events.map_settings_change_event.MapSettingsChangeEvent;
import code.router.events.map_settings_change_event.MapSettingsChangeEventHandler;
import code.router.events.mask_unmask_window_event.MaskWindowEvent;
import code.router.events.mask_unmask_window_event.UnmaskWindowEvent;
import code.router.events.routes_events.next_route_event.NextRouteEvent;
import code.router.events.routes_events.next_route_event.NextRouteEventHandler;
import code.router.events.routes_events.previous_route_event.PreviousRouteEvent;
import code.router.events.routes_events.previous_route_event.PreviousRouteEventHandler;
import code.router.events.routes_events.update_elevations_event.UpdateElevationsEvent;
import code.router.model.Elevation;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by razvanolar on 03.01.2017
 */
public class MapContentUtils {

  private WebEngine webEngine;
  private boolean domLoaded;
  private MaskWindowEvent maskWindowEvent;
  private UnmaskWindowEvent unmaskWindowEvent;

  public MapContentUtils(WebEngine webEngine) {
    this.webEngine = webEngine;
    this.maskWindowEvent = new MaskWindowEvent();
    this.unmaskWindowEvent = new UnmaskWindowEvent();
    webEngine.setOnAlert(event -> System.out.println("JS: " + event.toString()));
    JSObject window = (JSObject) webEngine.executeScript("window");
    window.setMember("util", this);
    addHandlers();
  }

  private void addHandlers() {
    EventBus.addHandler(MapSettingsChangeEvent.TYPE, (MapSettingsChangeEventHandler) event -> {
      if (!isDOMLoaded())
        return;
      String name = event.getMapSettings().getMarkerType().name();
      webEngine.executeScript("document.changeMarkerType('" + name.toLowerCase() + "');");
    });

    EventBus.addHandler(FindRouteEvent.TYPE, (FindRouteEventHandler) event -> {
      if (!isDOMLoaded())
        return;
      webEngine.executeScript("document.findRoute();");
    });

    EventBus.addHandler(PreviousRouteEvent.TYPE, (PreviousRouteEventHandler) event -> {
      if (!isDOMLoaded())
        return;
      webEngine.executeScript("document.prevRoute();");
    });

    EventBus.addHandler(NextRouteEvent.TYPE, (NextRouteEventHandler) event -> {
      if (!isDOMLoaded())
        return;
      webEngine.executeScript("document.nextRoute();");
    });

    EventBus.addHandler(ClearStartMarkerEvent.TYPE, (ClearStartMarkerEventHandler) event -> {
      webEngine.executeScript("document.clearStartMarker();");
      webEngine.executeScript("document.clearRenderer();");
    });

    EventBus.addHandler(ClearEndMarkerEvent.TYPE, (ClearEndMarkerEventHandler) event -> {
      webEngine.executeScript("document.clearEndMarker();");
      webEngine.executeScript("document.clearRenderer();");
    });

    EventBus.addHandler(ClearIntermediateMarkersEvent.TYPE, (ClearIntermediateMarkersEventHandler) event -> {
      webEngine.executeScript("document.clearIntermediateMarkers();");
    });

    EventBus.addHandler(ClearElevationMarkersEvent.TYPE, (ClearElevationMarkersEventHandler) event -> {
      webEngine.executeScript("document.clearElevationMarkers();");
    });

    EventBus.addHandler(ClearAllMarkersEvent.TYPE, (ClearAllMarkersEventHandler) event -> {
      webEngine.executeScript("document.clearAllMarkers();");
      webEngine.executeScript("document.clearRenderer();");
    });

    EventBus.addHandler(ShowElevationMarkerEvent.TYPE, (ShowElevationMarkerEventHandler) event -> {
      Elevation elevation = event.getElevation();
      double lat = elevation.getLatitude();
      double lng = elevation.getLongitude();
      int label = (int) elevation.getElevation();
      webEngine.executeScript("document.addElevationMarker(" + lat + ", " + lng + ", '" + label + "');");
    });
  }

  private boolean isDOMLoaded() {
    if (!domLoaded) {
      System.out.println("DOM is not fully loaded");
      return false;
    }
    return true;
  }

  public void mapHeightChanged(double newHeight) {
    int size = 51;
    if (newHeight > size)
      newHeight -= size;
    webEngine.executeScript("document.heightChanged(" + String.valueOf(newHeight) + ");");
  }

  public void setDomLoaded(boolean domLoaded) {
    this.domLoaded = domLoaded;
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
