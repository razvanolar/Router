package code.router.events.routes_events.previous_route_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 04.01.2017
 */
public class PreviousRouteEvent extends Event<PreviousRouteEventHandler> {

  public static final EventType<PreviousRouteEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(PreviousRouteEventHandler handler) {
    handler.onPreviousRouteEvent(this);
  }
}
