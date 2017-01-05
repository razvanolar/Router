package code.router.events.show_hide_chart_content_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 30.12.2016
 */
public interface HideChartContentEventHandler extends EventHandler {
  void onHideChartContentEvent(HideChartContentEvent event);
}
