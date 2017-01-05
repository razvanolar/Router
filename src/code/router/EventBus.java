package code.router;

import code.router.utils.event.Event;
import code.router.utils.event.EventHandler;
import code.router.utils.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by razvanolar on 29.12.2016
 */
public class EventBus {

  private static Map<EventType<EventHandler>, List<EventHandler>> eventHandlersMap = new HashMap<>();

  @SuppressWarnings("unchecked")
  public static void addHandler(EventType eventType, EventHandler eventHandler) {
    List<EventHandler> handlers = eventHandlersMap.get(eventType);
    if (handlers == null) {
      handlers = new ArrayList<>();
      handlers.add(eventHandler);
      eventHandlersMap.put(eventType, handlers);
    } else {
      if (!handlers.contains(eventHandler))
        handlers.add(eventHandler);
    }
  }

  @SuppressWarnings("unchecked")
  public static void fireEvent(Event event) {
    EventType eventType = event.getAssociatedType();
    List<EventHandler> handlers = eventHandlersMap.get(eventType);
    if (handlers != null) {
      handlers.forEach(event::dispatch);
    } else {
      System.out.println("No handler found for " + event.getClass().getName());
    }
  }
}
