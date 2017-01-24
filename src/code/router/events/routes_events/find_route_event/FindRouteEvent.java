package code.router.events.routes_events.find_route_event;

import code.router.model.routes.Route;
import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 04.01.2017
 */
public class FindRouteEvent extends Event<FindRouteEventHandler> {

  public Route route;

  public FindRouteEvent() {
  }

  public FindRouteEvent(Route route) {
    this.route = route;
  }

  public static final EventType<FindRouteEventHandler> TYPE = new EventType<>();

  public Route getRoute() {
    return route;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(FindRouteEventHandler handler) {
    handler.onFindRouteEvent(this);
  }
}
