package code.router.events.mask_unmask_window_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 03.01.2017
 */
public interface MaskWindowEventHandler extends EventHandler {
  void onMaskWindowEvent(MaskWindowEvent event);
}
