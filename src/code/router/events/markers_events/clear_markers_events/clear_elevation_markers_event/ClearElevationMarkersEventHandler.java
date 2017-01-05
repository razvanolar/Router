package code.router.events.markers_events.clear_markers_events.clear_elevation_markers_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 05.01.2017
 */
public interface ClearElevationMarkersEventHandler extends EventHandler {
  void onClearElevationMarkersEvent(ClearElevationMarkersEvent event);
}
