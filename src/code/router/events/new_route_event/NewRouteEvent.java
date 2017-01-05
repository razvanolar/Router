package code.router.events.new_route_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 29.12.2016
 */
public class NewRouteEvent extends Event<NewRouteEventHandler> {

  public static EventType<NewRouteEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(NewRouteEventHandler handler) {
    handler.onNewRouteEvent(this);
  }
}
