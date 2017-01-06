package code.router.events.new_route_event;

import code.router.utils.Component;
import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 29.12.2016
 */
public class AddNewRouteViewEvent extends Event<NewRouteViewEventHandler> {

  private String title;
  private Component mapComponent;

  public AddNewRouteViewEvent(String title, Component mapComponent) {
    this.title = title;
    this.mapComponent = mapComponent;
  }

  public static EventType<NewRouteViewEventHandler> TYPE = new EventType<>();

  public String getTitle() {
    return title;
  }

  public Component getMapComponent() {
    return mapComponent;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(NewRouteViewEventHandler handler) {
    handler.onNewRouteEvent(this);
  }
}
