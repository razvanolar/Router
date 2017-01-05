package code.router.events.show_hide_map_settings_content_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 04.01.2017
 */
public class HideMapSettingsEvent extends Event<HideMapSettingsEventHandler> {

  public static final EventType<HideMapSettingsEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(HideMapSettingsEventHandler handler) {
    handler.onHideMapSettingsEvent(this);
  }
}
