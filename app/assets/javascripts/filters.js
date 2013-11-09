/**
 * Created with IntelliJ IDEA.
 * User: oddgeir
 * Date: 11/10/13
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */

gitleHunt.filter('filterObservations', function(){
    return function(input, animal){
        var filteredObservations = [];
        var animalFilteredObservations = [];
        if(!animal){
            animalFilteredObservations = JSON.parse(JSON.stringify(input));
        }
        else{
            for(var i = 0;i<input.length; i++){
                if(input[i].animal.indexOf(animal) != -1){
                    animalFilteredObservations.splice(0,0,input[i])
                }
            }
        }
        //TODO Filter based on start date

        //TODO Filter based on end date

    return filteredObservations;
    }
});