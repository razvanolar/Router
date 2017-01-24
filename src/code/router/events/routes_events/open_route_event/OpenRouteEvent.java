package code.router.events.routes_events.open_route_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 24.01.2017
 */
public class OpenRouteEvent extends Event<OpenRouteEventHandler> {

  public static final EventType<OpenRouteEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(OpenRouteEventHandler handler) {
    handler.onOpenRouteEvent(this);
  }
}
