/**
 * Created with IntelliJ IDEA.
 * Author: oddgeir
 * Date: 10/14/13
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
function initialize() {
    var LatLong = new google.maps.LatLng(60.25, 7.5);
    var mapOptions = {
        center: LatLong,
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map-canvas"),
        mapOptions);
    var markerOptions = {
        map: map,
        position: LatLong
    }
    var marker = new google.maps.Marker(markerOptions);
}
google.maps.event.addDomListener(window, 'load', initialize);