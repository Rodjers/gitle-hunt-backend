/**
 * Created with IntelliJ IDEA.
 * Author: oddgeir
 * Date: 10/14/13
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
var map;

function initialize() {

    var LatLong = new google.maps.LatLng(60.25, 7.5);
    var mapOptions = {
        center: LatLong,
        zoom: 6,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("map-canvas"),
        mapOptions);
    google.maps.event.addListener(map, 'tilesloaded', function() {
        var north = map.getBounds().getNorthEast().lat();
        var west = map.getBounds().getSouthWest().lng();
        var south = map.getBounds().getSouthWest().lat();
        var east = map.getBounds().getNorthEast().lng();
    $.getJSON( "square?north=" + north + "&south=" + south + "&west=" + west + "&east=" + east, function( data ) {
        drawCollection(data, map);
    });
});


}
function drawCollection(observations, map){

    var i = 0;
    var markers = [];
    var infoWindows = [];

    while(observations[i] != undefined){
        markers.push(new google.maps.Marker({
            position: new google.maps.LatLng(observations[i].latitude, observations[i].longitude),
            map: map
        }));

        var contentString = '<div>' +
            'Animal: ' +
            observations[i].animal +
            '<br>' +
            'Amount: ' +
            observations[i].amount +
            '</div>';

        infoWindows.push(new google.maps.InfoWindow({
            content: contentString
        }));

        i++;
    }
    for (var n = 0; n < markers.length; n++){
        placeInfoWindow(markers[n], infoWindows[n])
        }
}
function placeInfoWindow(marker, thisWindow) {

    google.maps.event.addListener(marker, 'click', function()
    {
        thisWindow.open(map,marker);
    });
}
google.maps.event.addDomListener(window, 'load', initialize);



