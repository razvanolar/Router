package code.router.events.markers_events.clear_markers_events.clear_start_marker_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 05.01.2017
 */
public interface ClearStartMarkerEventHandler extends EventHandler {
  void onClearStartMarkerEvent(ClearStartMarkerEvent event);
}
