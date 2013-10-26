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
//        $scope.observations = observationModel.getObservations();
//        console.log(JSON.stringify($scope.observations));
//        var temp = 0;
    });