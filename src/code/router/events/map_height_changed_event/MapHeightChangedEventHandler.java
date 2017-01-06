package code.router.events.map_height_changed_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 06.01.2017
 */
public interface MapHeightChangedEventHandler extends EventHandler {
  void onMapHeightChangedEvent(MapHeightChangedEvent event);
}
