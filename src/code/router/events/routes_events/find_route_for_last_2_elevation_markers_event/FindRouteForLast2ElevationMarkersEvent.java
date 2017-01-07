package code.router.events.routes_events.find_route_for_last_2_elevation_markers_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 06.01.2017
 */
public class FindRouteForLast2ElevationMarkersEvent extends Event<FindRouteForLast2ElevationMarkersEventHandler> {

  private boolean inNewWindow;

  public FindRouteForLast2ElevationMarkersEvent() {
  }

  public FindRouteForLast2ElevationMarkersEvent(boolean inNewWindow) {
    this.inNewWindow = inNewWindow;
  }

  public static final EventType<FindRouteForLast2ElevationMarkersEventHandler> TYPE = new EventType<>();

  public boolean isInNewWindow() {
    return inNewWindow;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(FindRouteForLast2ElevationMarkersEventHandler handler) {
    handler.onFindRouteForLast2ElevationMarkersEvent(this);
  }
}
