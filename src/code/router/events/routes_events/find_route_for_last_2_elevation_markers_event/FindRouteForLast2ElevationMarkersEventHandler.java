package code.router.events.routes_events.find_route_for_last_2_elevation_markers_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 06.01.2017
 */
public interface FindRouteForLast2ElevationMarkersEventHandler extends EventHandler {
  void onFindRouteForLast2ElevationMarkersEvent(FindRouteForLast2ElevationMarkersEvent event);
}
