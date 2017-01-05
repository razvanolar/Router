package code.router.events.markers_events.clear_markers_events.clear_all_markers_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 05.01.2017
 */
public class ClearAllMarkersEvent extends Event<ClearAllMarkersEventHandler> {

  public static final EventType<ClearAllMarkersEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ClearAllMarkersEventHandler handler) {
    handler.onClearAllMarkersEvent(this);
  }
}
