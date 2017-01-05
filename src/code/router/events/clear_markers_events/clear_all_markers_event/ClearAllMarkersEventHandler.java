package code.router.events.clear_markers_events.clear_all_markers_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 05.01.2017
 */
public interface ClearAllMarkersEventHandler extends EventHandler {
  void onClearAllMarkersEvent(ClearAllMarkersEvent event);
}
