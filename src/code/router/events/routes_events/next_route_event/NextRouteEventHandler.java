package code.router.events.routes_events.next_route_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 04.01.2017
 */
public interface NextRouteEventHandler extends EventHandler {
  void onNextRouteEvent(NextRouteEvent event);
}
