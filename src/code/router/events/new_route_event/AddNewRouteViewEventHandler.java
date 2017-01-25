package code.router.events.new_route_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 29.12.2016
 */
public interface AddNewRouteViewEventHandler extends EventHandler {
  void onNewRouteEvent(AddNewRouteViewEvent event);
}
