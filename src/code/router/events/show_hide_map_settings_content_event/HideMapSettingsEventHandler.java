package code.router.events.show_hide_map_settings_content_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 04.01.2017
 */
public interface HideMapSettingsEventHandler extends EventHandler {
  void onHideMapSettingsEvent(HideMapSettingsEvent event);
}
