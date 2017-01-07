package code.router.events.routes_events.save_route_events;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 07.01.2017
 *
 * Fired when save button is triggered in order for the handler to establish the route name (tab name)
 */
public class GenericSaveRouteEvent extends Event<GenericSaveRouteEventHandler> {

  public static final EventType<GenericSaveRouteEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(GenericSaveRouteEventHandler handler) {
    handler.onGenericSaveRouteEvent(this);
  }
}
