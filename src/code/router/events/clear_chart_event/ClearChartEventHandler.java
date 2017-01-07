package code.router.events.clear_chart_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 06.01.2017
 */
public interface ClearChartEventHandler extends EventHandler {
  void onClearChartEvent(ClearChartEvent event);
}
