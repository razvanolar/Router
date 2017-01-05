package code.router.model;

/**
 * Created by razvanolar on 05.01.2017
 */
public class Elevation {

  private double elevation;
  private double latitude;
  private double longitude;

  public Elevation(double elevation, double latitude, double longitude) {
    this.elevation = elevation;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getElevation() {
    return elevation;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }
}
