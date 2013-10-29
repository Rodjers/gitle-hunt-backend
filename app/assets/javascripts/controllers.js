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
            zoom: 8,
            latitude: null,
            longitude: null
        });

        $scope.registerObservation = function registerObservation(animal, amount, latitude, longitude) {


            var observation = {};
            var dateTime = new Date();
            var date = dateTime.getFullYear() + "-" + dateTime.getMonth() + "-" + dateTime.getDay();
            var time = dateTime.getHours() + ":" + dateTime.getMinutes() + ":" + dateTime.getSeconds();
            observation.animal = animal;
            observation.amount = +(amount);
            observation.date = date;
            observation.time = time;
            observation.latitude = latitude;
            observation.longitude = longitude;



            $.ajax({
                type: "POST",
                url: "observations",
                data: JSON.stringify(observation),
                contentType: "application/json"
            });
            observation.infoWindow = '<div>' +
                'Animal: ' +
                animal +
                '<br>' +
                'Amount: ' +
                amount +
                '</div>';

            $scope.observations.splice($scope.observations.length,0,observation);
            document.getElementById('registerObservationForm').reset();
        }
    });