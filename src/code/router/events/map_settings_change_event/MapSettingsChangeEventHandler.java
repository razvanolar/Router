package code.router.events.map_settings_change_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 04.01.2017
 */
public interface MapSettingsChangeEventHandler extends EventHandler {
  void onMapSettingsChangeEvent(MapSettingsChangeEvent event);
}
