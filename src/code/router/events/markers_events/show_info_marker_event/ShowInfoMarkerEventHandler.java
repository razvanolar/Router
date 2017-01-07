package code.router.events.markers_events.show_info_marker_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 07.01.2017
 */
public interface ShowInfoMarkerEventHandler extends EventHandler {
  void onShowInfoMarkerEvent(ShowInfoMarkerEvent event);
}
