package code.router.events.routes_events.find_route_in_new_window_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 07.01.2017
 */
public interface FindRouteInNewWindowEventHandler extends EventHandler {
  void onFindRouteInNewWindowEvent(FindRouteInNewWindowEvent event);
}
