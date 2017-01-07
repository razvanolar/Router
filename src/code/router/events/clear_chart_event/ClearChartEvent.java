package code.router.events.clear_chart_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 06.01.2017
 */
public class ClearChartEvent extends Event<ClearChartEventHandler> {

  public static final EventType<ClearChartEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ClearChartEventHandler handler) {
    handler.onClearChartEvent(this);
  }
}
