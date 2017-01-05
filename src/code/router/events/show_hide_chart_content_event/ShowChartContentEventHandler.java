package code.router.events.show_hide_chart_content_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 30.12.2016
 */
public interface ShowChartContentEventHandler extends EventHandler {
  void onShowChartContentEvent(ShowChartContentEvent event);
}
