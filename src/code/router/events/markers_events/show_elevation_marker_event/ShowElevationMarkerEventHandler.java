package code.router.events.markers_events.show_elevation_marker_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 05.01.2017
 */
public interface ShowElevationMarkerEventHandler extends EventHandler {
  void onShowElevationMarkerEvent(ShowElevationMarkerEvent event);
}
