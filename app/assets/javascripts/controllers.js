/**
 * Author: oddgeir
 * Date: 10/23/13
 * Time: 10:10 PM
 */



angular.module('gitleHunt.controllers', ['gitleHunt.services']).
    controller('ObservationsCtrl', function ($scope, observationModel) {
        $scope.observations = observationModel.getObservations();

        var i = 0;
        while ($scope.observations[i] != undefined){
            var infoWindow = '<div>' +
                'Animal: ' +
                $scope.observations[i].animal +
                '<br>' +
                'Amount: ' +
                $scope.observations[i].amount +
                '</div>';

            $scope.observations[i].infoWindow = infoWindow;
            i++;
        }

        angular.extend($scope, {
            center: {
                latitude: 59.5, // initial map center latitude
                longitude: 7 // initial map center longitude
            },
            markers: $scope.observations, // an array of markers,
            zoom: 8 // the zoom level
        });

        $scope.registerObservation = function registerObservation(animal, amount) {


            var observation = {};
            var dateTime = new Date();
            var timestamp = dateTime.getFullYear() + "-" + dateTime.getMonth() + "-" + dateTime.getDay() + " " +
                dateTime.getHours() + ":" + dateTime.getMinutes() + ":" + dateTime.getSeconds()
            observation.animal = animal;
            observation.amount = +(amount);
            observation.timestamp = timestamp;
            observation.latitude = $scope.markers[$scope.markers.length-1].latitude;
            observation.longitude = $scope.markers[$scope.markers.length-1].longitude;

            console.log(JSON.stringify(observation));


            $.ajax({
                type: "POST",
                url: "observations",
                data: JSON.stringify(observation),
                contentType: "application/json"
            });
        }
    });