/**
 * Created by razvanolar on 04.01.2017
 */

var START_MARKER = "start";
var END_MARKER = "end";
var INTERMEDIATE_MARKER = "intermediate";
var ELEVATION_MARKER = "elevation";
var NONE_MARKER = "none";

var startMarker = null;
var endMarker = undefined;
var intermediateMarkers = [];
var elevationMarkers = [];
var markerType = NONE_MARKER;

var startMarkerIconPath = 'start_marker.png';
var endMarkerIconPath = 'end_marker.png';

google.maps.event.addListener(map, "click", function (event) {
    // util.test(event.latLng.lat(), event.latLng.lng());
    var latLng = event.latLng;
    if (markerType === START_MARKER) {
        document.clearStartMarker();
        util.addMarker(latLng.lat(), latLng.lng(), 'addStartMarker');
    } else if (markerType === END_MARKER) {
        document.clearEndMarker();
        util.addMarker(latLng.lat(), latLng.lng(), 'addEndMarker');
    } else if (markerType === INTERMEDIATE_MARKER) {
        util.addMarker(latLng.lat(), latLng.lng(), 'addIntermediateMarker');
    } else if (markerType == ELEVATION_MARKER) {
        document.getElevationForLatLng(latLng);
    }
});

document.changeMarkerType = function (type) {
    if (type == END_MARKER || type == START_MARKER || type == INTERMEDIATE_MARKER || type == ELEVATION_MARKER || type == NONE_MARKER)
        markerType = type;
};

document.addStartMarker = function (lat, lng) {
    var latLng = {
        lat: lat,
        lng: lng
    };
    startMarker = new google.maps.Marker({
        position: latLng,
        map: map,
        title: 'Start',
        draggable: true,
        icon: {
            url: startMarkerIconPath
        }
    });
};

document.addEndMarker = function (lat, lng) {
    var latLng = {
        lat: lat,
        lng: lng
    };
    endMarker = new google.maps.Marker({
        position: latLng,
        map: map,
        title: 'End',
        draggable: true,
        icon: {
            url: endMarkerIconPath
        }
    });
};

document.addIntermediateMarker = function (lat, lng) {
    if (intermediateMarkers.length >= 8) {
        alert("Maximum number of intermediate markers was reached");
        return;
    }
    var latLng = {
        lat: lat,
        lng: lng
    };
    var marker = new google.maps.Marker({
        position: latLng,
        map: map,
        title: 'Intermediate',
        draggable: true,
        icon: {
            url: 'intermediate_marker.png'
        }
    });
    intermediateMarkers.push(marker);
};

document.addElevationMarker = function (lat, lng, label) {
    var latLng = {
        lat: lat,
        lng: lng
    };
    var marker = new google.maps.Marker({
        position: latLng,
        map: map,
        title: 'Elevation',
        draggable: false,
        icon: {
            url: 'elevation_marker.png'
        },
        label: label
    });
    elevationMarkers.push(marker);
};

document.clearStartMarker = function () {
    document.clearMarker(startMarker);
};

document.clearEndMarker = function () {
    document.clearMarker(endMarker);
};

document.clearIntermediateMarkers = function () {
    for (var i = 0; i < intermediateMarkers.length; i ++) {
        intermediateMarkers[i].setMap(null);
        intermediateMarkers[i] = null;
    }
    intermediateMarkers = [];
};

document.clearElevationMarkers = function () {
    for (var i = 0; i < elevationMarkers.length; i ++) {
        elevationMarkers[i].setMap(null);
        elevationMarkers[i] = null;
    }
    elevationMarkers = [];
};

document.clearMarker = function (marker) {
    if (marker) {
        marker.setMap(null);
        marker = null;
    }
};

document.clearAllMarkers = function () {
    document.clearStartMarker();
    document.clearEndMarker();
    document.clearIntermediateMarkers();
    document.clearElevationMarkers();
};

document.canFindRouteForLastTwoElevationMarkers = function () {
    if (!elevationMarkers || elevationMarkers.length < 2) {
        alert("There should be at least two elevation markers defined in order to complete this action");
        return false;
    }
    var length = elevationMarkers.length;
    if (!elevationMarkers[length - 1] || !elevationMarkers[length - 2]) {
        alert("There should be at least two elevation markers defined in order to complete this action");
        return false;
    }
    return true;
};

document.canFindRouteForStartEndMarkers = function () {
    if (!startMarker || !endMarker) {
        alert("StartMaker and/or EndMarker are not defined.");
        return false;
    }
    return true;
};

document.cloneStartMarkerFromElevationMarkers = function () {
    var length = elevationMarkers.length;
    var marker = elevationMarkers[length - 2];
    document.addStartMarker(marker.getPosition().lat(), marker.getPosition().lng());
};

document.cloneEndMarkerFromElevationMarkers = function () {
    var length = elevationMarkers.length;
    var marker = elevationMarkers[length - 1];
    document.addEndMarker(marker.getPosition().lat(), marker.getPosition().lng());
};

document.clearLastTwoElevationMarkers = function () {
    var length = elevationMarkers.length;
    document.clearMarker(elevationMarkers[length - 2]);
    document.clearMarker(elevationMarkers[length - 1]);
    elevationMarkers.splice(length - 2, 2);
};

document.getCurrentRoutePositions = function () {
  return document.getMarkersPositions(startMarker, endMarker);
};

document.getLastTwoElevationMarkersPositions = function () {
    var length = elevationMarkers.length;
    return document.getMarkersPositions(elevationMarkers[length - 2], elevationMarkers[length - 1]);
};

document.getMarkersPositions = function (first, second) {
    var rez = '';
    rez += first.getPosition().lat() + ',' + first.getPosition().lng() + ',' + second.getPosition().lat() + ',' + second.getPosition().lng();
    return rez;
};