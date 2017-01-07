package code.router.events.show_save_route_dialog_event;

import code.router.model.MapDetails;
import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 07.01.2017
 */
public class ShowSaveRouteDialogEvent extends Event<ShowSaveRouteDialogEventHandler> {

  private MapDetails mapDetails;

  public ShowSaveRouteDialogEvent(MapDetails mapDetails) {
    this.mapDetails = mapDetails;
  }

  public static final EventType<ShowSaveRouteDialogEventHandler> TYPE = new EventType<>();

  public MapDetails getMapDetails() {
    return mapDetails;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ShowSaveRouteDialogEventHandler handler) {
    handler.onShowSaveRouteDialogEvent(this);
  }
}
