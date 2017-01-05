package code.router.events.load_resources_events.load_map_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 03.01.2017
 */
public class LoadMapEvent extends Event<LoadMapEventHandler> {

  public static final EventType<LoadMapEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(LoadMapEventHandler handler) {
    handler.onLoadMapEventHandler(this);
  }
}
