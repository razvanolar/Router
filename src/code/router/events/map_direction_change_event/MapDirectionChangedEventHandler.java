package code.router.events.map_direction_change_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 05.01.2017
 */
public interface MapDirectionChangedEventHandler extends EventHandler {
  void onMapDirectionChangedEvent(MapDirectionChangedEvent event);
}
