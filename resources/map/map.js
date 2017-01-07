/**
 * Created by razvanolar on 04.01.2017
 */

// routes variables
var routes_array;
var current_routes_index;

// services
var directionsService = new google.maps.DirectionsService();
var elevationService = new google.maps.ElevationService;

var latlng = new google.maps.LatLng(46.37983000514894, 23.852176666259766);
var Options = {
    zoom: 15,
    center: latlng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
};
var map = new google.maps.Map(document.getElementById("map_canvas"), Options);
var mapRenderer = new google.maps.DirectionsRenderer({
    suppressMarkers: true,
    draggable: true
});
mapRenderer.setMap(map);
var routeInfo = new google.maps.InfoWindow({});

document.findRoute = function () {
    if (!startMarker || !endMarker) {
        alert("Start or End marker is not defined");
        return;
    }
    var waypointsArray = [];
    for (i = 0; i < intermediateMarkers.length; i++) {
        var point = {
            location: intermediateMarkers[i].getPosition(),
            stopover: false
        };
        waypointsArray.push(point);
    }
    alert("waypoints length: " + waypointsArray.length);
    var request = {
        origin: startMarker.getPosition(),
        destination: endMarker.getPosition(),
        provideRouteAlternatives: true,
        travelMode: 'DRIVING',
        unitSystem: google.maps.UnitSystem.METRIC,
        waypoints: waypointsArray
    };
    util.beginServiceCall("Finding Routes...");
    directionsService.route(request, function (result, status) {
        util.endServiceCall();
        if (status === 'OK') {
            alert("routes: " + result.routes.length);

            // render route
            alert("Update renderer");
            mapRenderer.setDirections(result);
            routes_array = result.routes;
            current_routes_index = 0;

            // show route info
            alert("Add info window");
            document.addInfoWindowForRoute(result.routes[0]);

            // get route elevation
            alert("Get elevations");
            document.getElevationsForRoute(result.routes[0]);

            // only for debugging
            // result.routes.forEach(function (route) {
            //     alert("paths: " + route.overview_path.length);
            //     route.overview_path.forEach(function (path) {
            //         alert(path.lat() + " , " + path.lng());
            //     });
            // });
        }
    });
};

document.getElevationsForRoute = function (route) {
    util.beginServiceCall("Finding Elevation...");
    elevationService.getElevationForLocations({
        'locations': route.overview_path
    }, function (result, status) {
        util.endServiceCall();
        if (status === 'OK' && result[0]) {
            var concatenated_elevations = "";
            result.forEach(function (elevation) {
                concatenated_elevations += elevation.elevation + "+" + elevation.location.lat() + "+" + elevation.location.lng() + ",";
            });
            util.updateElevationChart(concatenated_elevations.substring(0, concatenated_elevations.length - 1));
        } else {
            alert("No elevations found");
        }
    });
};

document.getElevationForLatLng = function (latLng) {
    util.beginServiceCall("Finding Elevation...");
    elevationService.getElevationForLocations({
        'locations': [latLng]
    }, function (result, status) {
        util.endServiceCall();
        if (status === 'OK' && result[0]) {
            var res = result[0];
            document.addElevationMarker(res.location.lat(), res.location.lng(), parseInt(res.elevation).toString());
        } else {
            alert("Unable to find elevation");
        }
    });
};

document.addInfoWindowForRoute = function (route) {
    if (!route)
        return;
    var points = route.overview_path;
    if (!points)
        return;
    document.clearInfoWindow();
    var infoWindowPosition = parseInt(points.length / 2);
    var distance = document.calculateTotalDistanceForRoute(route);
    routeInfo.setOptions({
        content: '<div>' + distance + ' km</div>',
        position: points[infoWindowPosition]
    });

    routeInfo.open(map);
};

document.nextRoute = function () {
    if (routes_array && current_routes_index < routes_array.length - 1) {
        mapRenderer.setRouteIndex(++current_routes_index);
        document.addInfoWindowForRoute(routes_array[current_routes_index]);
        document.getElevationsForRoute(routes_array[current_routes_index]);
        alert("Route index changed to " + current_routes_index + " from " + routes_array.length);
    }
};

document.prevRoute = function () {
    if (current_routes_index > 0) {
        mapRenderer.setRouteIndex(--current_routes_index);
        document.addInfoWindowForRoute(routes_array[current_routes_index]);
        document.getElevationsForRoute(routes_array[current_routes_index]);
        alert("Route index changed to " + current_routes_index + " from " + routes_array.length);
    }
};

document.heightChanged = function (newHeight) {
    document.getElementById("map_canvas").setAttribute("style", "height:" + newHeight + "px;");
};

document.clearRenderer = function () {
    if (mapRenderer) {
        mapRenderer.setMap(null);
        mapRenderer.setPanel(null);
    }
    mapRenderer = new google.maps.DirectionsRenderer({suppressMarkers: true});
    mapRenderer.setMap(map);
    routes_array = null;
    current_routes_index = -1;
};

document.clearInfoWindow = function () {
    if (routeInfo) {
        routeInfo.close();
    }
};

document.calculateTotalDistanceForRoute = function (route) {
    if (!route || !route.legs)
        return 0;
    var total = 0;
    route.legs.forEach(function (leg) {
       total += leg.distance.value;
    });
    return parseInt(total) / 1000;
};