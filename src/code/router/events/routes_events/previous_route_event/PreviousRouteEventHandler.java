package code.router.events.routes_events.previous_route_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 04.01.2017
 */
public interface PreviousRouteEventHandler extends EventHandler {
  void onPreviousRouteEvent(PreviousRouteEvent event);
}
