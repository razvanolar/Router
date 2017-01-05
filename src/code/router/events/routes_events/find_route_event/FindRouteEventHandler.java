package code.router.events.routes_events.find_route_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 04.01.2017
 */
public interface FindRouteEventHandler extends EventHandler {
  void onFindRouteEvent(FindRouteEvent event);
}
