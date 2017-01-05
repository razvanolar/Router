package code.router.events.clear_markers_events.clear_intermediate_markers_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 05.01.2017
 */
public interface ClearIntermediateMarkersEventHandler extends EventHandler {
  void onClearIntermediateMarkersEvent(ClearIntermediateMarkersEvent event);
}
