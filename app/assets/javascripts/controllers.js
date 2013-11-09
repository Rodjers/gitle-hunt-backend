/**
 * Author: oddgeir
 * Date: 10/23/13
 * Time: 10:10 PM
 */



angular.module('gitleHunt.controllers', ['gitleHunt.services']).
    controller('ObservationsCtrl', function ($scope, $filter, observationModel) {

        $scope.observationFilter = {};
        $scope.observationFilter.startDate = "2013-01-01";
        $scope.observationFilter.endDate = $filter('date')(new Date(), 'yyyy-MM-dd');
        $scope.observationFilter.animal = '';


        $scope.observations = observationModel.getObservations();

        var i = 0;
        while ($scope.observations[i] != undefined){
            var stringDate = $filter('date')($scope.observations[i].date, 'EEE d. MMM yyyy');
            var stringTime = $filter('date')($scope.observations[i].time, 'HH:mm:ss');
            var infoWindow = '<div>' +
                'Animal: ' +
                $scope.observations[i].animal +
                '<br>' +
                'Amount: ' +
                $scope.observations[i].amount +
                '<br>' +
                'Date: ' +
                stringDate  +
                '<br>' +
                'Time: ' +
                stringTime  +
                '</div>';

            $scope.observations[i].infoWindow = infoWindow;
            i++;
        }



        angular.extend($scope, {
            center: {
                latitude: 59.5, // initial map center latitude
                longitude: 7 // initial map center longitude
            },
            markers: [], // an array of markers,
            zoom: 8,
            latitude: null,
            longitude: null
        });

        $scope.registerObservation = function registerObservation(animal, amount, latitude, longitude) {


            var observation = {};
            var dateTime = new Date();
            var date = $filter('date')(dateTime, 'yyyy-MM-dd');
            var time = $filter('date')(dateTime, 'HH:mm:ss');
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

            document.getElementById('registerObservationForm').reset();
        };

        $scope.$watch("observationFilter.animal", function(newValue){
            $scope.markers = $filter('filterObservations')($scope.observations, newValue);
        });
    });