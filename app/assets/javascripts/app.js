/**
 * Author: oddgeir
 * Date: 10/23/13
 * Time: 10:07 PM.
 */

var gitleHunt = angular.module('gitleHunt', ['gitleHunt.services', 'gitleHunt.controllers', 'google-maps']);

//gitleHunt.directive('hunterMap', function($parse) {
//    return function(scope, elm, attrs){
//            var LatLong = new google.maps.LatLng(60.25, 7.5);
//            var mapOptions = {
//                center: LatLong,
//                zoom: 6,
//                mapTypeId: google.maps.MapTypeId.ROADMAP
//            };
//            map = new google.maps.Map(elm,
//                mapOptions);
//
//            currentMarker = new google.maps.Marker({
//                map: map
//            });
//        elm.text("Hello World");
//        }
//});