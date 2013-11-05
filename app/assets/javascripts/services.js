/**
 * Author: oddgeir
 * Date: 10/23/13
 * Time: 10:12 PM
 */

angular.module('gitleHunt.services', []).service('observationModel', function () {
    var getObservations = function () {
        var observations = [];
        var xmlHttp = null;

        xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", "square?north=180&south=0&west=0&east=180", false );
        xmlHttp.send( null );
        var data = JSON.parse(xmlHttp.responseText);
        var i = 0;

            while (data[i] != undefined){
                observations.splice(i,0,data[i]);
                i++;
            }

        return observations;
    };
    return { getObservations: getObservations };
});