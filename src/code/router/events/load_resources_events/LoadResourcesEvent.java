package code.router.events.load_resources_events;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 03.01.2017
 */
public class LoadResourcesEvent extends Event<LoadResourcesEventHandler> {

  public static final EventType<LoadResourcesEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(LoadResourcesEventHandler handler) {
    handler.onLoadResourcesEvent(this);
  }
}
