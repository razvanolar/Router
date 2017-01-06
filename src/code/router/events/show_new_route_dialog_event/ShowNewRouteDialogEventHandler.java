package code.router.events.show_new_route_dialog_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 06.01.2017
 */
public interface ShowNewRouteDialogEventHandler extends EventHandler {
  void onShowNewRouteDialogEvent(ShowNewRouteDialogEvent event);
}
