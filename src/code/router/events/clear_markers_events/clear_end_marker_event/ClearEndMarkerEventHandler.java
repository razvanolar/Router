package code.router.events.clear_markers_events.clear_end_marker_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 05.01.2017
 */
public interface ClearEndMarkerEventHandler extends EventHandler {
  void onClearEndMarkerEvent(ClearEndMarkerEvent event);
}
