package code.router.events.show_dialog_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 24.01.2017
 */
public interface ShowDialogEventHandler extends EventHandler {
  void onShowDialogEvent(ShowDialogEvent event);
}
