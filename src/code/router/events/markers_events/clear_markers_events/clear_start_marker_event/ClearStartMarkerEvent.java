package code.router.events.markers_events.clear_markers_events.clear_start_marker_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 05.01.2017
 */
public class ClearStartMarkerEvent extends Event<ClearStartMarkerEventHandler> {

  public static final EventType<ClearStartMarkerEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ClearStartMarkerEventHandler handler) {
    handler.onClearStartMarkerEvent(this);
  }
}
