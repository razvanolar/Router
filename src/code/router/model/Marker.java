package code.router.model;

import code.router.utils.types.MarkerTypes;

/**
 * Created by razvanolar on 07.01.2017
 */
public class Marker {

  private LatLng position;
  private MarkerTypes type;

  public Marker(LatLng position, MarkerTypes type) {
    this.position = position;
    this.type = type;
  }

  public LatLng getPosition() {
    return position;
  }

  public MarkerTypes getType() {
    return type;
  }
}
