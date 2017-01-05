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
            url: 'start_marker.png'
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
            url: 'end_marker.png'
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
    if (startMarker) {
        startMarker.setMap(null);
        startMarker = null;
    }
};

document.clearEndMarker = function () {
    if (endMarker) {
        endMarker.setMap(null);
        endMarker = null;
    }
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

document.clearAllMarkers = function () {
    document.clearStartMarker();
    document.clearEndMarker();
    document.clearIntermediateMarkers();
    document.clearElevationMarkers();
};