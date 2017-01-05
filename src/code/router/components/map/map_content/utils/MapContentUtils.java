package code.router.components.map.map_content.utils;

import code.router.EventBus;
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
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

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
      double[] numbers = new double[split.length];
      for (int i = 0; i < split.length; i ++) {
        numbers[i] = Double.parseDouble(split[i]);
      }
      EventBus.fireEvent(new UpdateElevationsEvent(numbers));
    } catch (Exception e) {
      System.out.println("Unable to split elevations response");
    }
  }
}
