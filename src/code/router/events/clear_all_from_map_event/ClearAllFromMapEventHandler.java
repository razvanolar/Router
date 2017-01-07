package code.router.events.clear_all_from_map_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 06.01.2017
 */
public interface ClearAllFromMapEventHandler extends EventHandler {
  void onClearAllFromMapEvent(ClearAllFromMapEvent event);
}
