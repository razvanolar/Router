package code.router.events.show_hide_map_content_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 30.12.2016
 */
public interface ShowMapContentEventHandler extends EventHandler {
  void onShowMapContentEvent(ShowMapContentEvent event);
}
