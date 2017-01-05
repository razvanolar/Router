package code.router.events.routes_events.next_route_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 04.01.2017
 */
public class NextRouteEvent extends Event<NextRouteEventHandler> {

  public static final EventType<NextRouteEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(NextRouteEventHandler handler) {
    handler.onNextRouteEvent(this);
  }
}
