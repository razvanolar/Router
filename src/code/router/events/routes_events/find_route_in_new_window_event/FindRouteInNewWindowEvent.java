package code.router.events.routes_events.find_route_in_new_window_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 07.01.2017
 */
public class FindRouteInNewWindowEvent extends Event<FindRouteInNewWindowEventHandler> {

  public static final EventType<FindRouteInNewWindowEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(FindRouteInNewWindowEventHandler handler) {
    handler.onFindRouteInNewWindowEvent(this);
  }
}
