package code.router.model.routes;

/**
 * Created by razvanolar on 06.01.2017
 */
public class LatLng {

  private double latitude;
  private double longitude;

  public LatLng(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }
}
