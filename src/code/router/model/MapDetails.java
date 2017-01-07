package code.router.model;

import java.util.List;

/**
 * Created by razvanolar on 07.01.2017
 */
public class MapDetails {

  private String name;
  private String relativeProjectPath;
  private Marker startMarker;
  private Marker endMarker;
  private List<Marker> intermediateMarkers;
  private List<Marker> elevationMarkers;
  private Route route;

  public MapDetails(Marker startMarker, Marker endMarker,
                    List<Marker> intermediateMarkers, List<Marker> elevationMarkers, Route route) {
    this.startMarker = startMarker;
    this.endMarker = endMarker;
    this.intermediateMarkers = intermediateMarkers;
    this.elevationMarkers = elevationMarkers;
    this.route = route;
  }

  public String getName() {
    return name;
  }

  public String getRelativeProjectPath() {
    return relativeProjectPath;
  }

  public Route getRoute() {
    return route;
  }

  public Marker getStartMarker() {
    return startMarker;
  }

  public Marker getEndMarker() {
    return endMarker;
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

  public void setRelativeProjectPath(String relativeProjectPath) {
    this.relativeProjectPath = relativeProjectPath;
  }
}
