package code.router.events.routes_events.update_elevations_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 04.01.2017
 */
public interface UpdateElevationsEventHandler extends EventHandler {
  void onUpdateElevationsEvent(UpdateElevationsEvent event);
}
