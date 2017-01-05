package code.router.events.show_hide_chart_content_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 30.12.2016
 */
public class ShowChartContentEvent extends Event<ShowChartContentEventHandler> {

  public static final EventType<ShowChartContentEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ShowChartContentEventHandler handler) {
    handler.onShowChartContentEvent(this);
  }
}
