package code.router.events.load_resources_events.load_map_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 03.01.2017
 */
public interface LoadMapEventHandler extends EventHandler {
  void onLoadMapEventHandler(LoadMapEvent event);
}
