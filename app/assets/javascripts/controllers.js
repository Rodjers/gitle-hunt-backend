/**
 * Author: oddgeir
 * Date: 10/23/13
 * Time: 10:10 PM
 */



angular.module('gitleHunt.controllers', ['gitleHunt.services']).
    controller('ObservationsCtrl', function ($scope, observationModel) {
//        $scope.observations = [{"animal":"Moose"}];
        $scope.observations = observationModel.getObservations();
        console.log(JSON.stringify($scope.observations));
        var temp = 0;

    });