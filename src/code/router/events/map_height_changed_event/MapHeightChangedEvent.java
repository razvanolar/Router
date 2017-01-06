package code.router.events.map_height_changed_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 06.01.2017
 */
public class MapHeightChangedEvent extends Event<MapHeightChangedEventHandler> {

  private double height;

  public MapHeightChangedEvent(double height) {
    this.height = height;
  }

  public static final EventType<MapHeightChangedEventHandler> TYPE = new EventType<>();

  public double getHeight() {
    return height;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(MapHeightChangedEventHandler handler) {
    handler.onMapHeightChangedEvent(this);
  }
}
