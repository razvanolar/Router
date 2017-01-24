package code.router.model.routes;

import java.util.List;

/**
 * Created by razvanolar on 07.01.2017
 */
public class MapDetails {

  private String name;
  private String parentDirPath;
  private Marker startMarker;
  private Marker endMarker;
  private LatLng mapCenter;
  private double zoomValue;
  private List<Marker> intermediateMarkers;
  private List<Marker> elevationMarkers;

  public MapDetails(Marker startMarker, Marker endMarker, LatLng mapCenter, double zoomValue,
                    List<Marker> intermediateMarkers, List<Marker> elevationMarkers) {
    this.startMarker = startMarker;
    this.endMarker = endMarker;
    this.mapCenter = mapCenter;
    this.zoomValue = zoomValue;
    this.intermediateMarkers = intermediateMarkers;
    this.elevationMarkers = elevationMarkers;
  }

  public String getName() {
    return name;
  }

  public String getParentDirPath() {
    return parentDirPath;
  }

  public Marker getStartMarker() {
    return startMarker;
  }

  public Marker getEndMarker() {
    return endMarker;
  }

  public LatLng getMapCenter() {
    return mapCenter;
  }

  public double getZoomValue() {
    return zoomValue;
  }

  public List<Marker> getIntermediateMarkers() {
    return intermediateMarkers;
  }

  public List<Marker> getElevationMarkers() {
    return elevationMarkers;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setParentDirPath(String parentDirPath) {
    this.parentDirPath = parentDirPath;
  }
}
