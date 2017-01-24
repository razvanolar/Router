package code.router.repository.xml_handlers;

import code.router.model.routes.LatLng;
import code.router.model.routes.MapDetails;
import code.router.model.routes.Marker;
import code.router.repository.SystemParameters;
import code.router.utils.validators.StringValidator;

import java.util.List;

/**
 * Created by razvanolar on 08.01.2017
 */
public class RouteXMLConverter {

  private static RouteXMLConverter INSTANCE;

  private RouteXMLConverter() {
  }

  public String convertRoute(MapDetails map) throws Exception {
    if (map == null)
      throw new Exception("RouteXMLConverter - convertRoute - Map instance is NULL");

    LatLng mapCenter = map.getMapCenter();
    Marker startMarker = map.getStartMarker();
    Marker endMarker = map.getEndMarker();

    StringBuilder builder = new StringBuilder();
    builder.append(SystemParameters.XML_HEADER).append("\n\n");
    builder.append("<map>\n");

    if (mapCenter != null) {
      builder.append("\t<center latitude=\"").append(mapCenter.getLatitude()).append("\" longitude=\"").
              append(mapCenter.getLongitude()).append("\" />\n");
    }

    builder.append("\t<zoom value=\"").append(map.getZoomValue()).append("\" />\n");

    addMarker("\t", builder, startMarker);
    addMarker("\t", builder, endMarker);
    addMarkers("\t", builder, map.getIntermediateMarkers());
    addMarkers("\t", builder, map.getElevationMarkers());

    builder.append("</map>");
    return builder.toString();
  }

  private void addMarker(String tabs, StringBuilder builder, Marker marker) {
    if (marker != null) {
      builder.append(tabs).append("<marker type=\"").append(marker.getType().name()).append("\" latitude=\"").
              append(marker.getPosition().getLatitude()).append("\" longitude=\"").
              append(marker.getPosition().getLongitude()).append("\" ");
      if (!StringValidator.isNullOrEmpty(marker.getLabel()))
        builder.append("label=\"").append(marker.getLabel()).append("\" ");
      builder.append("/>\n");
    }
  }

  private void addMarkers(String tabs, StringBuilder builder, List<Marker> markers) {
    if (markers != null && !markers.isEmpty()) {
      builder.append(tabs).append("<markers>\n");
      String newTabs = "\t" + tabs;
      for (Marker marker : markers) {
        addMarker(newTabs, builder, marker);
      }
      builder.append(tabs).append("</markers>\n");
    }
  }

  public static RouteXMLConverter getInstance() {
    if (INSTANCE == null)
      INSTANCE = new RouteXMLConverter();
    return INSTANCE;
  }
}
