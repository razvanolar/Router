package code.router.events.routes_events.save_route_events;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 07.01.2017
 *
 * Fired after the route name was established (@see {@link GenericSaveRouteEvent})
 */
public class SaveCurrentRouteEvent extends Event<SaveCurrentRouteEventHandler> {

  private String routeName;

  public SaveCurrentRouteEvent(String routeName) {
    this.routeName = routeName;
  }

  public static final EventType<SaveCurrentRouteEventHandler> TYPE = new EventType<>();

  public String getRouteName() {
    return routeName;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(SaveCurrentRouteEventHandler handler) {
    handler.onSaveCurrentRouteEvent(this);
  }
}
