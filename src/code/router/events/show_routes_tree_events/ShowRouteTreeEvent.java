package code.router.events.show_routes_tree_events;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ShowRouteTreeEvent extends Event<ShowRouteTreeEventHandler> {

  public static final EventType<ShowRouteTreeEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ShowRouteTreeEventHandler handler) {
    handler.onShowRouteTreeEvent(this);
  }
}
