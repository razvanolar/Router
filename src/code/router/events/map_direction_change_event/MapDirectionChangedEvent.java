package code.router.events.map_direction_change_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 05.01.2017
 */
public class MapDirectionChangedEvent extends Event<MapDirectionChangedEventHandler> {

  public static final EventType<MapDirectionChangedEventHandler> TYPE = new EventType<>();

  private boolean isHorizontal;

  public MapDirectionChangedEvent(boolean isHorizontal) {
    this.isHorizontal = isHorizontal;
  }

  public boolean isHorizontal() {
    return isHorizontal;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(MapDirectionChangedEventHandler handler) {
    handler.onMapDirectionChangedEvent(this);
  }
}
