package code.router.events.map_settings_change_event;

import code.router.model.MapSettings;
import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 04.01.2017
 */
public class MapSettingsChangeEvent extends Event<MapSettingsChangeEventHandler> {

  private MapSettings mapSettings;

  public MapSettingsChangeEvent(MapSettings mapSettings) {
    this.mapSettings = mapSettings;
  }

  public static final EventType<MapSettingsChangeEventHandler> TYPE = new EventType<>();

  public MapSettings getMapSettings() {
    return mapSettings;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(MapSettingsChangeEventHandler handler) {
    handler.onMapSettingsChangeEvent(this);
  }
}
