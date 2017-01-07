package code.router.events.routes_events.save_route_events;

import code.router.model.MapDetails;
import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 07.01.2017
 */
public class SaveRouteEvent extends Event<SaveRouteEventHandler> {

  private MapDetails mapDetails;

  public SaveRouteEvent(MapDetails mapDetails) {
    this.mapDetails = mapDetails;
  }

  public static final EventType<SaveRouteEventHandler> TYPE = new EventType<>();

  public MapDetails getMapDetails() {
    return mapDetails;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(SaveRouteEventHandler handler) {
    handler.onSaveRouteEvent(this);
  }
}
