/**
 * Created by razvanolar on 04.01.2017
 */

var START_MARKER = "start";
var END_MARKER = "end";
var INTERMEDIATE_MARKER = "intermediate";
var NONE_MARKER = "none";

var startMarker = null;
var endMarker = undefined;
var intermediateMarkers = [];
var markerType = NONE_MARKER;

google.maps.event.addListener(map, "click", function (event) {
    // util.test(event.latLng.lat(), event.latLng.lng());
    var latLng = event.latLng;
    if (markerType === START_MARKER) {
        if (startMarker) {
            startMarker.setMap(null);
            startMarker = null;
        }
        util.addMarker(latLng.lat(), latLng.lng(), 'addStartMarker');
    } else if (markerType === END_MARKER) {
        if (endMarker) {
            endMarker.setMap(null);
            endMarker = null;
        }
        util.addMarker(latLng.lat(), latLng.lng(), 'addEndMarker');
    } else if (markerType === INTERMEDIATE_MARKER) {
        util.addMarker(latLng.lat(), latLng.lng(), 'addIntermediateMarker');
    }
});

document.changeMarkerType = function (type) {
    if (type == END_MARKER || type == START_MARKER || type == INTERMEDIATE_MARKER || type == NONE_MARKER)
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
    if (intermediateMarkers.length > 8) {
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