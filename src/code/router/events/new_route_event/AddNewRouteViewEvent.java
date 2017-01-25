package code.router.events.new_route_event;

import code.router.model.routes.Route;
import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 29.12.2016
 */
public class AddNewRouteViewEvent extends Event<AddNewRouteViewEventHandler> {

  private String title;
  private Route routeToBeLoaded;

  public AddNewRouteViewEvent(String title) {
    this.title = title;
  }

  public AddNewRouteViewEvent(String title, Route routeToBeLoaded) {
    this.title = title;
    this.routeToBeLoaded = routeToBeLoaded;
  }

  public static EventType<AddNewRouteViewEventHandler> TYPE = new EventType<>();

  public String getTitle() {
    return title;
  }

  public Route getRouteToBeLoaded() {
    return routeToBeLoaded;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(AddNewRouteViewEventHandler handler) {
    handler.onNewRouteEvent(this);
  }
}
