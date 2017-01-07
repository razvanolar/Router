package code.router.model;

/**
 * Created by razvanolar on 05.01.2017
 */
public class Elevation {

  private double elevation;
  private LatLng latLng;

  public Elevation(double elevation, double latitude, double longitude) {
    this.elevation = elevation;
    this.latLng = new LatLng(latitude, longitude);
  }

  public double getElevation() {
    return elevation;
  }

  public double getLatitude() {
    return latLng.getLatitude();
  }

  public double getLongitude() {
    return latLng.getLongitude();
  }
}
