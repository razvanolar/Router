package code.router.events.markers_events.clear_markers_events.clear_elevation_markers_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 05.01.2017
 */
public class ClearElevationMarkersEvent extends Event<ClearElevationMarkersEventHandler> {

  public static final EventType<ClearElevationMarkersEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ClearElevationMarkersEventHandler handler) {
    handler.onClearElevationMarkersEvent(this);
  }
}
