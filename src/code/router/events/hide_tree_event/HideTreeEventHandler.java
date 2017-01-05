package code.router.events.hide_tree_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 29.12.2016
 */
public interface HideTreeEventHandler extends EventHandler {
  void onHideRouteTreeEvent(HideTreeEvent event);
}
