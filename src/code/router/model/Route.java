package code.router.model;

/**
 * Created by razvanolar on 06.01.2017
 */
public class Route {

  private LatLng fromPoint;
  private LatLng toPoint;

  public Route(LatLng fromPoint, LatLng toPoint) {
    this.fromPoint = fromPoint;
    this.toPoint = toPoint;
  }

  public LatLng getFromPoint() {
    return fromPoint;
  }

  public LatLng getToPoint() {
    return toPoint;
  }
}
