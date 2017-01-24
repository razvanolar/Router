package code.router.model.routes;

import code.router.utils.types.MarkerTypes;

/**
 * Created by razvanolar on 07.01.2017
 */
public class Marker {

  private LatLng position;
  private MarkerTypes type;
  private String label;

  public Marker(LatLng position, MarkerTypes type) {
    this.position = position;
    this.type = type;
  }

  public Marker(LatLng position, MarkerTypes type, String label) {
    this(position, type);
    this.label = label;
  }

  public LatLng getPosition() {
    return position;
  }

  public MarkerTypes getType() {
    return type;
  }

  public String getLabel() {
    return label;
  }
}
