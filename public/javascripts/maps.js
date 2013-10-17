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

    while(observations[i] != undefined){
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(observations[i].latitude, observations[i].longitude),
            map: map

        });
        i++;
    }

}
google.maps.event.addDomListener(window, 'load', initialize);



