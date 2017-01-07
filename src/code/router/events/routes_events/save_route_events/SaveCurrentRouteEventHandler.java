package code.router.events.routes_events.save_route_events;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 07.01.2017
 */
public interface SaveCurrentRouteEventHandler extends EventHandler {
  void onSaveCurrentRouteEvent(SaveCurrentRouteEvent event);
}
