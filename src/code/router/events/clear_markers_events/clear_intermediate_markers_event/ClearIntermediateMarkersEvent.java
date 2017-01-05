package code.router.events.clear_markers_events.clear_intermediate_markers_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 05.01.2017
 */
public class ClearIntermediateMarkersEvent extends Event<ClearIntermediateMarkersEventHandler> {

  public static final EventType<ClearIntermediateMarkersEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ClearIntermediateMarkersEventHandler handler) {
    handler.onClearIntermediateMarkersEvent(this);
  }
}
