package code.router.events.markers_events.clear_markers_events.clear_end_marker_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 05.01.2017
 */
public class ClearEndMarkerEvent extends Event<ClearEndMarkerEventHandler> {

  public static final EventType<ClearEndMarkerEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ClearEndMarkerEventHandler handler) {
    handler.onClearEndMarkerEvent(this);
  }
}
