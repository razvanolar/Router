package code.router.events.show_new_route_dialog_event;

import code.router.model.Route;
import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 06.01.2017
 */
public class ShowNewRouteDialogEvent extends Event<ShowNewRouteDialogEventHandler> {

  public Route routeToBeLoaded;

  public ShowNewRouteDialogEvent() {
  }

  public ShowNewRouteDialogEvent(Route routeToBeLoaded) {
    this.routeToBeLoaded = routeToBeLoaded;
  }

  public static final EventType<ShowNewRouteDialogEventHandler> TYPE = new EventType<ShowNewRouteDialogEventHandler>();

  public Route getRouteToBeLoaded() {
    return routeToBeLoaded;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ShowNewRouteDialogEventHandler handler) {
    handler.onShowNewRouteDialogEvent(this);
  }
}
