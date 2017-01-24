package code.router.events.routes_events.update_elevations_event;

import code.router.model.routes.Elevation;
import code.router.utils.event.Event;
import code.router.utils.event.EventType;

import java.util.List;

/**
 * Created by razvanolar on 04.01.2017
 */
public class UpdateElevationsEvent extends Event<UpdateElevationsEventHandler> {

  public static final EventType<UpdateElevationsEventHandler> TYPE = new EventType<>();

  private List<Elevation> elevations;

  public UpdateElevationsEvent(List<Elevation> elevations) {
    this.elevations = elevations;
  }

  public List<Elevation> getElevations() {
    return elevations;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(UpdateElevationsEventHandler handler) {
    handler.onUpdateElevationsEvent(this);
  }
}
