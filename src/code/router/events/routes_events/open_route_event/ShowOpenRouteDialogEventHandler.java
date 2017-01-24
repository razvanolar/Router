package code.router.events.routes_events.open_route_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 24.01.2017
 */
public interface ShowOpenRouteDialogEventHandler extends EventHandler {
  void onOpenRouteEvent(ShowOpenRouteDialogEvent event);
}
