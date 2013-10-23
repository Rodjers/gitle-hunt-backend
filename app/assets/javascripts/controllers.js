/**
 * Author: oddgeir
 * Date: 10/23/13
 * Time: 10:10 PM
 */

angular.module('gitleHunt.controllers', ['gitleHunt.services']).
    controller('ObservationsCtrl', function ($scope) {
        $scope.observations = chatModel.getObservations();

    });