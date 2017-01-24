package code.router.events.new_route_event;

import code.router.model.routes.Route;
import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 29.12.2016
 */
public class AddNewRouteViewEvent extends Event<NewRouteViewEventHandler> {

  private String title;
  private Route routeToBeLoaded;

  public AddNewRouteViewEvent(String title) {
    this.title = title;
  }

  public AddNewRouteViewEvent(String title, Route routeToBeLoaded) {
    this.title = title;
    this.routeToBeLoaded = routeToBeLoaded;
  }

  public static EventType<NewRouteViewEventHandler> TYPE = new EventType<>();

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
  public void dispatch(NewRouteViewEventHandler handler) {
    handler.onNewRouteEvent(this);
  }
}
