package code.router.events.show_routes_tree_events;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 29.12.2016
 */
public interface ShowRouteTreeEventHandler extends EventHandler {
  void onShowRouteTreeEvent(ShowRouteTreeEvent event);
}
