package code.router.events.markers_events.show_elevation_marker_event;

import code.router.model.Elevation;
import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 05.01.2017
 */
public class ShowElevationMarkerEvent extends Event<ShowElevationMarkerEventHandler> {

  public static final EventType<ShowElevationMarkerEventHandler> TYPE = new EventType<>();

  private Elevation elevation;

  public ShowElevationMarkerEvent(Elevation elevation) {
    this.elevation = elevation;
  }

  public Elevation getElevation() {
    return elevation;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ShowElevationMarkerEventHandler handler) {
    handler.onShowElevationMarkerEvent(this);
  }
}
