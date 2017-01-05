package code.router.events.routes_events.update_elevations_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 04.01.2017
 */
public class UpdateElevationsEvent extends Event<UpdateElevationsEventHandler> {

  public static final EventType<UpdateElevationsEventHandler> TYPE = new EventType<>();

  private double[] elevations;

  public UpdateElevationsEvent(double[] elevations) {
    this.elevations = elevations;
  }

  public double[] getElevations() {
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
