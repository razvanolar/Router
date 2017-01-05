package code.router.events.load_resources_events;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 03.01.2017
 */
public interface LoadResourcesEventHandler extends EventHandler {
  void onLoadResourcesEvent(LoadResourcesEvent event);
}
